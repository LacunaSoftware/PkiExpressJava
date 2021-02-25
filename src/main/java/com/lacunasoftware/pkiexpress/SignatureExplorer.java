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


public class SignatureExplorer extends PkiExpressOperator {
	protected Path signatureFilePath;
	private boolean validate;
	private boolean fillCertContent;
	private boolean trustUncertifiedSigningTime;

	public SignatureExplorer(PkiExpressConfig config) {
		super(config);
	}


	protected void verifyAndAddCommonOptions(List<String> args) {
		if (validate) {
			args.add("--validate");
			// This operation can only be used on versions greater than 1.3 of the PKI Express.
			this.versionManager.requireVersion(new Version("1.3"));
		}
		
		if (trustUncertifiedSigningTime) {
			args.add("--trust-uncertified-signing-time");
			// This policy can only be used on version greater than 1.23 of the PKI Express.
			versionManager.requireVersion(new Version("1.23"));
		}

		if (fillCertContent) {
			args.add("--fill-cert-content");
			// This policy can only be used on version greater than 1.23 of the PKI Express.
			versionManager.requireVersion(new Version("1.23"));
		}
	}

	//region setSignatureFile
	public void setSignatureFile(InputStream inputStream) throws IOException {
		this.signatureFilePath = writeToTempFile(inputStream);
	}

	public void setSignatureFile(byte[] content) throws IOException {
		setSignatureFile(new ByteArrayInputStream(content, 0, content.length));
	}

	public void setSignatureFile(Path path) throws IOException {
		if (!Files.exists(path)) {
			throw new FileNotFoundException("The provided file to be signed was not found");
		}

		this.signatureFilePath = path;
	}

	public void setSignatureFile(String path) throws IOException {
		setSignatureFile(path != null ? Paths.get(path) : null);
	}
	//endregion

	public boolean getValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public void setTrustUncertifiedSigningTime(boolean trust){
		this.trustUncertifiedSigningTime = trust;
	}
	public void setFillCertContent(boolean fillCertContent){
		this.fillCertContent = fillCertContent;
	}

	public boolean getTrustUncertifiedSigningTime(){
		return trustUncertifiedSigningTime;
	}
	public boolean getFillCertContent(){
		return fillCertContent;
	}
}
