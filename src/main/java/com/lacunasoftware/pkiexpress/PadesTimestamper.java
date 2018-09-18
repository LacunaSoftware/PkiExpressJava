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

public class PadesTimestamper extends PkiExpressOperator {

	private Path pdfPath;
	private Path outputFilePath;
	private boolean overwriteOriginalFile = false;

	public PadesTimestamper(PkiExpressConfig config) {
		super(config);
	}

	public PadesTimestamper() throws IOException {
		this(new PkiExpressConfig());
	}

	public Path getPdfPath() {
		return pdfPath;
	}

	// region setPdf

	public void setPdf(InputStream inputStream) throws IOException {
		this.pdfPath = writeToTempFile(inputStream);
	}

	public void setPdf(byte[] content) throws IOException {
		setPdf(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setPdf(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided PDF was not found");
		}
		this.pdfPath = path;
	}

	public void setPdf(String path) throws IOException {
		setPdf(path != null ? Paths.get(path) : null);
	}

	// endregion

	public Path getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(Path path) {
		this.outputFilePath = path;
	}

	public void setOutputFilePath(String path) {
		setOutputFilePath(path != null ? Paths.get(path) : null);
	}

	public boolean getOverwriteOriginalFile() {
		return overwriteOriginalFile;
	}

	public void setOverwriteOriginalFile(boolean overwriteOriginalFile) {
		this.overwriteOriginalFile = overwriteOriginalFile;
	}

	public void stamp() throws IOException {

		if (pdfPath == null) {
			throw new RuntimeException("The PDF to be timestamped was not set");
		}

		if (!overwriteOriginalFile && outputFilePath == null) {
			throw new RuntimeException("The output destination was not set");
		}

		List<String> args = new ArrayList<>();
		args.add(pdfPath.toString());

		// Add timestamp authority.
		if (timestampAuthority != null) {
			args.addAll(timestampAuthority.getCmdArguments());

			// This operation can only be used on versions greater than 1.5 of the PKI Express.
			versionManager.requireVersion(new Version("1.5"));
		}

		// Logic to overwrite original file or use the output file.
		if (overwriteOriginalFile) {
			args.add("--overwrite");
		} else {
			args.add(outputFilePath.toString());
		}

		// This operation can only be used on versions greater than 1.7 of the PKI Express.
		versionManager.requireVersion(new Version("1.7"));

		// Invoke command.
		invoke(CommandEnum.CommandStampPdf, args);
	}
}
