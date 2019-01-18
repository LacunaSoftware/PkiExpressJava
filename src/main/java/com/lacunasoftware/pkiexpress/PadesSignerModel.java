package com.lacunasoftware.pkiexpress;


public class PadesSignerModel extends CadesSignerModel {
	private Boolean isDocumentTimestamp = null;
	private String signatureFieldName = null;


	public Boolean getIsDocumentTimestamp() {
		return isDocumentTimestamp;
	}

	public void setIsDocumentTimestamp(Boolean documentTimestamp) {
		isDocumentTimestamp = documentTimestamp;
	}

	public String getSignatureFieldName() {
		return signatureFieldName;
	}

	public void setSignatureFieldName(String signatureFieldName) {
		this.signatureFieldName = signatureFieldName;
	}
}
