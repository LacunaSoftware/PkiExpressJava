package com.lacunasoftware.pkiexpress;

public class PkiPeruCertificateFields {

	private String dni;
	private String ruc;

	PkiPeruCertificateFields(PkiPeruCertificateModel model) {
		this.dni = model.getDni();
		this.ruc = model.getRuc();
	}

	public String getDni() {
		return this.dni;
	}
	public String getRuc() {
		return this.ruc;
	}
}
