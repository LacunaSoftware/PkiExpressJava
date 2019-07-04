package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SignatureModel {
	private FileModel auditPackage = null;


	@JsonProperty("auditPackage")
	public FileModel getAuditPackage() {
		return auditPackage;
	}
	public void setAuditPackage(FileModel auditPackage) {
		this.auditPackage = auditPackage;
	}
}
