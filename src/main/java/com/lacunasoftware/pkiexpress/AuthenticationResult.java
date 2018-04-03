package com.lacunasoftware.pkiexpress;

public class AuthenticationResult {

    private PKCertificate certificate;
    private ValidationResults validationResults;

    AuthenticationResult(CertificateModel certificate, ValidationResultsModel validationResults) {
        this.certificate = new PKCertificate(certificate);
        if (validationResults != null) {
            this.validationResults = new ValidationResults(validationResults);
        }
    }

    AuthenticationResult(AuthCompleteModel model) {
        this(model.getCertificate(), model.getValidationResults());
    }

    public PKCertificate getCertificate() {
        return certificate;
    }

    public ValidationResults getValidationResults() {
        return validationResults;
    }
}
