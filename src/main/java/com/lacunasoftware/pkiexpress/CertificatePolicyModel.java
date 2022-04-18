package com.lacunasoftware.pkiexpress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificatePolicyModel {

	private String id;
	private List<CertificatePolicyQualifierInfoModel> qualifiers = new ArrayList<CertificatePolicyQualifierInfoModel>();

	@JsonProperty("id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("qualifiers")
	public List<CertificatePolicyQualifierInfoModel> getQualifiers() {
		return this.qualifiers;
	}

	public void setQualifiers(List<CertificatePolicyQualifierInfoModel> qualifiers) {
		this.qualifiers = qualifiers;
	}
}