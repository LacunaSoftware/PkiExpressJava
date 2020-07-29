package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrustServiceSessionResultModel {
	private String session;

	@JsonProperty("serviceInfo")
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
}
