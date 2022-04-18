package com.lacunasoftware.pkiexpress;

public class PkiEcuadorCertificateFields {

	public enum CertificateTypes {
		Unknown, PersonaNatural, PersonaJuridica;
	}

	private CertificateTypes certificateType;
	private String cedulaDeIdentidad;
	private String pasaporte;
	private String rup;
	private String ruc;
	private String nombres;
	private String apellidos;
	private String razonSocial;

	PkiEcuadorCertificateFields(PkiEcuadorCertificateModel model) {
		this.certificateType = CertificateTypes.valueOf(model.getCertificateType().toString());
		this.cedulaDeIdentidad = model.getCedulaDeIdentidad();
		this.pasaporte = model.getPasaporte();
		this.rup = model.getRup();
		this.ruc = model.getRuc();
		this.nombres = model.getNombres();
		this.apellidos = model.getApellidos();
		this.razonSocial = model.getRazonSocial();
	}

	public CertificateTypes getCertificateType() {
		return this.certificateType;
	}

	public String getCedulaDeIdentidad() {
		return this.cedulaDeIdentidad;
	}

	public String getPasaporte() {
		return this.pasaporte;
	}

	public String getRup() {
		return this.rup;
	}

	public String getRuc() {
		return this.ruc;
	}

	public String getNombres() {
		return this.nombres;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}
}
