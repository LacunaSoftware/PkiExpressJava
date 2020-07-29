package com.lacunasoftware.pkiexpress;

public enum TrustServiceSessionTypes {
	SINGLE_SIGNATURE("SingleSignature"),
	MULTI_SIGNATURE("MultiSignature"),
	SIGNATURE_SESSION("SignatureSession"),
	AUTHENTICATION_SESSION("AuthenticationSession");

	private String value;

	TrustServiceSessionTypes(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
