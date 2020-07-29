package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrustServiceInfoModel {
	private String service;
	private String provider;
	private String badgeUrl;

	@JsonProperty("service")
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}

	@JsonProperty("provider")
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}

	@JsonProperty("badgeUrl")
	public String getBadgeUrl() {
		return badgeUrl;
	}
	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}
}
