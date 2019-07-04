package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class AuthStartModel {
	private String toSignData = null;
	private String digestAlgorithmName = null;
	private String digestAlgorithmOid = null;


	@JsonProperty("toSignData")
	String getToSignData() {
		return toSignData;
	}
	void setToSignData(String toSignData) {
		this.toSignData = toSignData;
	}

	@JsonProperty("digestAlgorithmName")
	String getDigestAlgorithmName() {
		return digestAlgorithmName;
	}
	void setDigestAlgorithmName(String digestAlgorithmName) {
		this.digestAlgorithmName = digestAlgorithmName;
	}

	@JsonProperty("digestAlgorithmOid")
	String getDigestAlgorithmOid() {
		return digestAlgorithmOid;
	}
	void setDigestAlgorithmOid(String digestAlgorithmOid) {
		this.digestAlgorithmOid = digestAlgorithmOid;
	}
}
