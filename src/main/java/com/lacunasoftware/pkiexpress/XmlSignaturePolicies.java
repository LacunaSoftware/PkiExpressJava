package com.lacunasoftware.pkiexpress;


@Deprecated
public enum XmlSignaturePolicies {
	NFe("nfe"),
	Basic("basic");

	private final String value;

	XmlSignaturePolicies(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
