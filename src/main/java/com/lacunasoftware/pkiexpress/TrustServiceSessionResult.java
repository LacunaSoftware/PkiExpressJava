package com.lacunasoftware.pkiexpress;

import java.util.Date;

public class TrustServiceSessionResult {
	private String session;
	private String service;
	private TrustServiceSessionTypes type;
	private Date expiresOn;
	private String customState;

	public TrustServiceSessionResult(TrustServiceSessionResultModel model) {
		this.session = model.getSession();
		this.customState = model.getCustomState();

		if (model.getExpiresOn() != null) {
			this.expiresOn = Util.parseApiDate(model.getExpiresOn());
		}

		if (model.getService() != null) {
			this.service = model.getService().getName();
		}

		if (model.getType() != null) {
			TrustServiceSessionTypes type = null;
			for (TrustServiceSessionTypes t : TrustServiceSessionTypes.values()) {
				if (t.getValue().equals(model.getType().name())) {
					type = t;
				}
			}
			if (type == null) {
				throw new RuntimeException("Invalid TrustServiceSessionTypes value.");
			}
			this.type = type;
		}
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public TrustServiceSessionTypes getType() {
		return type;
	}

	public void setType(TrustServiceSessionTypes type) {
		this.type = type;
	}

	public Date getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getCustomState() {
		return customState;
	}

	public void setCustomState(String customState) {
		this.customState = customState;
	}
}
