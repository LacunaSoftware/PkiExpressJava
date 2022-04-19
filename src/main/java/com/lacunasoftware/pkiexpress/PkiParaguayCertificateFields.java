package com.lacunasoftware.pkiexpress;

public class PkiParaguayCertificateFields {

	public enum CertificateTypes {
		Unknown, F1, F2, C1, C2, F3, C3;
	}

	public enum PersonCertificateTypes {
		Unknown, PersonaFisica, PersonaJuridica, Aplicacion;
	}

	private PersonCertificateTypes personCertificateType;
	private CertificateTypes certificateType;
	private String ci;
	private String cie;
	private String pasaporte;
	private String ruc;
	private String responsable;

	PkiParaguayCertificateFields(PkiParaguayCertificateModel model) {
		this.personCertificateType = PersonCertificateTypes.valueOf(model.getPersonCertificateType().toString());
		this.certificateType = CertificateTypes.valueOf(model.getCertificateType().toString());
		this.ci = model.getCi();
		this.cie = model.getCie();
		this.pasaporte = model.getPasaporte();
		this.ruc = model.getRuc();
		this.responsable = model.getResponsable();
	}

	public PersonCertificateTypes getPersonCertificateType() {
		return this.personCertificateType;
	}

	public CertificateTypes getCertificateType() {
		return this.certificateType;
	}

	public String getCi() {
		return this.ci;
	}

	public String getCie() {
		return this.cie;
	}

	public String getPasaporte() {
		return this.pasaporte;
	}

	public String getRuc() {
		return this.ruc;
	}

	public String getResponsable() {
		return this.responsable;
	}

}
