package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrustServiceAuthParametersModel {
	private TrustServiceInfoModel serviceInfo;
	private String authUrl;

	@JsonProperty("serviceInfo")
	public TrustServiceInfoModel getServiceInfo() {
		return serviceInfo;
	}
	public void setServiceInfo(TrustServiceInfoModel serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	@JsonProperty("authUrl")
	public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
}
