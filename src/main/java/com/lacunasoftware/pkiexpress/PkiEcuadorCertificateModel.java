package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PkiEcuadorCertificateModel {

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


	@JsonProperty("certificateType")
	public CertificateTypes getCertificateType() {
		return this.certificateType;
	}

	public void setCertificateType(CertificateTypes certificateType) {
		this.certificateType = certificateType;
	}

	@JsonProperty("cedulaDeIdentidad")
	public String getCedulaDeIdentidad() {
		return this.cedulaDeIdentidad;
	}

	public void setCedulaDeIdentidad(String cedulaDeIdentidad) {
		this.cedulaDeIdentidad = cedulaDeIdentidad;
	}

	@JsonProperty("pasaporte")
	public String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	@JsonProperty("rup")
	public String getRup() {
		return this.rup;
	}

	public void setRup(String rup) {
		this.rup = rup;
	}

	@JsonProperty("ruc")
	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@JsonProperty("nombres")
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@JsonProperty("apellidos")
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@JsonProperty("razonSocial")
	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


}
