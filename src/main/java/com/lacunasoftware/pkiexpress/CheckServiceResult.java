package com.lacunasoftware.pkiexpress;

public class CheckServiceResult {
	private boolean userHasCertificates;

	public CheckServiceResult(CheckServiceResultModel model) {
		this.userHasCertificates = model.getUserHasCertificates();
	}

	public boolean isUserHasCertificates() {
		return userHasCertificates;
	}

	public void setUserHasCertificates(boolean userHasCertificates) {
		this.userHasCertificates = userHasCertificates;
	}
}
