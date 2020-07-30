package com.lacunasoftware.pkiexpress;

public class TrustServiceSessionResult {
	private String session;

	public TrustServiceSessionResult(TrustServiceSessionResultModel model) {
		this.session = model.getSession();
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
}
