package com.lacunasoftware.pkiexpress;


public enum SignatureValidationPolicies {
	Pades("pades"),
	PadesWithCertProtection("pades-with-cert-protection"),
	PkiBrazil("pki-brazil"),
	PkiBrazilWithCertProtection("pki-brazil-with-cert-protection"),
	AdobeReader("adobe-reader");


	private final String value;


	SignatureValidationPolicies(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}

	public boolean equals(SignatureValidationPolicies other) {
		return this.value.equals(other.getValue());
	}
}
