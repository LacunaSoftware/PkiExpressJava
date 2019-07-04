package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Pkcs12GenerationResultModel {
	private String pfx = null;


	@JsonProperty("pfx")
	public String getPfx() {
		return pfx;
	}
	public void setPfx(String pfx) {
		this.pfx = pfx;
	}
}
