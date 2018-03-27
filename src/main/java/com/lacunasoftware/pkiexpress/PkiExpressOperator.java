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
    protected Boolean offline = false;
    protected VersionManager versionManager;

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

    protected String[] invokePlain(CommandEnum command, List<String> args) throws IOException {
        OperatorResult result = invoke(command, args, true);
        return result.getOutput();
    }

    protected OperatorResult invoke(CommandEnum command, List<String> args) throws IOException {
        return invoke(command, args, false);
    }

    protected OperatorResult invoke(CommandEnum command, List<String> args, boolean plainOutput) throws IOException {

        // Add PKI Express invocation arguments
        List<String> cmdArgs = new ArrayList<>();
        cmdArgs.addAll(getPkiExpressInvocation());

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

        // Verify the necessity of using the --min-version flag.
        if (versionManager.requireMinVersionFlag()) {
            cmdArgs.add("--min-version");
            cmdArgs.add(versionManager.getMinVersion().toString());
        }

        // Process command arguments
        String[] argArr = new String[cmdArgs.size()];
        cmdArgs.toArray(argArr);

        // Execute the command
        Process proc = Runtime.getRuntime().exec(argArr);

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

    protected List<String> getPkiExpressInvocation() {

        String os = null;

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
                if (!Files.exists(home.resolve("pkie.dll"))) {
                    throw new RuntimeException("The file pkie.dll could not be found on directory " + home.toString());
                }
            } else {
                if (!Files.exists(home.resolve("pkie.exe"))) {
                    throw new RuntimeException("The file pkie.exe could not be found on directory " + home.toString());
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
                    throw new RuntimeException("Could not determine the installation folder of PKI Express. If you installed PKI Express on a custom folder, make sure you are specifying it on the PkiExpressConfig object.");
                }

            }

        }

        List<String> invocArgs = new ArrayList<>();
        if (os.equals("linux")) {

            if (home != null) {
                invocArgs.add("dotnet");
                invocArgs.add(home.resolve("pkie.dll").toString());
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

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }

}
