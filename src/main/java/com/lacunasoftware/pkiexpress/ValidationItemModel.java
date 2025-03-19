package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationItemModel {
	public enum TypeEnum {
		Success,
		CertificateNotYetValid,
		CertificateExpired,
		CertificateRevoked,
		CertificateIssuerNotFound,
		CertificateRevocationStatusUnknown,
		CertificateChainRootNotTrusted,
		InvalidCertificateSignature,
		DecodeError,
		RequiredSignedAttributeMissing,
		ForbiddenSignedAttributePresent,
		RequiredUnsignedAttributeMissing,
		ForbiddenUnsignedAttributePresent,
		ContentTypeMismatch,
		MessageDigestMismatch,
		SigningCertificateDigestMismatch,
		SignatureAlgorithmValidationFailed,
		RevocationDataIssuedBeforeGracePeriod,
		UncertifiedDateReference,
		SignaturePolicyMismatch,
		SigningTimeOutOfCertificateValidity,
		UnknownSignedAttributesPresent,
		UnknownUnsignedAttributesPresent,
		TimestampWithMoreThanOneSigner,
		TimestampMessageImprintMismatch,
		TimestampValidationException,
		CompleteReferencesMismatch,
		InvalidSignatureTimestamp,
		InvalidReferencesTimestamp,
		InvalidSigAndRefsTimestamp,
		InvalidArchiveTimestamp,
		InvalidKeyUsage,
		InvalidOcspResponse,
		UnauthorizedIssuer,
		UnknownRootTrustStatus,
		InvalidTsl,
		InvalidCrl,
		CertificateIssuerValid,
		CertificateIssuerInvalid,
		CertificateValidationFailed,
		SignatureVulnerableToSignerSubstitution,
		InvalidXmlSignatureSchema,
		XmlDSigCoreValidationFailed,
		SignatureTimestampIgnored,
		InvalidCertificationPathLen,
		SigningCertificateNotFound,
		UnauthorizedACIssuer,
		AlgorithmNotAllowed,
		UnacceptableSignaturePolicy,
		TslNotAvailable,
		SigningCertificateIssuerMismatch,
		SigningCertificateSerialNumberMismatch,
		PdfAnnotationChangesAfterSignature,
		InvalidPdfByteRange,
		RequiredDssEntryMissing
	}

	private TypeEnum type = null;
	private String message = null;
	private String detail = null;
	private ValidationResultsModel innerValidationResults = null;

	@JsonProperty("type")
	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("detail")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@JsonProperty("innerValidationResults")
	public ValidationResultsModel getInnerValidationResults() {
		return innerValidationResults;
	}

	public void setInnerValidationResults(ValidationResultsModel innerValidationResults) {
		this.innerValidationResults = innerValidationResults;
	}
}
