package com.lacunasoftware.pkiexpress;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CadesSigner extends Signer {
	private Path fileToSignPath;
	private Path dataFilePath;

	@Deprecated
	public Boolean encapsulateContent = true;


	public CadesSigner(PkiExpressConfig config) {
		super(config);
	}

	public CadesSigner() throws IOException {
		this(new PkiExpressConfig());
	}


	//region setFileToSign
	public void setFileToSign(InputStream inputStream) throws IOException {
		this.fileToSignPath = writeToTempFile(inputStream);
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
		setFileToSign(path != null ? Paths.get(path) : null);
	}
	//endregion

	//region setDatafile
	public void setDataFile(InputStream inputStream) throws IOException {
		this.dataFilePath = writeToTempFile(inputStream);
	}

	public void setDataFile(byte[] content) throws IOException {
		setDataFile(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setDataFile(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided file to be signed was not found");
		}

		this.dataFilePath = path;
	}

	public void setDataFile(String path) throws IOException {
		setDataFile(path != null ? Paths.get(path) : null);
	}
	//endregion

	public Boolean getEncapsulateContent() {
		return encapsulateContent;
	}

	public void setEncapsulateContent(Boolean encapsulateContent) {
		this.encapsulateContent = encapsulateContent;
	}

	public void sign() throws IOException {

		if (fileToSignPath == null) {
			throw new RuntimeException("The file to be signed was not set");
		}

		if (outputFilePath == null) {
			throw new RuntimeException("The output destination was not set");
		}

		List<String> args = new ArrayList<String>();
		args.add(fileToSignPath.toString());
		args.add(outputFilePath.toString());

		// Verify and add common options between signers
		verifyAndAddCommonOptions(args);

		if (dataFilePath != null) {
			args.add("--data-file");
			args.add(dataFilePath.toString());
		}

		if (!encapsulateContent) {
			args.add("--detached");
		}

		// Invoke command with plain text output (to support PKI Express < 1.3)
		invokePlain(CommandEnum.CommandSignCades, args);
	}
}
