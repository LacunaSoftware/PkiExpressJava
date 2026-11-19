package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.security.SecureRandom;


public abstract class PkiExpressOperator {
	private List<Path> tempFiles;
	private Map<String, Path> fileReferences;
	private Boolean disposed = false;

	protected PkiExpressConfig config;
	protected List<Path> trustedRoots;
	protected boolean trustOnlyICPBrasil = false;
	protected boolean trustOnlySystem = false;
	protected Boolean offline = false;
	protected VersionManager versionManager;
	protected StandardSignaturePolicies signaturePolicy;
	protected TimestampAuthority timestampAuthority;
	protected String culture;
	protected String timeZone;


	@Deprecated
	public Boolean trustLacunaTestRoot = false;

	private static SecureRandom rng = new SecureRandom();


	protected PkiExpressOperator(PkiExpressConfig config) {
		this.config = config;
		this.trustedRoots = new ArrayList<>();
		this.tempFiles = new ArrayList<>();
		this.fileReferences = new HashMap<>();
		this.versionManager = new VersionManager();
	}

	protected PkiExpressOperator() throws IOException {
		this(new PkiExpressConfig());
	}

	/**
	 * Returns whether arguments must be escaped by this class before being given to ProcessBuilder.
	 *
	 * On non-Windows platforms the JDK passes the argument array directly to the child process, so
	 * no escaping is needed (and escaping would corrupt values containing quotes).
	 *
	 * On Windows, the JDK (java.lang.ProcessImpl.createCommandLine) joins the arguments into a
	 * single command line, which pkie.exe splits again following the MSVCRT/CommandLineToArgvW
	 * rules. Whether the JDK escapes quotes inside arguments depends on the value of the
	 * jdk.lang.Process.allowAmbiguousCommands property:
	 *
	 * - Effectively "false" (set explicitly, or not set while a SecurityManager is installed): the
	 *   JDK quotes each argument and escapes quotes inside it itself (VERIFICATION_WIN32_SAFE), so
	 *   escaping here would apply it twice. JDKs older than 8u221/11.0.4 (JDK-8221858) do not
	 *   escape in this mode either, which this library can't fix without double escaping on newer
	 *   JDKs. In this mode the JDK also strips the outer quotes of values that start and end with a
	 *   quote, and rejects some of them, so such values don't round-trip exactly.
	 * - Any other value: the JDK does not escape quotes inside arguments, and copies an argument
	 *   that starts and ends with a quote verbatim, so we must escape. This includes the default
	 *   "legacy" mode and also the strict mode used when a SecurityManager is installed and the
	 *   property is set to "true" (VERIFICATION_WIN32, which does not escape either). Do not add
	 *   a SecurityManager check here: it would reopen argument injection in that configuration.
	 *
	 * The default value mirrors ProcessImpl. On JDK 24+ (JDK-8343958) the SecurityManager is gone
	 * and System.getSecurityManager() always returns null, so the default is "true".
	 */
	@SuppressWarnings("removal")
	private static boolean mustEscapeArguments() {
		if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
			return false;
		}
		String defaultValue = System.getSecurityManager() == null ? "true" : "false";
		String allowAmbiguousCommands = System.getProperty("jdk.lang.Process.allowAmbiguousCommands", defaultValue);
		return !"false".equalsIgnoreCase(allowAmbiguousCommands);
	}

	/**
	 * Quotes an argument so that pkie.exe parses it back as exactly one argument with the original
	 * value, following the MSVCRT/CommandLineToArgvW rules:
	 *
	 * - 2n backslashes followed by a quote produce n backslashes and a quote that opens/closes
	 *   the quoted section;
	 * - 2n+1 backslashes followed by a quote produce n backslashes and a literal quote;
	 * - backslashes not followed by a quote are literal.
	 *
	 * These rules are common to the C runtime (UCRT, used by .NET apphosts) and CommandLineToArgvW
	 * (used by .NET Framework). The two differ on "" inside a quoted section, which the result
	 * never contains.
	 *
	 * The result always starts and ends with a quote, so the JDK treats it as already quoted and
	 * copies it verbatim to the command line (whenever mustEscapeArguments() returns true). It
	 * also never ends with \", which JDK 8u281+ (JDK-8250568) doesn't consider properly quoted.
	 *
	 * Package-private for testing.
	 */
	static String escapeWindowsArgument(String arg) {

		// Separate trailing backslashes, which need special handling (see below).
		int end = arg.length();
		while (end > 0 && arg.charAt(end - 1) == '\\') {
			end--;
		}
		int trailingBackslashes = arg.length() - end;

		StringBuilder sb = new StringBuilder();
		sb.append('"');
		int backslashes = 0;
		for (int i = 0; i < end; i++) {
			char c = arg.charAt(i);
			if (c == '\\') {
				backslashes++;
				continue;
			}
			if (c == '"') {
				// Double the preceding backslashes and escape the quote itself.
				appendBackslashes(sb, backslashes * 2 + 1);
			} else {
				appendBackslashes(sb, backslashes);
			}
			backslashes = 0;
			sb.append(c);
		}
		appendBackslashes(sb, backslashes);
		sb.append('"');

		if (trailingBackslashes > 0) {
			// Trailing backslashes are written after the closing quote, doubled and followed by
			// an empty quoted section (""), which yields exactly the original backslashes. We
			// avoid the usual form ("abc\\") because JDK 8u281+ (JDK-8250568) does not treat a
			// string ending in \" as already quoted, and would quote it again if it contains
			// whitespace. JDK-8282008 removed that check from JDK 19 and from 11u/17u, but it is
			// still in 8u.
			appendBackslashes(sb, trailingBackslashes * 2);
			sb.append("\"\"");
		}
		return sb.toString();
	}

	private static void appendBackslashes(StringBuilder sb, int count) {
		for (int i = 0; i < count; i++) {
			sb.append('\\');
		}
	}


	protected String[] invokePlain(CommandEnum command, List<String> args) throws IOException {
		OperatorResult result = invoke(command, args, true);
		return result.getOutput();
	}

	protected OperatorResult invoke(CommandEnum command, List<String> args) throws IOException {
		return invoke(command, args, false);
	}

	protected OperatorResult invoke(CommandEnum command, List<String> args, boolean plainOutput) throws IOException {

		// Add PKI Express invocation arguments
		List<String> invocation = getPkiExpressInvocation();
		List<String> cmdArgs = new ArrayList<>(invocation);

		// Add PKI Express command
		cmdArgs.add(command.getValue());

		// Add PKI Express arguments
		cmdArgs.addAll(args);

		// Add file references if added
		if (fileReferences != null && !fileReferences.isEmpty()) {

			for (String key : fileReferences.keySet()) {
				cmdArgs.add("--file-reference");
				cmdArgs.add(String.format("%s=%s", key, fileReferences.get(key)));
			}
		}

		// Add trusted roots if added
		if (trustedRoots != null && !trustedRoots.isEmpty()) {

			for (Path trustedRoot : trustedRoots) {
				cmdArgs.add("--trust-root");
				cmdArgs.add(trustedRoot.toString());
			}
		}

		if (trustOnlyICPBrasil){
			cmdArgs.add("--trust-clear");
			cmdArgs.add("--trust-brazil");
		}

		if (trustOnlySystem){
			cmdArgs.add("--trust-clear");
			cmdArgs.add("--trust-system");
		}

		// Add trust Lacuna test root if set
		if (trustLacunaTestRoot) {
			cmdArgs.add("--trust-test");
		}

		// Add offline option if provided
		if (offline) {
			cmdArgs.add("--offline");
			// This option can only be used on versions greater than 1.2 of the PKI Express.
			versionManager.requireVersion(new Version("1.2"));
		}

		// Add base64 output option.
		if (!plainOutput) {
			cmdArgs.add("--base64");
			// This option can only be used on versions greater than 1.3 of the PKI Express.
			versionManager.requireVersion(new Version("1.3"));
		}

		// Add culture information.
		if (culture != null) {
			cmdArgs.add("--culture");
			cmdArgs.add(culture);
			// This option can only be used on versions greater than 1.10 of the PKI Express.
			versionManager.requireVersion(new Version("1.10"));
		}

		// Add timezone option.
		if (timeZone != null) {
			cmdArgs.add("--timezone");
			cmdArgs.add(timeZone);
			// This option can only be used on versions greater than 1.10 of the PKI Express.
			versionManager.requireVersion(new Version("1.10"));
		}

		// Verify the necessity of using the --min-version flag.
		if (versionManager.requireMinVersionFlag()) {
			cmdArgs.add("--min-version");
			cmdArgs.add(versionManager.getMinVersion().toString());
		}

		// Process command arguments
		String[] argArr = new String[cmdArgs.size()];
		cmdArgs.toArray(argArr);

		// On Windows (see mustEscapeArguments), quote every argument so that values containing
		// quotes, or backslashes before quotes, round-trip exactly and can't inject extra
		// arguments. The invocation (the pkie.exe path, a single element on Windows) is left to
		// the JDK, which quotes it itself.
		if (mustEscapeArguments()) {
			for (int i = invocation.size(); i < argArr.length; i++) {
				argArr[i] = escapeWindowsArgument(argArr[i]);
			}
		}

		// Execute the command
		Process proc;
		try {
			proc = new ProcessBuilder(argArr).start();
		} catch (IOException ex) {
			throw new InstallationNotFoundException("Could not find PKI Express' installation.", ex);
		}


		// Read response
		BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		ArrayList<String> response = new ArrayList<String>();
		String lineBuff;
		while ((lineBuff = input.readLine()) != null) {
			response.add(lineBuff);
		}

		// Terminate process
		int output;
		try {

			// Wait finish
			output = proc.waitFor();

			// Close streams
			proc.getInputStream().close();
			proc.getErrorStream().close();
			proc.getOutputStream().close();

			// Destroy
			proc.destroy();

		} catch (InterruptedException ex) {
			throw new RuntimeException("An unexpected InterruptedException has occurred", ex);
		}

		// Transform to string array
		String[] responseArr = new String[response.size()];
		response.toArray(responseArr);

		// Return result of the command
		OperatorResult result = new OperatorResult(output, responseArr);
		if (result.getResponse() != 0) {
			StringBuilder sb = new StringBuilder();
			for (String line : result.getOutput()) {
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
			}
			if (result.getResponse() == 1 && versionManager.getMinVersion().compareTo(new Version("1.0")) > 0) {
				String errorMsg = String.format("%s %s>>>>> TIP: This operation requires PKI Express %s, please check your PKI Express version.",
						sb.toString(), System.getProperty("line.separator"), versionManager.getMinVersion());
				throw new RuntimeException(errorMsg);

			}
			throw new RuntimeException(sb.toString());
		}

		return result;
	}

	/**
	 * @return
	 * @throws InstallationNotFoundException
	 */
	protected List<String> getPkiExpressInvocation() throws InstallationNotFoundException, IOException {

		String os;

		// Identify OS
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			os = "linux";
		} else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			os = "win";
		} else {
			throw new RuntimeException("Unsupported OS: " + System.getProperty("os.name").toLowerCase());
		}

		// Verify if the PKI Express home is set on configuration
		Path home = config.getPkiExpressHome();
		if (home != null) {

			if (os.equals("linux")) {
				if (!Files.exists(home.resolve("pkie.dll")) && !Files.exists(home.resolve("pkie"))) {
					// 25-09-23 Changed because in newer versions of PkiExpress (i.e. >1.31) this
					// DLL file is no longer required
					throw new InstallationNotFoundException(
							"Neither the pkie.dll nor the pkie standalone file could be found on directory "
									+ home.toString());
				}
			} else {
				if (!Files.exists(home.resolve("pkie.exe"))) {
					throw new InstallationNotFoundException("The file pkie.exe could not be found on directory " + home.toString());
				}
			}

		} else {

			// On windows, search for a standard installation file.
			if (os.equals("win")) {

				if (Files.exists(Paths.get(System.getenv("ProgramFiles")).resolve("Lacuna Software\\PKI Express\\pkie.exe"))) {
					home = Paths.get(System.getenv("ProgramFiles")).resolve("Lacuna Software\\PKI Express");
				} else if (Files.exists(Paths.get(System.getenv("ProgramFiles(x86)")).resolve("Lacuna Software\\PKI Express\\pkie.exe"))) {
					home = Paths.get(System.getenv("ProgramFiles(x86)")).resolve("Lacuna Software\\PKI Express");
				} else if (Files.exists(Paths.get(System.getenv("LOCALAPPDATA")).resolve("Lacuna Software\\PKI Express\\pkie.exe"))) {
					home = Paths.get(System.getenv("LOCALAPPDATA")).resolve("Lacuna Software\\PKI Express");
				} else if (Files.exists(Paths.get(System.getenv("LOCALAPPDATA")).resolve("Lacuna Software\\PKI Express (x86)\\pkie.exe"))) {
					home = Paths.get(System.getenv("LOCALAPPDATA")).resolve("Lacuna Software\\PKI Express (x86)");
				}

				if (home == null) {
					throw new InstallationNotFoundException("Could not determine the installation folder of PKI Express. If you installed PKI Express on a custom folder, make sure you are specifying it on the PkiExpressConfig object.");
				}
			}

		}

		List<String> invocArgs = new ArrayList<>();
		if (os.equals("linux")) {

			if (home != null) {
				// 16-10-23 Changed because in newer versions of PkiExpress (i.e. >1.31) this
				// DLL file is no longer required
				if (Files.exists(home.resolve("pkie.dll"))) {
					invocArgs.add("dotnet");
					invocArgs.add(home.resolve("pkie.dll").toString());
				} else if (Files.exists(home.resolve("pkie"))) {
					invocArgs.add(home.resolve("pkie").toString());
				} else {
					throw new InstallationNotFoundException(
							"Neither the pkie.dll nor the pkie standalone file could be found on directory "
									+ home.toString());
				}
			} else {
				invocArgs.add("pkie");
			}

		} else {
			invocArgs.add(home.resolve("pkie.exe").toString());
		}

		return invocArgs;
	}

	protected Path createTempFile() throws IOException {
		Path tempFile = Files.createTempFile(config.getTempFolder(), "pkie", "");
		tempFiles.add(tempFile);
		return tempFile;
	}

	protected String getTransferFileName() {
		byte[] bytes = new byte[16];
		rng.nextBytes(bytes);
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	protected Path writeToTempFile(InputStream inputStream) throws IOException {
		byte[] buff = new byte[1024];
		Path tempPath = createTempFile();
		OutputStream outputStream = new FileOutputStream(tempPath.toFile());

		int nRead;
		while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
			outputStream.write(buff, 0, nRead);
		}
		outputStream.close();
		return tempPath;
	}

	protected <TResponse> TResponse parseOutput(String outputBase64, Class<TResponse> responseType) throws IOException {
		byte[] output = Util.decodeBase64(outputBase64);
		return new ObjectMapper().readValue(output, responseType);
	}

	public Boolean getTrustLacunaTestRoot() {
		return trustLacunaTestRoot;
	}

	public void setTrustLacunaTestRoot(Boolean trustLacunaTestRoot) {
		this.trustLacunaTestRoot = trustLacunaTestRoot;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public void finalize() {
		dispose();
	}

	public void dispose() {
		if (!disposed) {
			for (Path tempFile : tempFiles) {
				try {
					Files.delete(tempFile);
				} catch (Exception ex) {
					// TODO: log
				}
			}
			disposed = true;
		}
	}

	public void addFileReference(String alias, Path path) {
		if (!Files.exists(path)) {
			throw new RuntimeException("The provided file path was not found");
		}

		fileReferences.put(alias, path);
	}

	public void addFileReference(String alias, String path) {
		addFileReference(alias, path != null ? Paths.get(path) : null);
	}

	public void addTrustedRoot(Path path) {
		if (!Files.exists(path)) {
			throw new RuntimeException("The provided trusted root was not found");
		}

		trustedRoots.add(path);
	}

	public void addTrustedRoot(String path) {
		addTrustedRoot(path != null ? Paths.get(path) : null);
	}

	public void setTrustOnlyICPBrasil(boolean trust) {
		trustOnlyICPBrasil = trust;
	}

	public void setTrustOnlySystem(boolean trust) {
		trustOnlySystem = trust;
	}

	public Boolean getOffline() {
		return offline;
	}

	public void setOffline(Boolean offline) {
		this.offline = offline;
	}

	public void setSignaturePolicy(StandardSignaturePolicies signaturePolicy) {
		this.signaturePolicy = signaturePolicy;
	}

	@Deprecated
	public void setSignaturePolicy(XmlSignaturePolicies signaturePolicy) {

		switch (signaturePolicy) {
			case NFe:
				this.signaturePolicy = StandardSignaturePolicies.NFePadraoNacional;
				break;
			case Basic:
				this.signaturePolicy = StandardSignaturePolicies.XmlDSigBasic;
				break;
			default:
				throw new RuntimeException("Invalid signature policy: " + signaturePolicy.getValue());
		}
	}

	public void setTimestampAuthority(TimestampAuthority timestampAuthority) {
		this.timestampAuthority = timestampAuthority;
	}
}
