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
	private String keyId;
	private String azureKeyName;
	private String kmsKeyId;
	private Path certFilePath;
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

		if (certThumb == null && pkcs12Path == null && trustServiceSession == null && keyId == null && azureKeyName == null && kmsKeyId == null) {
			throw new RuntimeException("No PKCS #12 file, certificate's thumbprint, TrustServiceSession, PKCS #11 keyId, Azure Key Vault key name or AWS KMS key id was provided");
		}

		if ((azureKeyName != null || kmsKeyId != null) && certFilePath == null) {
			throw new RuntimeException("A certificate file must be provided when using an Azure Key Vault key name or an AWS KMS key id");
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

		if (keyId != null) {
			args.add("--key-id");
			args.add(keyId);
			versionManager.requireVersion(new Version("1.37"));
		}

		if (azureKeyName != null) {
			args.add("--key-name");
			args.add(azureKeyName);
			// This option can only be used on versions greater than 1.20 of the PKI Express.
			versionManager.requireVersion(new Version("1.20"));
		}

		if (kmsKeyId != null) {
			args.add("--kms-key-id");
			args.add(kmsKeyId);
			// This option can only be used on versions greater than 1.39 of the PKI Express.
			versionManager.requireVersion(new Version("1.39"));
		}

		if (certFilePath != null) {
			args.add("--cert-file");
			args.add(certFilePath.toString());
			// This option can only be used on versions greater than 1.20 of the PKI Express.
			versionManager.requireVersion(new Version("1.20"));
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

	/**
	 * Sets the PKCS #11 key identifier of the signer's private key (e.g. a key stored on AWS CloudHSM). The
	 * identifier can be either the key's id in Base64 or its label. The token's PIN is taken from the value set on
	 * {@link #setCertPassword(String)} (--password parameter) or, if not set, from the "pkcs11Pin" setting on the
	 * PKI Express configuration.
	 *
	 * @param keyId The PKCS #11 key id (in Base64) or label.
	 */
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	/**
	 * Sets the name of the signer's key stored on Azure Key Vault. When using this option, the signer's certificate
	 * must also be provided by {@link #setCertificateFile(Path)} or {@link #setCertificateFile(String)}.
	 *
	 * @param azureKeyName The name of the key on Azure Key Vault.
	 */
	public void setAzureKeyName(String azureKeyName) {
		this.azureKeyName = azureKeyName;
	}

	/**
	 * Sets the identifier of the signer's key stored on AWS KMS. When using this option, the signer's certificate
	 * must also be provided by {@link #setCertificateFile(Path)} or {@link #setCertificateFile(String)}.
	 *
	 * @param kmsKeyId The AWS KMS key id, key ARN, alias name (e.g. "alias/my-key") or alias ARN.
	 */
	public void setKmsKeyId(String kmsKeyId) {
		this.kmsKeyId = kmsKeyId;
	}

	//region setCertificateFile
	/**
	 * Sets the signer's certificate file. This is required when the signer's key is stored on Azure Key Vault
	 * ({@link #setAzureKeyName(String)}) or on AWS KMS ({@link #setKmsKeyId(String)}).
	 *
	 * @param path The path to the signer's certificate file.
	 * @throws IOException if the provided certificate file was not found.
	 */
	public void setCertificateFile(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided certificate file was not found");
		}
		this.certFilePath = path;
	}

	/**
	 * Sets the signer's certificate file. This is required when the signer's key is stored on Azure Key Vault
	 * ({@link #setAzureKeyName(String)}) or on AWS KMS ({@link #setKmsKeyId(String)}).
	 *
	 * @param path The path to the signer's certificate file.
	 * @throws IOException if the provided certificate file was not found.
	 */
	public void setCertificateFile(String path) throws IOException {
		setCertificateFile(path != null ? Paths.get(path) : null);
	}
	//endregion

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
