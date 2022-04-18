package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificatePolicyQualifierInfoModel {

	public enum QualifierTypes {
		Cps, UserNotice;
	}

	private QualifierTypes type;
	private String cpsUri;
	private String unOrganization;
	private String unExplicitText;
	private List<Integer> unNoticeNumbers = new ArrayList<Integer>();

	@JsonProperty("type")
	public QualifierTypes getType() {
		return this.type;
	}

	public void setType(QualifierTypes type) {
		this.type = type;
	}

	@JsonProperty("cpsUri")
	public String getCpsUri() {
		return this.cpsUri;
	}

	public void setCpsUri(String cpsUri) {
		this.cpsUri = cpsUri;
	}

	@JsonProperty("unOrganization")
	public String getUnOrganization() {
		return this.unOrganization;
	}

	public void setUnOrganization(String unOrganization) {
		this.unOrganization = unOrganization;
	}

	@JsonProperty("unExplicitText")
	public String getUnExplicitText() {
		return this.unExplicitText;
	}

	public void setUnExplicitText(String unExplicitText) {
		this.unExplicitText = unExplicitText;
	}

	@JsonProperty("unNoticeNumbers")
	public List<Integer> getUnNoticeNumbers() {
		return this.unNoticeNumbers;
	}

	public void setUnNoticeNumbers(List<Integer> unNoticeNumbers) {
		this.unNoticeNumbers = unNoticeNumbers;
	}
}