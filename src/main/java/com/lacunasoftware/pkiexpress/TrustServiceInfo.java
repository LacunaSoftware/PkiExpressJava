package com.lacunasoftware.pkiexpress;

public class TrustServiceInfo {
	private String service;
	private String provider;
	private String badgeUrl;

	public TrustServiceInfo(TrustServiceInfoModel model) {
		this.service = model.getService();
		this.provider = model.getProvider();
		this.badgeUrl = model.getBadgeUrl();
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getBadgeUrl() {
		return badgeUrl;
	}

	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}
}
