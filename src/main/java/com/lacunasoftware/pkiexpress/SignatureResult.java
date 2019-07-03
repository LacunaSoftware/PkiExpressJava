package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class SignatureResult {
	private CertificateModel signer = null;

	@JsonProperty("signer")
	public CertificateModel getSigner() {
		return signer;
	}
	public void setSigner(CertificateModel signer) {
		this.signer = signer;
	}
}
