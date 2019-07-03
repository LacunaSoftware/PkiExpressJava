package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthCompleteModel {
	private CertificateModel certificate = null;
	private ValidationResultsModel validationResults = null;


	@JsonProperty("certificate")
	public CertificateModel getCertificate() {
		return certificate;
	}
	public void setCertificate(CertificateModel certificate) {
		this.certificate = certificate;
	}

	@JsonProperty("validationResults")
	public ValidationResultsModel getValidationResults() {
		return validationResults;
	}
	public void setValidationResults(ValidationResultsModel validationResults) {
		this.validationResults = validationResults;
	}
}
