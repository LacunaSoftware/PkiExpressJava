package com.lacunasoftware.pkiexpress;

public class PkiArgentinaCertificateFields {

	private String cuil;
	private String cuit;

	PkiArgentinaCertificateFields(PkiArgentinaCertificateModel model) {
		this.cuil = model.getCuil();
		this.cuit = model.getCuit();
	}

	public String getCuil() {
		return this.cuil;
	}

	public String getCuit() {
		return this.cuit;
	}
}
