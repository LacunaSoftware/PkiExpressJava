package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PkiParaguayCertificateModel {

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

	@JsonProperty("personCertificateType")
	public PersonCertificateTypes getPersonCertificateType() {
		return this.personCertificateType;
	}

	public void setPersonCertificateType(PersonCertificateTypes personCertificateType) {
		this.personCertificateType = personCertificateType;
	}

	@JsonProperty("certificateType")
	public CertificateTypes getCertificateType() {
		return this.certificateType;
	}

	public void setCertificateType(CertificateTypes certificateType) {
		this.certificateType = certificateType;
	}

	@JsonProperty("ci")
	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	@JsonProperty("cie")
	public String getCie() {
		return this.cie;
	}

	public void setCie(String cie) {
		this.cie = cie;
	}

	@JsonProperty("pasaporte")
	public String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	@JsonProperty("ruc")
	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@JsonProperty("responsable")
	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

}
