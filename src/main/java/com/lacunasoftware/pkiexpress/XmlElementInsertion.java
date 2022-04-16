package com.lacunasoftware.pkiexpress;

public enum XmlElementInsertion {

	AppendChild("append-child"),
	PrependChild("prepend-child"),
	AppendSibling("append-sibling"),
	PrependSibling("prepend-sibling");

	private final String value;

	XmlElementInsertion(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}