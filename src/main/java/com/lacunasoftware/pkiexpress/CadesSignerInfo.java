package com.lacunasoftware.pkiexpress;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Represents one of the signatures in a CAdES signature file.
 */
public class CadesSignerInfo {
	private DigestAlgorithmAndValue messageDigest;
	//private SignatureAlgorithmAndValueModel signature = null; // TODO!s
	private SignaturePolicyIdentifier signaturePolicy;
	private PKCertificate certificate;
	private Date signingTime;
	private Date certifiedDateReference;
	private List<CadesTimestamp> timestamps = new ArrayList<CadesTimestamp>();
	private ValidationResults validationResults;


	CadesSignerInfo(
			DigestAlgorithmAndValueModel messageDigest,
			SignatureAlgorithmAndValueModel signature,
			CertificateModel certificate,
			Date signingTime,
			Date certifiedDateReference,
			SignaturePolicyIdentifierModel signaturePolicy,
			List<CadesTimestampModel> timestamps,
			ValidationResultsModel validationResults
	) {
		this.messageDigest = new DigestAlgorithmAndValue(messageDigest);
		//this.signature = ...; // TODO!
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
				model.getValidationResults()
		);
	}


	public DigestAlgorithmAndValue getMessageDigest() {
		return messageDigest;
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
}
