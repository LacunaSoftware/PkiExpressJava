package com.lacunasoftware.pkiexpress;


public enum PadesCertificationLevel {
	NotCertified("not-certified"),
	CertifiedFormFilling("certified-form-filling"),
	CertifiedFromFillingAndAnnotations("certified-form-filling-annotations"),
	CertifiedNoChangesAllowed("certified-no-changes-allowed");

	private final String value;

	PadesCertificationLevel(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
