package com.lacunasoftware.pkiexpress;

public class TrustServiceAuthParameters {
	private TrustServiceInfo serviceInfo;
	private String authUrl;

	public TrustServiceAuthParameters(TrustServiceAuthParametersModel model) {
		this.authUrl = model.getAuthUrl();

		if (model.getServiceInfo() != null) {
			this.serviceInfo = new TrustServiceInfo(model.getServiceInfo());
		}
	}

	public TrustServiceInfo getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(TrustServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
}
