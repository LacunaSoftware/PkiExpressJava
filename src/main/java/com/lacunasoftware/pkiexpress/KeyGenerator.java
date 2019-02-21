package com.lacunasoftware.pkiexpress;


import java.io.IOException;
import java.util.*;


public class KeyGenerator extends PkiExpressOperator {
	private int keySize = 2048;
	private KeyFormats keyFormat = KeyFormats.JSON;
	private boolean genCsr = false;

	private final int[] SUPPORTED_KEY_SIZES = {1024, 2048, 4096};


	public KeyGenerator(PkiExpressConfig config) {
		super(config);
	}

	public KeyGenerator() throws IOException {
		this(new PkiExpressConfig());
	}


	public int getKeySize() {
		return keySize;
	}

	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}

	public KeyFormats getKeyFormat() {
		return keyFormat;
	}

	public void setKeyFormat(KeyFormats keyFormat) {
		this.keyFormat = keyFormat;
	}

	public boolean isGenCsr() {
		return genCsr;
	}

	public void setGenCsr(boolean genCsr) {
		this.genCsr = genCsr;
	}

	public KeyGenerationResult generate() throws IOException {
		return generate(this.keyFormat);
	}

	public KeyGenerationResult generate(KeyFormats keyFormat) throws IOException {

		List<String> args = new ArrayList<>();
		if (!supportsKeySize(this.keySize)) {
			throw new IllegalStateException(String.format("Unsupported key size: %d", keySize));
		}
		args.add("--size");
		args.add(String.valueOf(this.keySize));

		if (this.keyFormat != null) {
			args.add("--format");
			args.add(keyFormat.getValue());
		}

		if (this.genCsr) {
			args.add("--gen-csr");
		}

		// This operation can only be used on version greater then 1.11 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.11"));

		// Invoke command.
		OperatorResult result = this.invoke(CommandEnum.CommandGenKey, args);

		// Parse output and return model.
		KeyGenerationResultModel resultModel = parseOutput(result.getOutput()[0], KeyGenerationResultModel.class);
		return new KeyGenerationResult(resultModel);
	}

	private boolean supportsKeySize(int keySize) {
		boolean supports = false;
		for (int value : SUPPORTED_KEY_SIZES) {
			if (value == keySize) {
				supports = true;
				break;
			}
		}
		return supports;
	}
}
