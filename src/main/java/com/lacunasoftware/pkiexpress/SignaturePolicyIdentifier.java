package com.lacunasoftware.pkiexpress;


/**
 * Contains information about a signature policy as indicated in a signature file
 */
public class SignaturePolicyIdentifier {
	private DigestAlgorithmAndValue digest;
	private String oid;
	private String uri;


	SignaturePolicyIdentifier(SignaturePolicyIdentifierModel model) {
		this.digest = new DigestAlgorithmAndValue(model.getDigest());
		this.oid = model.getOid();
		this.uri = model.getUri();
	}


	public DigestAlgorithmAndValue getDigest() {
		return digest;
	}

	public String getOid() {
		return oid;
	}

	public String getUri() {
		return uri;
	}
}
