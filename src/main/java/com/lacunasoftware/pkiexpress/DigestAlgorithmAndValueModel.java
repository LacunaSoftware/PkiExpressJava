package com.lacunasoftware.pkiexpress;


public class DigestAlgorithmAndValueModel {
	public enum AlgorithmEnum {
		MD5, SHA1, SHA256, SHA384, SHA512
	}


	private AlgorithmEnum algorithm = null;
	private String value = null;
	private String hexValue = null;


	public AlgorithmEnum getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(AlgorithmEnum algorithm) {
		this.algorithm = algorithm;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getHexValue() {
		return hexValue;
	}

	public void setHexValue(String hexValue) {
		this.hexValue = hexValue;
	}
}
