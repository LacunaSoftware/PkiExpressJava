package com.lacunasoftware.pkiexpress;


public class SignaturePolicyIdentifierModel {
	private DigestAlgorithmAndValueModel digest = null;
	private String oid = null;
	private String uri = null;


	public DigestAlgorithmAndValueModel getDigest() {
		return digest;
	}

	public void setDigest(DigestAlgorithmAndValueModel digest) {
		this.digest = digest;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
