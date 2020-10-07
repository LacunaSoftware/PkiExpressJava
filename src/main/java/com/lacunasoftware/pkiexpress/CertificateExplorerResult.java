package com.lacunasoftware.pkiexpress;


public class CertificateExplorerResult {
	private PKCertificate certificate;
	private ValidationResults validationResults;


	CertificateExplorerResult(CertificateModel certificate, ValidationResultsModel validationResults) {

		if (certificate != null) {
			this.certificate = new PKCertificate(certificate);
		}
		
		if (validationResults != null) {
			this.validationResults = new ValidationResults(validationResults);
		}
	}

	CertificateExplorerResult(CertificateExplorerModel model) {
		this(model.getCertificate(), model.getValidationResults());
	}

	public PKCertificate getCertificate() {
		return certificate;
	}

	public ValidationResults getValidationResults() {
		return validationResults;
	}
}
