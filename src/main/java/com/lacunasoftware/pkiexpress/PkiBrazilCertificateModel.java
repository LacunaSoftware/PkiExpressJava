package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PkiBrazilCertificateModel {
	public enum CertificateTypeEnum {
		Unknown, A1, A2, A3, A4, S1, S2, S3, S4, T3, T4,
	}


	private CertificateTypeEnum certificateType = null;
	private String cpf = null;
	private String cnpj = null;
	private String responsavel = null;
	private String dateOfBirth = null;
	private String companyName = null;
	private String oabUF = null;
	private String oabNumero = null;
	private String rgEmissor = null;
	private String rgEmissorUF = null;
	private String rgNumero = null;


	@JsonProperty("certificateType")
	public CertificateTypeEnum getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(CertificateTypeEnum certificateType) {
		this.certificateType = certificateType;
	}

	@JsonProperty("cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@JsonProperty("cnpj")
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@JsonProperty("responsavel")
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("companyName")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@JsonProperty("oabUF")
	public String getOabUF() {
		return oabUF;
	}
	public void setOabUF(String oabUF) {
		this.oabUF = oabUF;
	}

	@JsonProperty("oabNumero")
	public String getOabNumero() {
		return oabNumero;
	}
	public void setOabNumero(String oabNumero) {
		this.oabNumero = oabNumero;
	}

	@JsonProperty("rgEmissor")
	public String getRgEmissor() {
		return rgEmissor;
	}
	public void setRgEmissor(String rgEmissor) {
		this.rgEmissor = rgEmissor;
	}

	@JsonProperty("rgEmissorUF")
	public String getRgEmissorUF() {
		return rgEmissorUF;
	}
	public void setRgEmissorUF(String rgEmissorUF) {
		this.rgEmissorUF = rgEmissorUF;
	}

	@JsonProperty("rgNumero")
	public String getRgNumero() {
		return rgNumero;
	}
	public void setRgNumero(String rgNumero) {
		this.rgNumero = rgNumero;
	}
}
