package com.lacunasoftware.pkiexpress;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XadesSignatureExplorer extends SignatureExplorer {

	public XadesSignatureExplorer(PkiExpressConfig config) {
		super(config);
	}

	public XadesSignatureExplorer() throws IOException {
		this(new PkiExpressConfig());
	}

	public XadesSignature open() throws IOException {

		if (signatureFilePath == null) {
			throw new RuntimeException("The provided signature file was not found");
		}

		List<String> args = new ArrayList<String>();
		args.add(signatureFilePath.toString());

		// Verify and add common options
		verifyAndAddCommonOptions(args);

		// Invoke command.
		OperatorResult result = invoke(CommandEnum.CommandOpenXades, args);

		// Parse output and return model.
		XadesSignatureModel resultModel = parseOutput(result.getOutput()[0], XadesSignatureModel.class);
		return new XadesSignature(resultModel);
	}
} 