package com.lacunasoftware.pkiexpress;


public enum StandardSignaturePoliciesForValidation {
	Pades("pades"),
	PadesWithCertProtection("pades-with-cert-protection"),
	PkiBrazil("pki-brazil"),
	PkiBrazilWithCertProtection("pki-brazil-with-cert-protection"),
	AdobeReader("adobe-reader");


	private final String value;


	StandardSignaturePoliciesForValidation(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}

	public boolean equals(StandardSignaturePoliciesForValidation other) {
		return this.value.equals(other.getValue());
	}
}
