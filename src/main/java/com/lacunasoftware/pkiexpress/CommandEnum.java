package com.lacunasoftware.pkiexpress;


public enum CommandEnum {
	CommandSignCades("sign-cades"),
	CommandSignPades("sign-pades"),
	CommandSignXml("sign-xml"),
	CommandStartCades("start-cades"),
	CommandStartPades("start-pades"),
	CommandStartXml("start-xml"),
	CommandCompleteSig("complete-sig"),
	CommandOpenPades("open-pades"),
	CommandOpenCades("open-cades"),
	CommandOpenCertificate("open-cert"),
	CommandEditPdf("edit-pdf"),
	CommandStartAuth("start-auth"),
	CommandCompleteAuth("complete-auth"),
	CommandStampPdf("stamp-pdf"),
	CommandGenKey("gen-key"),
	CommandCreatePfx("create-pfx"),
	CommandReadCert("read-cert"),
	CommandCheckService("check-service"),
	CommandDiscoverServices("discover-services"),
	CommandPasswordAuthorize("pwd-auth"),
	CommandCompleteServiceAuth("complete-service-auth");


	private final String value;


	CommandEnum(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}
}
