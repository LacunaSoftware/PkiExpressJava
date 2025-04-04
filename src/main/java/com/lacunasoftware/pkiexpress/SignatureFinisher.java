package com.lacunasoftware.pkiexpress;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class SignatureFinisher extends PkiExpressOperator {
	private Path fileToSignPath;
	private String transferFileId;
	private Path dataFilePath;
	private Path outputFilePath;
	private String signature;


	public SignatureFinisher(PkiExpressConfig config) {
		super(config);
	}

	public SignatureFinisher() throws IOException {
		this(new PkiExpressConfig());
	}


	//region setFileToSign
	public void setFileToSign(InputStream inputStream) throws IOException {
		byte[] buff = new byte[1024];
		Path tempPath = createTempFile();
		OutputStream outputStream = new FileOutputStream(tempPath.toFile());

		int nRead;
		while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
			outputStream.write(buff, 0, nRead);
		}
		outputStream.close();

		this.fileToSignPath = tempPath;
	}

	public void setFileToSign(byte[] content) throws IOException {
		setFileToSign(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setFileToSign(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided file to be signed was not found");
		}

		this.fileToSignPath = path;
	}

	public void setFileToSign(String path) throws IOException {
		setFileToSign(Paths.get(path));
	}
	//endregion

	//region setDataFile
	public void setDataFile(InputStream inputStream) throws IOException {
		byte[] buff = new byte[1024];
		Path tempPath = createTempFile();
		OutputStream outputStream = new FileOutputStream(tempPath.toFile());

		int nRead;
		while ((nRead = inputStream.read(buff, 0, buff.length)) != -1) {
			outputStream.write(buff, 0, nRead);
		}
		outputStream.close();

		this.dataFilePath = tempPath;
	}

	public void setDataFile(byte[] content) throws IOException {
		setDataFile(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setDataFile(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided data file was not found");
		}

		this.dataFilePath = path;
	}

	public void setDataFile(String path) throws IOException {
		setDataFile(Paths.get(path));
	}
	//endregion

	//region setTransferFilePath

	/**
	 * Set transfer file id to be used when finalizing the signature.
	 * @param path the transfer file id
	 *
	 * @deprecated Use setTransferFileId() instead.
	 */
	@Deprecated
	public void setTransferFilePath(Path path) {
		setTransferFileId(path.toString());
	}

	/**
	 * Set transfer file id to be used when finalizing the signature.
	 * @param path the transfer file id.
	 *
	 * @deprecated Use setTransferFileId() instead.
	 */
	@Deprecated
	public void setTransferFilePath(String path) {
		setTransferFileId(path);
	}

	public void setTransferFileId(String transferFileId) {
		Util.validateFile(transferFileId, config.getTransferDataFolder());
		this.transferFileId = transferFileId;
	}
	//endregion

	public void setSignature(String signature) {
		try {
			Util.decodeBase64(signature);
		} catch (Exception ex) {
			throw new RuntimeException("The provided signature was not valid");
		}

		this.signature = signature;
	}

	//region setOutputFilePath
	public void setOutputFilePath(Path path) {
		this.outputFilePath = path;
	}

	public void setOutputFilePath(String path) {
		setOutputFilePath(Paths.get(path));
	}
	//endregion

	public PKCertificate complete() throws IOException {
		return complete(false);
	}

	public PKCertificate complete(boolean getCert) throws IOException {

		if (fileToSignPath == null) {
			throw new RuntimeException("The file to be signed was not set");
		}

		if (transferFileId == null) {
			throw new RuntimeException("The transfer data file was not set");
		}

		if (signature == null) {
			throw new RuntimeException("The signature was not set");
		}

		if (outputFilePath == null) {
			throw new RuntimeException("The output destination was not set");
		}

		List<String> args = new ArrayList<String>();
		args.add(fileToSignPath.toString());
		args.add(config.getTransferDataFolder().resolve(transferFileId).toString());
		args.add(signature);
		args.add(outputFilePath.toString());

		if (dataFilePath != null) {
			args.add("--data-file");
			args.add(dataFilePath.toString());
		}

		if (getCert) {
			// This operation can only be used on version greater than 1.8 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.8"));

			// Invoke command.
			OperatorResult result = invoke(CommandEnum.CommandCompleteSig, args);

			// Parse output and return model
			SignatureResult resultModel = parseOutput(result.getOutput()[0], SignatureResult.class);
			return new PKCertificate(resultModel.getSigner());
		}

		// Invoke command with plain text output (to support PKI Express < 1.3)
		invokePlain(CommandEnum.CommandCompleteSig, args);
		return null;
	}
}
