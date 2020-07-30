package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscoverServicesResultModel {
	private List<TrustServiceInfoModel> services = new ArrayList<TrustServiceInfoModel>();
	private List<TrustServiceAuthParametersModel> authParameters = new ArrayList<TrustServiceAuthParametersModel>();

	@JsonProperty("services")
	public List<TrustServiceInfoModel> getServices() {
		return services;
	}
	public void setServices(List<TrustServiceInfoModel> services) {
		this.services = services;
	}

	@JsonProperty("authParameters")
	public List<TrustServiceAuthParametersModel> getAuthParameters() {
		return authParameters;
	}
	public void setAuthParameters(List<TrustServiceAuthParametersModel> authParameters) {
		this.authParameters = authParameters;
	}
}
