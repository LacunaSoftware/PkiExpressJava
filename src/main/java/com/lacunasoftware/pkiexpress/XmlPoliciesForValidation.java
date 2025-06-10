package com.lacunasoftware.pkiexpress;

public enum XmlPoliciesForValidation {
	NFe("nfe"),
	Basic("basic"),
	XML_DSIG_BASIC("xml-dsig-basic"),
    PKI_BRAZIL_WITH_CERT_PROTECTION("pki-brazil-with-cert-protection"),
    PKI_BRAZIL("pki-brazil"),
    XADES_BASIC("xades-basic");

	private final String value;

	XmlPoliciesForValidation(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}