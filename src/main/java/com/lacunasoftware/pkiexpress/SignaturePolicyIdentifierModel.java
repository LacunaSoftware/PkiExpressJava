package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SignaturePolicyIdentifierModel {
	private DigestAlgorithmAndValueModel digest = null;
	private String oid = null;
	private String uri = null;


	@JsonProperty("digest")
	public DigestAlgorithmAndValueModel getDigest() {
		return digest;
	}
	public void setDigest(DigestAlgorithmAndValueModel digest) {
		this.digest = digest;
	}

	@JsonProperty("oid")
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}

	@JsonProperty("uri")
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
