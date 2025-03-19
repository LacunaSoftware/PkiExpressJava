package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SignatureAlgorithmAndValueModel {
	private SignatureAlgorithm algorithm = null;
	private String value = null;
	private String hexValue = null;


	@JsonProperty("algorithmIdentifier")
	public SignatureAlgorithm getAlgorithm() {
		return algorithm;
	}
	
	public void setAlgorithm(SignatureAlgorithm algorithm) {
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
