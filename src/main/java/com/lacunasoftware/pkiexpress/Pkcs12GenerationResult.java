package com.lacunasoftware.pkiexpress;


public class Pkcs12GenerationResult {
	private byte[] pfxContent;


	Pkcs12GenerationResult(Pkcs12GenerationResultModel model) {
		this.pfxContent = Util.decodeBase64(model.getPfx());
	}


	public byte[] getPfxContent() {
		return pfxContent;
	}

	public void setPfxContent(byte[] pfxContent) {
		this.pfxContent = pfxContent;
	}

	public String getPfxContentBase64() {
		return Util.encodeBase64(pfxContent);
	}
}
