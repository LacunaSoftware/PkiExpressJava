package com.lacunasoftware.pkiexpress;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class SignatureStarter extends BaseSigner {
	protected Path certificatePath;


	public SignatureStarter(PkiExpressConfig config) {
		super(config);
	}

	public SignatureStarter() throws IOException {
		this(new PkiExpressConfig());
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

	protected SignatureStartResult getResult(String[] response, String transferFile) {
		SignatureStartResult startResult = new SignatureStartResult();
		startResult.setToSignHash(response[0]);
		startResult.setDigestAlgorithm(response[1]);
		startResult.setDigestAlgorithmOid(response[2]);
		startResult.setTransferFile(transferFile);
		return startResult;
	}
}
