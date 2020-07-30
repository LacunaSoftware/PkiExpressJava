package com.lacunasoftware.pkiexpress;


import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


abstract class Signer extends BaseSigner {
	protected Path outputFilePath;

	private String certThumb;
	private Path pkcs12Path;
	private String certPassword;
	private boolean useMachine;
	private String trustServiceSession;


	public Signer(PkiExpressConfig config) {
		super(config);
	}

	public Signer() throws IOException {
		this(new PkiExpressConfig());
	}


	protected void verifyAndAddCommonOptions(List<String> args) {

		// Verify and add common options between signers and signature starters.
		super.verifyAndAddCommonOptions(args);

		if (certThumb == null && pkcs12Path == null && trustServiceSession == null) {
			throw new RuntimeException("No PKCS #12 file, certificate's thumbprint or TrustServiceSession was provided");
		}

		if (certThumb != null) {
			args.add("--thumbprint");
			args.add(certThumb);
			versionManager.requireVersion(new Version("1.3"));
		}

		if (pkcs12Path != null) {
			args.add("--pkcs12");
			args.add(pkcs12Path.toString());
			versionManager.requireVersion(new Version("1.3"));
		}

		if (certPassword != null) {
			args.add("--password");
			args.add(certPassword);
			versionManager.requireVersion(new Version("1.3"));
		}

		if (useMachine) {
			args.add("--machine");
			versionManager.requireVersion(new Version("1.3"));
		}

		// Add trust service session.
		if (trustServiceSession != null) {
			args.add("--trust-service-session");
			args.add(trustServiceSession);
			// This option can only be used on versions greater than 1.17 of the PKI Express.
			versionManager.requireVersion(new Version("1.17"));
		}

	}

	public void setOutputFile(Path path) {
		this.outputFilePath = path;
	}

	public void setCertificateThumbprint(String certThumb) {
		this.certThumb = certThumb;
	}

	//region setPkcs12
	public void setPkcs12(InputStream inputStream) throws IOException {
		this.pkcs12Path = writeToTempFile(inputStream);
	}

	public void setPkcs12(byte[] content) throws IOException {
		setPkcs12(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setPkcs12(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided PKCS #12 certificate file was not found");
		}
		this.pkcs12Path = path;
	}

	public void setPkcs12(String path) throws IOException {
		setPkcs12(path != null ? Paths.get(path) : null);
	}
	//endregion

	public void setCertPassword(String password) {
		this.certPassword = password;
	}

	public void setUseMachine(boolean useMachine) {
		this.useMachine = useMachine;
	}

	public String getTrustServiceSession() {
		return trustServiceSession;
	}

	public void setTrustServiceSession(String trustServiceSession) {
		this.trustServiceSession = trustServiceSession;
	}
}
