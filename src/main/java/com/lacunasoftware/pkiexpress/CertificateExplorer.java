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


public class CertificateExplorer extends PkiExpressOperator {
	private Path certificatePath;
	private boolean validate;

	public CertificateExplorer(PkiExpressConfig config) {
		super(config);
	}

	public CertificateExplorer() throws IOException {
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

	// endregion

	// region validate

	public boolean getValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	// endregion

	public CertificateExplorerResult open() throws IOException {
		if (certificatePath == null) {
			throw new RuntimeException("The certificate file was not set");
		}

		List<String> args = new ArrayList<String>();
		args.add("--file");
		args.add(certificatePath.toString());

		if (validate) {
			args.add("--validate");
		}

		// This operation can only be used on versions greater than 1.20 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.20"));

		// Invoke command
		OperatorResult result = invoke(CommandEnum.CommandOpenCertificate, args);
		
		// Parse output and return model
		CertificateExplorerModel resultModel = parseOutput(result.getOutput()[0], CertificateExplorerModel.class);
		return new CertificateExplorerResult(resultModel);
	}
}
