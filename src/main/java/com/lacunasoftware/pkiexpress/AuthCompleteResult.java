package com.lacunasoftware.pkiexpress;

public class AuthCompleteResult {

    private PKCertificate certificate;
    private ValidationResults validationResults;


    AuthCompleteResult(CertificateModel certificate, ValidationResultsModel validationResults) {

        if (certificate != null) {
            this.certificate = new PKCertificate(certificate);
        }

        if (validationResults != null) {
            this.validationResults = new ValidationResults(validationResults);
        }
    }

    AuthCompleteResult(AuthCompleteModel model) {
        this(model.getCertificate(), model.getValidationResults());
    }

    public PKCertificate getCertificate() {
        return certificate;
    }

    public ValidationResults getValidationResults() {
        return validationResults;
    }
}
