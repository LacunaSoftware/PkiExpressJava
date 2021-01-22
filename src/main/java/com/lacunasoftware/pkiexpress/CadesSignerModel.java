package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CadesSignerModel {
	private DigestAlgorithmAndValueModel messageDigest = null;
	private SignatureAlgorithmAndValueModel signature = null;
	private SignaturePolicyIdentifierModel signaturePolicy = null;
	private CertificateModel certificate = null;
	private Date signingTime = null;
	private Date certifiedDateReference = null;
	private List<CadesTimestampModel> timestamps = new ArrayList<CadesTimestampModel>();
	private ValidationResultsModel validationResults = null;
	private CommitmentTypeModel commitmentType = null;


	@JsonProperty("messageDigest")
	public DigestAlgorithmAndValueModel getMessageDigest() {
		return messageDigest;
	}
	public void setMessageDigest(DigestAlgorithmAndValueModel messageDigest) {
		this.messageDigest = messageDigest;
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

	@JsonProperty("validationResults")
	public ValidationResultsModel getValidationResults() {
		return validationResults;
	}
	public void setValidationResults(ValidationResultsModel validationResults) {
		this.validationResults = validationResults;
	}

	@JsonProperty("commitmentType")
	public CommitmentTypeModel getCommitmentType() {
		return commitmentType;
	}
	public void setCommitmentType(CommitmentTypeModel commitmentType) {
		this.commitmentType = commitmentType;
	}
}
