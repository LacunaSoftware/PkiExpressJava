package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PkiItalyCertificateModel {
	public enum CertificateTypeEnum {
		Undefined, Cns, DigitalSignature,
	}


	private CertificateTypeEnum certificateType = null;
	private String codiceFiscale = null;
	private String idCarta = null;

	@JsonProperty("certificateType")
	public CertificateTypeEnum getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(CertificateTypeEnum certificateType) {
		this.certificateType = certificateType;
	}

	@JsonProperty("codiceFiscale")
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@JsonProperty("idCarta")
	public String getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(String idCarta) {
		this.idCarta = idCarta;
	}
}
