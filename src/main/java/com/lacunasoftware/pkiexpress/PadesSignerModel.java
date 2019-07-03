package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PadesSignerModel extends CadesSignerModel {
	private Boolean isDocumentTimestamp = null;
	private String signatureFieldName = null;


	@JsonProperty("isDocumentTimestamp")
	public Boolean getIsDocumentTimestamp() {
		return isDocumentTimestamp;
	}
	public void setIsDocumentTimestamp(Boolean documentTimestamp) {
		isDocumentTimestamp = documentTimestamp;
	}

	@JsonProperty("signatureFieldName")
	public String getSignatureFieldName() {
		return signatureFieldName;
	}
	public void setSignatureFieldName(String signatureFieldName) {
		this.signatureFieldName = signatureFieldName;
	}
}
