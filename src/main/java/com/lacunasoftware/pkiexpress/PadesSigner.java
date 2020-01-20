package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PadesSigner extends Signer {
	private Path pdfToSignPath;
	private Path vrJsonPath;
	private boolean suppressDefaultVisualRepresentation = false;

	@Deprecated
	public Boolean overwriteOriginalFile = false;


	public PadesSigner(PkiExpressConfig config) {
		super(config);
	}

	public PadesSigner() throws IOException {
		this(new PkiExpressConfig());
	}


	//region setPdfToSign
	public void setPdfToSign(InputStream inputStream) throws IOException {
		this.pdfToSignPath = writeToTempFile(inputStream);
	}

	public void setPdfToSign(byte[] content) throws IOException {
		setPdfToSign(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setPdfToSign(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided PDF to be signed was not found");
		}

		this.pdfToSignPath = path;
	}

	public void setPdfToSign(String path) throws IOException {
		setPdfToSign(path != null ? Paths.get(path) : null);
	}
	//endregion

	//region setVisualRepresentation
	public void setVisualRepresentationFromFile(InputStream inputStream) throws IOException {
		this.vrJsonPath = writeToTempFile(inputStream);
	}

	public void setVisualRepresentationFromFile(byte[] content) throws IOException {
		setVisualRepresentationFromFile(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setVisualRepresentationFromFile(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided visual representation file was not found");
		}
		this.vrJsonPath = path;
	}

	public void setVisualRepresentationFromFile(String path) throws IOException {
		setVisualRepresentationFromFile(path != null ? Paths.get(path) : null);
	}

	public void setVisualRepresentation(PadesVisualRepresentation visualRepresentation) throws IOException {
		Path tempPath = createTempFile();
		OutputStream outputStream = new FileOutputStream(tempPath.toFile());
		new ObjectMapper().writeValue(outputStream, visualRepresentation.toModel());
		outputStream.close();
		this.vrJsonPath = tempPath;
	}
	//endregion

	public Boolean getOverwriteOriginalFile() {
		return overwriteOriginalFile;
	}

	public void setOverwriteOriginalFile(Boolean overwriteOriginalFile) {
		this.overwriteOriginalFile = overwriteOriginalFile;
	}

	public boolean isSuppressDefaultVisualRepresentation() {
		return suppressDefaultVisualRepresentation;
	}

	public void setSuppressDefaultVisualRepresentation(boolean suppressDefaultVisualRepresentation) {
		this.suppressDefaultVisualRepresentation = suppressDefaultVisualRepresentation;
	}

	public PKCertificate sign() throws IOException {
		return sign(false);
	}
	public PKCertificate sign(boolean getCert) throws IOException {

		if (pdfToSignPath == null) {
			throw new RuntimeException("The PDF to be signed was not set");
		}

		if (!overwriteOriginalFile && outputFilePath == null) {
			throw new RuntimeException("The output destination was not set");
		}

		List<String> args = new ArrayList<String>();
		args.add(pdfToSignPath.toString());

		// Verify and add common options between signers
		verifyAndAddCommonOptions(args);

		// Logic to overwrite original file or use the output file
		if (overwriteOriginalFile) {
			args.add("--overwrite");
		} else {
			args.add(outputFilePath.toString());
		}

		if (vrJsonPath != null) {
			args.add("--visual-rep");
			args.add(vrJsonPath.toString());
		}

		if (suppressDefaultVisualRepresentation) {
			args.add("--suppress-default-visual-rep");

			// This option can only be used on versions greater than 1.13.1 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.13.1"));
		}

		if (getCert) {
			// This operation can only be used on version greater than 1.8 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.8"));

			// Invoke command.
			OperatorResult result = invoke(CommandEnum.CommandSignPades, args);

			// Parse output and return model.
			SignatureResult resultModel = parseOutput(result.getOutput()[0], SignatureResult.class);
			return new PKCertificate(resultModel.getSigner());
		}

		// Invoke command with plain text output (to support PKI Express < 1.3)
		invokePlain(CommandEnum.CommandSignPades, args);
		return null;
	}
}
