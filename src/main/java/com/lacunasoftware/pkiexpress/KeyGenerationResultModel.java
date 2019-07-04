package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyGenerationResultModel {
	private String key;
	private String csr;


	@JsonProperty("key")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("csr")
	public String getCsr() {
		return csr;
	}
	public void setCsr(String csr) {
		this.csr = csr;
	}
}
