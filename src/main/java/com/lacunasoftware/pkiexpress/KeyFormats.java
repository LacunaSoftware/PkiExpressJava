package com.lacunasoftware.pkiexpress;


public enum KeyFormats {
	JSON("json"),
	BLOB("blob"),
	XML("xml");

	private String value;


	KeyFormats(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
