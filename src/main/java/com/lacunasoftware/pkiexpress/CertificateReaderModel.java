package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateReaderModel {
	private CertificateModel info = null;
	private String pemContent = null;
	private String derContent = null;


	@JsonProperty("info")
	public CertificateModel getInfo() {
		return info;
	}
	public void setInfo(CertificateModel info) {
		this.info = info;
	}

	@JsonProperty("pemContent")
	public String getPemContent() {
		return pemContent;
	}
	public void setPemContent(String pemContent) {
		this.pemContent = pemContent;
	}

	@JsonProperty("derContent")
	public String getDerContent() {
		return derContent;
	}
	public void setDerContent(String derContent) {
		this.derContent = derContent;
	}
}
