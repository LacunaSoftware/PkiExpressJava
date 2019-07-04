package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DigestAlgorithmAndValueModel {
	public enum AlgorithmEnum {
		MD5, SHA1, SHA256, SHA384, SHA512
	}


	private AlgorithmEnum algorithm = null;
	private String value = null;
	private String hexValue = null;


	@JsonProperty("algorithm")
	public AlgorithmEnum getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(AlgorithmEnum algorithm) {
		this.algorithm = algorithm;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("hexValue")
	public String getHexValue() {
		return hexValue;
	}
	public void setHexValue(String hexValue) {
		this.hexValue = hexValue;
	}
}
