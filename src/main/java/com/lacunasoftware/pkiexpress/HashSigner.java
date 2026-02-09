package com.lacunasoftware.pkiexpress;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Class used to perform a signature algorithm computation on a "to-sign-hash".
 * 
 * This class corresponds to the "sign-hash" command of PKI Express.
 * 
 * Usage: pkie sign-hash <to-sign-hash> [options]
 * 
 * Arguments:
 *   to-sign-hash   The hash to be used as input on the signature algorithm computation (hex or Base64).
 * 
 * Options:
 *   --algorithm|-a <algorithm>   The digest algorithm used to compute the hash (OID or name). If omitted,
 *                                the algorithm is inferred from the size of the hash.
 * 
 * Certificate store options:
 *   --machine|-m                Use certificates from the machine certificate store.
 *   --pkcs12|-p12               Use certificates from the given PKCS #12 (.pfx) file.
 *   --password|-pw <pass>       Password for the PKCS #12 file.
 *   --thumbprint|-t <hex>       Thumbprint of the certificate (required if the store contains multiple certificates).
 *   --key-name|-kn <key-name>   Name that identifies the signer's key on Azure Key Vault.
 *   --cert-file|-cf             Signer's certificate file.
 */
public class HashSigner extends PkiExpressOperator {
	private String toSignHash;
	private String algorithm;
	private String certThumb;
	private Path pkcs12Path;
	private String certPassword;
	private boolean useMachine;
	private String keyName;
	private Path certFilePath;

	public HashSigner(PkiExpressConfig config) {
		super(config);
	}

	public HashSigner() throws IOException {
		this(new PkiExpressConfig());
	}

	public String getToSignHash() {
		return toSignHash;
	}

	public void setToSignHash(String toSignHash) {
		this.toSignHash = toSignHash;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getCertThumb() {
		return certThumb;
	}

	public void setCertThumb(String certThumb) {
		this.certThumb = certThumb;
	}

	public Path getPkcs12Path() {
		return pkcs12Path;
	}

	//region setPkcs12
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

	public String getCertPassword() {
		return certPassword;
	}

	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}

	public boolean isUseMachine() {
		return useMachine;
	}

	public void setUseMachine(boolean useMachine) {
		this.useMachine = useMachine;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Path getCertFilePath() {
		return certFilePath;
	}

	//region setCertFile
	public void setCertFile(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided certificate file was not found");
		}
		this.certFilePath = path;
	}

	public void setCertFile(String path) throws IOException {
		setCertFile(path != null ? Paths.get(path) : null);
	}
	//endregion

	/**
	 * Performs the signature algorithm computation on the to-sign-hash.
	 * Uses the toSignHash field that was previously set.
	 * 
	 * @return The signature bytes
	 * @throws IOException if an error occurs during the operation
	 */
	public byte[] sign() throws IOException {
		return sign(this.toSignHash);
	}

	/**
	 * Performs the signature algorithm computation on the provided to-sign-hash.
	 * 
	 * @param toSignHash The hash to be used as input on the signature algorithm computation (hex or Base64)
	 * @return The signature bytes
	 * @throws IOException if an error occurs during the operation
	 */
	public byte[] sign(String toSignHash) throws IOException {
		if (toSignHash == null || toSignHash.trim().isEmpty()) {
			throw new RuntimeException("The \"toSignHash\" argument was not set");
		}

		// Verify that at least one certificate store option is provided
		if (certThumb == null && pkcs12Path == null && !useMachine && keyName == null && certFilePath == null) {
			throw new RuntimeException("No certificate store option was provided. Please provide one of: thumbprint, PKCS #12 file, machine store, Azure Key Vault key name, or certificate file");
		}

		List<String> args = new ArrayList<>();

		// Add the to-sign-hash argument (first positional argument)
		args.add(toSignHash);

		// Add algorithm option if provided (--algorithm|-a)
		// If omitted, the algorithm is inferred from the size of the hash
		if (algorithm != null && !algorithm.trim().isEmpty()) {
			args.add("--algorithm");
			args.add(algorithm);
		}

		// Add certificate store options
		// --thumbprint|-t <hex>
		if (certThumb != null) {
			args.add("--thumbprint");
			args.add(certThumb);
			versionManager.requireVersion(new Version("1.3"));
		}

		// --pkcs12|-p12
		if (pkcs12Path != null) {
			args.add("--pkcs12");
			args.add(pkcs12Path.toString());
			versionManager.requireVersion(new Version("1.3"));
		}

		// --password|-pw <pass>
		if (certPassword != null) {
			args.add("--password");
			args.add(certPassword);
			versionManager.requireVersion(new Version("1.3"));
		}

		// --machine|-m
		if (useMachine) {
			args.add("--machine");
			versionManager.requireVersion(new Version("1.3"));
		}

		// --key-name|-kn <key-name>
		if (keyName != null) {
			args.add("--key-name");
			args.add(keyName);
			// Azure Key Vault support typically requires a newer version
			versionManager.requireVersion(new Version("1.20"));
		}

		// --cert-file|-cf
		if (certFilePath != null) {
			args.add("--cert-file");
			args.add(certFilePath.toString());
			versionManager.requireVersion(new Version("1.3"));
		}

		// Invoke command: pkie sign-hash <to-sign-hash> [options]
		OperatorResult result = invoke(CommandEnum.CommandSignHash, args);

		// Parse output and return signature (base64 decoded)
		byte[] output = Base64.getDecoder().decode(result.getOutput()[0]);
		return output;
	}
}
