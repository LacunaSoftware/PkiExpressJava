package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PkiArgentinaCertificateModel {

	private String cuil;
	private String cuit;

	@JsonProperty("cuil")
	public String getCuil() {
		return this.cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	@JsonProperty("cuit")
	public String getCuit() {
		return this.cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
