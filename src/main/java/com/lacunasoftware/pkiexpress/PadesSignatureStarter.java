package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PadesSignatureStarter extends SignatureStarter {
	private Path pdfToSignPath;
	private Path vrJsonPath;
	private boolean suppressDefaultVisualRepresentation = false;
	private String reason;
	private String customSignatureFieldName;


	public PadesSignatureStarter(PkiExpressConfig config) {
		super(config);
	}

	public PadesSignatureStarter() throws IOException {
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

	public boolean isSuppressDefaultVisualRepresentation() {
		return suppressDefaultVisualRepresentation;
	}

	public void setSuppressDefaultVisualRepresentation(boolean suppressDefaultVisualRepresentation) {
		this.suppressDefaultVisualRepresentation = suppressDefaultVisualRepresentation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCustomSignatureFieldName() {
		return customSignatureFieldName;
	}

	public void setCustomSignatureFieldName(String customSignatureFieldName) {
		this.customSignatureFieldName = customSignatureFieldName;
	}

	public SignatureStartResult start() throws IOException {

		if (pdfToSignPath == null) {
			throw new RuntimeException("The PDF to be signed was not set");
		}

		if (certificatePath == null) {
			throw new RuntimeException("The certificate was not set");
		}

		// Generate transfer file
		String transferFile = getTransferFileName();

		List<String> args = new ArrayList<String>();
		args.add(pdfToSignPath.toString());
		args.add(certificatePath.toString());
		args.add(config.getTransferDataFolder().resolve(transferFile).toString());

		// Verify and add common options between signers.
		verifyAndAddCommonOptions(args);

		if (vrJsonPath != null) {
			args.add("--visual-rep");
			args.add(vrJsonPath.toString());
		}

		if (customSignatureFieldName != null) {
			args.add("--custom-signature-field-name");
			args.add(customSignatureFieldName);
		}

		if (!Util.isNullOrEmpty(reason)) {
			args.add("--reason");
			args.add(reason);

			// This option can only be used on versions greater than 1.13.0 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.13.0"));
		}

		if (suppressDefaultVisualRepresentation) {
			args.add("--suppress-default-visual-rep");

			// This option can only be used on versions greater than 1.13.1 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.13.1"));
		}

		// Invoke command with plain text output (to support PKI Express < 1.3)
		String[] response = invokePlain(CommandEnum.CommandStartPades, args);

		// Parse output
		return getResult(response, transferFile);
	}
}
