package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CadesTimestampModel extends CadesSignatureModel {
	private Date genTime = null;
	private String serialNumber = null;
	private DigestAlgorithmAndValueModel messageImprint = null;


	@JsonProperty("genTime")
	public Date getGenTime() {
		return genTime;
	}
	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	@JsonProperty("genSerialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@JsonProperty("messageImprint")
	public DigestAlgorithmAndValueModel getMessageImprint() {
		return messageImprint;
	}
	public void setMessageImprint(DigestAlgorithmAndValueModel messageImprint) {
		this.messageImprint = messageImprint;
	}
}
