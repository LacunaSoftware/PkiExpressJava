package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XmlSignatureModel extends SignatureModel {

    private XmlSignedEntityTypes type;
    private XmlElementModel signedElement;
    private SignatureAlgorithmAndValueModel signature;
    private SignaturePolicyIdentifierModel signaturePolicy;
    private CertificateModel certificate;
    private Date signingTime;
    private Date certifiedDateReference;
    private List<CadesTimestampModel> timestamps = new ArrayList<CadesTimestampModel>();
    private List<CadesTimestampModel> archiveTimestamps = new ArrayList<CadesTimestampModel>();
    private ValidationResultsModel validationResults;
    private SignerBStampModel bStamp;
    

    @JsonProperty("type")
    public XmlSignedEntityTypes getType() {
        return type;
    }
    public void setType(XmlSignedEntityTypes type) {
        this.type = type;
    }

    @JsonProperty("signedElement")
    public XmlElementModel getSignedElement() {
        return signedElement;
    }
    public void setSignedElement(XmlElementModel signedElement) {
        this.signedElement = signedElement;
    }

    @JsonProperty("signature")
    public SignatureAlgorithmAndValueModel getSignature() {
        return signature;
    }
    public void setSignature(SignatureAlgorithmAndValueModel signature) {
        this.signature = signature;
    }

    @JsonProperty("signaturePolicy")
    public SignaturePolicyIdentifierModel getSignaturePolicy() {
        return signaturePolicy;
    }
    public void setSignaturePolicy(SignaturePolicyIdentifierModel signaturePolicy) {
        this.signaturePolicy = signaturePolicy;
    }

    @JsonProperty("certificate")
    public CertificateModel getCertificate() {
        return certificate;
    }
    public void setCertificate(CertificateModel certificate) {
        this.certificate = certificate;
    }

    @JsonProperty("signingTime")
    public Date getSigningTime() {
        return signingTime;
    }
    public void setSigningTime(Date signingTime) {
        this.signingTime = signingTime;
    }

    @JsonProperty("certifiedDateReference")
    public Date getCertifiedDateReference() {
        return certifiedDateReference;
    }
    public void setCertifiedDateReference(Date certifiedDateReference) {
        this.certifiedDateReference = certifiedDateReference;
    }

    @JsonProperty("timestamps")
    public List<CadesTimestampModel> getTimestamps() {
        return timestamps;
    }
    public void setTimestamps(List<CadesTimestampModel> timestamps) {
        this.timestamps = timestamps;
    }

    @JsonProperty("archiveTimestamps")
    public List<CadesTimestampModel> getArchiveTimestamps() {
        return archiveTimestamps;
    }
    public void setArchiveTimestamps(List<CadesTimestampModel> archiveTimestamps) {
        this.archiveTimestamps = archiveTimestamps;
    }

    @JsonProperty("validationResults")
    public ValidationResultsModel getValidationResults() {
        return validationResults;
    }
    public void setValidationResults(ValidationResultsModel validationResults) {
        this.validationResults = validationResults;
    }

    @JsonProperty("bStamp")
    public SignerBStampModel getBStamp() {
        return bStamp;
    }
    public void setBStamp(SignerBStampModel bStamp) {
        this.bStamp = bStamp;
    }
} 