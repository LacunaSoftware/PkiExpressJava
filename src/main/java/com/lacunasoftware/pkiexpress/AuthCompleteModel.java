package com.lacunasoftware.pkiexpress;


public class AuthCompleteModel {
	private CertificateModel certificate;
	private ValidationResultsModel validationResults;


	public CertificateModel getCertificate() {
		return certificate;
	}

	public void setCertificate(CertificateModel certificate) {
		this.certificate = certificate;
	}

	public ValidationResultsModel getValidationResults() {
		return validationResults;
	}

	public void setValidationResults(ValidationResultsModel validationResults) {
		this.validationResults = validationResults;
	}
}
