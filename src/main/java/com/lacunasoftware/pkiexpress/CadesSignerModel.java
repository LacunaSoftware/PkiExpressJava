package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadesSignerModel {

    private DigestAlgorithmAndValueModel messageDigest = null;
    private SignatureAlgorithmAndValueModel signature = null;
    private SignaturePolicyIdentifierModel signaturePolicy = null;
    private CertificateModel certificate = null;
    private Date signingTime = null;
    private Date certifiedDateReference = null;
    private List<CadesTimestampModel> timestamps = new ArrayList<CadesTimestampModel>();
    private ValidationResultsModel validationResults = null;

    public DigestAlgorithmAndValueModel getMessageDigest() {
        return messageDigest;
    }
    public void setMessageDigest(DigestAlgorithmAndValueModel messageDigest) {
        this.messageDigest = messageDigest;
    }

    public SignatureAlgorithmAndValueModel getSignature() {
        return signature;
    }
    public void setSignature(SignatureAlgorithmAndValueModel signature) {
        this.signature = signature;
    }

    public SignaturePolicyIdentifierModel getSignaturePolicy() {
        return signaturePolicy;
    }
    public void setSignaturePolicy(SignaturePolicyIdentifierModel signaturePolicy) {
        this.signaturePolicy = signaturePolicy;
    }

    public CertificateModel getCertificate() {
        return certificate;
    }
    public void setCertificate(CertificateModel certificate) {
        this.certificate = certificate;
    }

    public Date getSigningTime() {
        return signingTime;
    }
    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
    }

    public Date getCertifiedDateReference() {
        return certifiedDateReference;
    }
    public void setCertifiedDateReference(Date certifiedDateReference) {
        this.certifiedDateReference = certifiedDateReference;
    }

    public List<CadesTimestampModel> getTimestamps() {
        return timestamps;
    }
    public void setTimestamps(List<CadesTimestampModel> timestamps) {
        this.timestamps = timestamps;
    }

    public ValidationResultsModel getValidationResults() {
        return validationResults;
    }
    public void setValidationResults(ValidationResultsModel validationResults) {
        this.validationResults = validationResults;
    }
}
