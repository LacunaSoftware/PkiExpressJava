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


public class CadesSignatureEditor extends PkiExpressOperator {
	private Path outputFilePath;
	private Path dataFilePath;
	private Boolean encapsulateContent = true;
	private List<Path> cmsFiles;

	public CadesSignatureEditor(PkiExpressConfig config) {
		super(config);
		this.cmsFiles = new ArrayList<>();
	}

	public CadesSignatureEditor() throws IOException {
		this(new PkiExpressConfig());
	}

	//region setDataFile
	public void setDataFile(InputStream inputStream) throws IOException {
		this.dataFilePath = writeToTempFile(inputStream);
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
		setDataFile(path != null ? Paths.get(path) : null);
	}

	public void setDataFileBase64(String contentBase64) throws IOException {
		byte[] contentRaw = Util.decodeBase64(contentBase64);
		setDataFile(contentRaw);
	}
	//endregion

	public Path getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(Path path) {
		this.outputFilePath = path;
	}

	public void setOutputFilePath(String path) {
		setOutputFilePath(path != null ? Paths.get(path) : null);
	}

	public Boolean getEncapsulateContent() {
		return encapsulateContent;
	}

	public void setEncapsulateContent(Boolean encapsulateContent) {
		this.encapsulateContent = encapsulateContent;
	}

	public List<Path> getCmsFiles() {
		return this.cmsFiles;
	}

	public void addCmsFile(Path path) {
		if (!Files.exists(path)) {
			throw new RuntimeException("The provided CMS File was not found");
		}

		this.cmsFiles.add(path);
	}

	public void addCmsFile(String path) {
		addCmsFile(path != null ? Paths.get(path) : null);
	}

	public void addCmsFile(InputStream inputStream) throws IOException {
		addCmsFile(writeToTempFile(inputStream));
	}

	public void addCmsFile(byte[] content) throws IOException {
		addCmsFile(new ByteArrayInputStream(content, 0, content.length));
	}

	public void addCmsFileBase64(String contentBase64) throws IOException {
		byte[] contentRaw = Util.decodeBase64(contentBase64);
		addCmsFile(contentRaw);
	}

	public void merge() throws IOException {
		if (cmsFiles == null || cmsFiles.isEmpty()) {
			throw new RuntimeException("Insufficient CMS/CAdES files for merging. "
			+ "Provided at least one signature.");
		}

		if (outputFilePath == null){
			throw new RuntimeException("The output destination was not set");
		}

		List<String> args = new ArrayList<String>();
		args.add(outputFilePath.toString());

		if (cmsFiles.size() == 1){
			// This operation can only be used on versions greater than 1.18 of the
			// PKI Express.
			versionManager.requireVersion(new Version("1.18"));
		}

		for (Path cmsFile : cmsFiles) {
			args.add(cmsFile.toString());
		}

		if (dataFilePath != null) {
			args.add("--data-file");
			args.add(dataFilePath.toString());
		}

		if (!encapsulateContent) {
			args.add("--detached");
		}

		// This operation can only be used on versions greater than 1.9 of the
		// PKI Express.
		this.versionManager.requireVersion(new Version("1.9"));

		invoke(CommandEnum.CommandMergeCms, args);
	}
}