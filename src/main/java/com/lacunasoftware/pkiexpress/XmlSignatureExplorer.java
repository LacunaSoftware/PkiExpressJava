package com.lacunasoftware.pkiexpress;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XmlSignatureExplorer extends SignatureExplorer {
	private boolean validate;
	private XmlPoliciesForValidation policy;

	public XmlSignatureExplorer(PkiExpressConfig config) {
		super(config);
	}

	public XmlSignatureExplorer() throws IOException {
		this(new PkiExpressConfig());
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public void setPolicy(XmlPoliciesForValidation policy) {
		this.policy = policy;
	}

	public List<XmlSignatureModel> open() throws IOException {

		if (signatureFilePath == null) {
			throw new RuntimeException("The provided signature file was not found");
		}

		List<String> args = new ArrayList<String>();
		args.add(signatureFilePath.toString());

		// Verify and add common options
		verifyAndAddCommonOptions(args);

		if (validate) {
			args.add("--validate");
		}

		// Set signature policy.
		if (policy != null) {
			args.add("--policy");
			args.add(policy.getValue());
		}

		// Invoke command.
		OperatorResult result = invoke(CommandEnum.CommandOpenXml, args);

		// Parse output and return model.
		OpenXmlCommandResult resultModel = parseOutput(result.getOutput()[0], OpenXmlCommandResult.class);
		return resultModel.getSigners();
	}
} 