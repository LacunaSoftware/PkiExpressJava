package com.lacunasoftware.pkiexpress;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PadesSignatureExplorer extends SignatureExplorer {

	public PadesSignatureExplorer(PkiExpressConfig config) {
		super(config);
	}

	public PadesSignatureExplorer() throws IOException {
		this(new PkiExpressConfig());
	}


	public PadesSignature open() throws IOException {

		if (signatureFilePath == null) {
			throw new RuntimeException("The provided signature file was not found");
		}

		List<String> args = new ArrayList<String>();
		args.add(signatureFilePath.toString());

		// Verify and add common options
		verifyAndAddCommonOptions(args);

		// This operation can only be used on versions greater than 1.3 of the PKI Express.
		this.versionManager.requireVersion(new Version("1.3"));

		// Invoke command
		OperatorResult result = invoke(CommandEnum.CommandOpenPades, args);

		// Parse output and return model
		PadesSignatureModel resultModel = parseOutput(result.getOutput()[0], PadesSignatureModel.class);
		return new PadesSignature(resultModel);
	}
}
