package com.lacunasoftware.pkiexpress;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class XmlSigner extends Signer {
	private Path xmlToSignPath;
	private String toSignElementId;
	private String signatureElementInsertion;
	private boolean useClassicEnvelopedTransform;


	public XmlSigner(PkiExpressConfig config) {
		super(config);
	}

	public XmlSigner() throws IOException {
		this(new PkiExpressConfig());
	}


	//region setXmlToSign
	public void setXmlToSign(InputStream inputStream) throws IOException {
		this.xmlToSignPath = writeToTempFile(inputStream);
	}

	public void setXmlToSign(byte[] content) throws IOException {
		setXmlToSign(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setXmlToSign(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided file to be signed was not found");
		}

		this.xmlToSignPath = path;
	}

	public void setXmlToSign(String path) throws IOException {
		setXmlToSign(path != null ? Paths.get(path) : null);
	}
	//endregion

	public void setToSignElementId(String toSignElementId) {
		this.toSignElementId = toSignElementId;
	}

	public void setSignatureElementInsertion(XmlElementInsertion insertion) {
		this.signatureElementInsertion = insertion.getValue();
	}

	public void setUseClassicEnvelopedTransform(boolean useClassicEnvelopedTransform) {
		this.useClassicEnvelopedTransform = useClassicEnvelopedTransform;
	}

	public PKCertificate sign() throws IOException {
		return sign(false);
	}

	public PKCertificate sign(boolean getCert) throws IOException {

		if (xmlToSignPath == null) {
			throw new RuntimeException("The file to be signed was not set");
		}

		if (outputFilePath == null) {
			throw new RuntimeException("The output destination was not set");
		}

		List<String> args = new ArrayList<String>();
		args.add(xmlToSignPath.toString());
		args.add(outputFilePath.toString());

		// Verify and add common options between signers
		verifyAndAddCommonOptions(args);

		if (!Util.isNullOrEmpty(toSignElementId)) {
			args.add("--element-id");
			args.add(toSignElementId);
		}

		if (!Util.isNullOrEmpty(this.signatureElementInsertion)) {
			args.add("--sig-element-insertion");
			args.add(this.signatureElementInsertion);
			this.versionManager.requireVersion(new Version("1.26.0"));
		}

		if (this.useClassicEnvelopedTransform) {
			args.add("--classic-enveloped-transform");
			this.versionManager.requireVersion(new Version("1.27.0"));
		}

		if (getCert) {
			// This operation can only be used on version greater than 1.8 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.8"));

			// Invoke command.
			OperatorResult result = invoke(CommandEnum.CommandSignXml, args);

			// Parse output and return model.
			SignatureResult resultModel = parseOutput(result.getOutput()[0], SignatureResult.class);
			return new PKCertificate(resultModel.getSigner());
		}

		// Invoke command with plain text output (to support PKI Express < 1.3)
		invokePlain(CommandEnum.CommandSignXml, args);
		return null;
	}
}
