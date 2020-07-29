package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckServiceResultModel {

	private Boolean userHasCertificates;

	@JsonProperty("userHasCertificates")
	public Boolean getUserHasCertificates() {
		return userHasCertificates;
	}
	public void setUserHasCertificates(Boolean userHasCertificates) {
		this.userHasCertificates = userHasCertificates;
	}
}
