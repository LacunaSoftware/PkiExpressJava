package com.lacunasoftware.pkiexpress;


import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Authentication extends PkiExpressOperator {
	private String nonce;
	private Path certificatePath;
	private String signature;
	private boolean useExternalStorage = false;


	public Authentication(PkiExpressConfig config) {
		super(config);
	}

	public Authentication() throws IOException {
		this(new PkiExpressConfig());
	}


	public void setNonce(String nonceBase64) {
		try {
			Util.decodeBase64(nonceBase64);
		} catch (Exception ex) {
			throw new RuntimeException("The provided nonce was not valid");
		}

		this.nonce = nonceBase64;
	}

	//region setCertificate

	public void setCertificate(InputStream inputStream) throws IOException {
		this.certificatePath = writeToTempFile(inputStream);
	}

	public void setCertificate(byte[] content) throws IOException {
		setCertificate(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setCertificate(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided certificate was not found");
		}

		this.certificatePath = path;
	}

	public void setCertificate(String path) throws IOException {
		setCertificate(Paths.get(path));
	}

	public void setCertificateBase64(String contentBase64) throws IOException {
		byte[] contentRaw = Util.decodeBase64(contentBase64);
		setCertificate(contentRaw);
	}

	//endregion

	public void setSignature(String signatureBase64) {
		try {
			Util.decodeBase64(signatureBase64);
		} catch (Exception ex) {
			throw new RuntimeException("The provided signature was not valid");
		}

		this.signature = signatureBase64;
	}

	public void setExternalStorage(boolean useExternalStorage) {
		this.useExternalStorage = useExternalStorage;
	}

	public AuthStartResult start() throws IOException {

		List<String> args = new ArrayList<String>();

		// The option "use external storage" is used to ignore the PKI Express's nonce
		// verification, to make a own nonce store and nonce verification.
		if (!useExternalStorage) {
			args.add("--nonce-store");
			args.add(config.getTransferDataFolder().toString());
			// This option can only be used on versions greater than 1.4 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.4"));
		}

		// This operation can only be used on versions greater than 1.4 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.4"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandStartAuth, args);

		// Parse output and return result.
		AuthStartModel model = parseOutput(response.getOutput()[0], AuthStartModel.class);
		return new AuthStartResult(model);
	}

	public AuthCompleteResult complete() throws IOException {

		if (nonce == null) {
			throw new RuntimeException("The nonce was not set");
		}

		if (certificatePath == null) {
			throw new RuntimeException("The certificate file was not set");
		}

		if (signature == null) {
			throw new RuntimeException("The signature was not set");
		}

		List<String> args = new ArrayList<String>();
		args.add(nonce);
		args.add(certificatePath.toString());
		args.add(signature);

		// The option "use external storage" is used to ignore the PKI Express's nonce
		// verification, to make a own nonce store and nonce verification.
		if (!useExternalStorage) {
			args.add("--nonce-store");
			args.add(config.getTransferDataFolder().toString());
			// This option can only be used on versions greater than 1.4 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.4"));
		}

		// This operation can only be used on versions greater than 1.4 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.4"));

		// Invoke command.
		OperatorResult response = invoke(CommandEnum.CommandCompleteAuth, args);

		// Parse output and return model.
		AuthCompleteModel model = parseOutput(response.getOutput()[0], AuthCompleteModel.class);
		return new AuthCompleteResult(model);
	}
}
