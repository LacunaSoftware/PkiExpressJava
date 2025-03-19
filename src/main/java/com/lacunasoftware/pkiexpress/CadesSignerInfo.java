package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents one of the signatures in a CAdES signature file.
 */
public class CadesSignerInfo {
	private DigestAlgorithmAndValue messageDigest;
	private SignatureAlgorithmAndValue signature;
	private SignaturePolicyIdentifier signaturePolicy;
	private PKCertificate certificate;
	private Date signingTime;
	private Date certifiedDateReference;
	private List<CadesTimestamp> timestamps = new ArrayList<CadesTimestamp>();
	private ValidationResults validationResults;
	private CommitmentType commitmentType;

	CadesSignerInfo(
			DigestAlgorithmAndValueModel messageDigest,
			SignatureAlgorithmAndValueModel signature,
			CertificateModel certificate,
			Date signingTime,
			Date certifiedDateReference,
			SignaturePolicyIdentifierModel signaturePolicy,
			List<CadesTimestampModel> timestamps,
			ValidationResultsModel validationResults,
			CommitmentTypeModel commitmentType
	) {
		this.messageDigest = new DigestAlgorithmAndValue(messageDigest);
		this.signature = new SignatureAlgorithmAndValue(signature);
		this.certificate = new PKCertificate(certificate);
		this.signingTime = signingTime;
		this.certifiedDateReference = certifiedDateReference;
		if (signaturePolicy != null) {
			this.signaturePolicy = new SignaturePolicyIdentifier(signaturePolicy);
		}
		if (timestamps != null) {
			for (CadesTimestampModel timestampModel : timestamps) {
				this.timestamps.add(new CadesTimestamp(timestampModel));
			}
		}
		if (validationResults != null) {
			this.validationResults = new ValidationResults(validationResults);
		}
		if (commitmentType != null) {
			this.commitmentType = new CommitmentType(commitmentType);
		}
	}

	CadesSignerInfo(CadesSignerModel model) {
		this(
				model.getMessageDigest(),
				model.getSignature(),
				model.getCertificate(),
				model.getSigningTime(),
				model.getCertifiedDateReference(),
				model.getSignaturePolicy(),
				model.getTimestamps(),
				model.getValidationResults(),
				model.getCommitmentType()
		);
	}

	public DigestAlgorithmAndValue getMessageDigest() {
		return messageDigest;
	}

	public SignatureAlgorithmAndValue getSignature() {
		return signature;
	}

	public SignaturePolicyIdentifier getSignaturePolicy() {
		return signaturePolicy;
	}

	public PKCertificate getCertificate() {
		return certificate;
	}

	public Date getSigningTime() {
		return signingTime;
	}

	public Date getCertifiedDateReference() {
		return certifiedDateReference;
	}

	public List<CadesTimestamp> getTimestamps() {
		return timestamps;
	}

	public ValidationResults getValidationResults() {
		return validationResults;
	}

	public CommitmentType getCommitmentType() {
		return commitmentType;
	}
}
