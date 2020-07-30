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


public class CertificateReader extends PkiExpressOperator {
	private Path certPath;

	public CertificateReader(PkiExpressConfig config) {
		super(config);
	}

	public CertificateReader() throws IOException {
		this(new PkiExpressConfig());
	}


	// region setCert

	public void setCert(InputStream inputStream) throws IOException {
		this.certPath = writeToTempFile(inputStream);
	}

	public void setCert(byte[] content) throws IOException {
		setCert(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setCert(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided certificate was not found");
		}
		this.certPath = path;
	}

	public void setCert(String path) throws IOException {
		setCert(path != null ? Paths.get(path) : null);
	}

	// endregion

	public PKCertificate decode() throws IOException {
		if (certPath == null) {
			throw new RuntimeException("No certificate was provided");
		}

		List<String> args = new ArrayList<String>();
		if (certPath != null) {
			args.add("--file");
			args.add(certPath.toString());
		}

		// Invoke command.
		OperatorResult result = invoke(CommandEnum.CommandReadCert, args);

		// Parse output and return model.
		CertificateModel certificateModel = parseOutput(result.getOutput()[0], CertificateModel.class);
		return new PKCertificate(certificateModel);
	}

}
