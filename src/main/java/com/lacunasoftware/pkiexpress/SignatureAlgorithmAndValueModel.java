package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SignatureAlgorithmAndValueModel {
	private SignatureAlgorithmIdentifier algorithmIdentifier = null;
	private String value = null;
	private String hexValue = null;


	@JsonProperty("algorithmIdentifier")
	public SignatureAlgorithmIdentifier getAlgorithmIdentifier() {
		return algorithmIdentifier;
	}
	public void setAlgorithmIdentifier(SignatureAlgorithmIdentifier algorithmIdentifier) {
		this.algorithmIdentifier = algorithmIdentifier;
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
