package com.lacunasoftware.pkiexpress;


public class KeyGenerationResult {
	private String key;
	private String csr;


	KeyGenerationResult(KeyGenerationResultModel model) {
		this.key = model.getKey();
		this.csr = model.getCsr();
	}


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCsr() {
		return csr;
	}

	public void setCsr(String csr) {
		this.csr = csr;
	}
}
