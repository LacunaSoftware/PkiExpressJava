package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrustServiceSessionResultModel {
	public enum TrustServiceSessionTypes {
		SingleSignature,
		MultiSignature,
		SignatureSession,
		AuthenticationSession
	}

	private String session;
	private TrustServiceNameModel service;
	private TrustServiceSessionTypes type;
	private String expiresOn;
	private String customState;

	@JsonProperty("session")
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}

	@JsonProperty("service")
	public TrustServiceNameModel getService() {
		return service;
	}
	public void setService(TrustServiceNameModel service) {
		this.service = service;
	}

	@JsonProperty("type")
	public TrustServiceSessionTypes getType() {
		return type;
	}
	public void setType(TrustServiceSessionTypes type) {
		this.type = type;
	}

	@JsonProperty("expiresOn")
	public String getExpiresOn() {
		return expiresOn;
	}
	public void setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
	}

	@JsonProperty("customState")
	public String getCustomState() {
		return customState;
	}
	public void setCustomState(String customState) {
		this.customState = customState;
	}
}
