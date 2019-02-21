package com.lacunasoftware.pkiexpress;


public class PkiItalyCertificateModel {
	public enum CertificateTypeEnum {
		Undefined, Cns, DigitalSignature,
	}


	private CertificateTypeEnum certificateType = null;
	private String codiceFiscale = null;
	private String idCarta = null;


	public CertificateTypeEnum getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(CertificateTypeEnum certificateType) {
		this.certificateType = certificateType;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(String idCarta) {
		this.idCarta = idCarta;
	}
}
