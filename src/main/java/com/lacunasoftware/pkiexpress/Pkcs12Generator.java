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


public class Pkcs12Generator extends PkiExpressOperator {
	private String key;
	private Path certFilePath;
	private String password;


	public Pkcs12Generator(PkiExpressConfig config) {
		super(config);
	}

	public Pkcs12Generator() throws IOException {
		this(new PkiExpressConfig());
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Path getCertFilePath() {
		return certFilePath;
	}

	//region setCertFile
	public void setCertFile(InputStream inputStream) throws IOException {
		this.certFilePath = writeToTempFile(inputStream);
	}

	public void setCertFile(byte[] content) throws IOException {
		setCertFile(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setCertFile(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided file to be signed was not found");
		}

		this.certFilePath = path;
	}

	public void setCertFile(String path) throws IOException {
		setCertFile(path != null ? Paths.get(path) : null);
	}
	//endregion

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Pkcs12GenerationResult generate() throws IOException {
		return generate(this.password);
	}

	public Pkcs12GenerationResult generate(String password) throws IOException {
		if (this.key == null) {
			throw new RuntimeException("The generated key was not set");
		}

		if (this.certFilePath == null) {
			throw new RuntimeException("The certificate file was not set");
		}

		List<String> args = new ArrayList<>();
		args.add(this.key);
		args.add(this.certFilePath.toString());

		if (this.password != null) {
			args.add("--password");
			args.add(this.password);
		}

		// This operation can only be used on version greater than 1.11 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.11"));

		// Invoke command.
		OperatorResult result = this.invoke(CommandEnum.CommandCreatePfx, args);

		// Parse output and return model.
		Pkcs12GenerationResultModel resultModel = parseOutput(result.getOutput()[0], Pkcs12GenerationResultModel.class);
		return new Pkcs12GenerationResult(resultModel);
	}
}
