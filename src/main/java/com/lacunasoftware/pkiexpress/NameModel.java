package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NameModel {
	private String country = null;
	private String organization = null;
	private String organizationUnit = null;
	private String dnQualifier = null;
	private String stateName = null;
	private String commonName = null;
	private String serialNumber = null;
	private String locality = null;
	private String title = null;
	private String surname = null;
	private String givenName = null;
	private String initials = null;
	private String pseudonym = null;
	private String generationQualifier = null;
	private String emailAddress = null;
	private Map<String, List<String>> allValues = new HashMap<String, List<String>>();


	@JsonProperty("country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("organization")
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@JsonProperty("organizationUnit")
	public String getOrganizationUnit() {
		return organizationUnit;
	}
	public void setOrganizationUnit(String organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	@JsonProperty("dnQualifier")
	public String getDnQualifier() {
		return dnQualifier;
	}
	public void setDnQualifier(String dnQualifier) {
		this.dnQualifier = dnQualifier;
	}

	@JsonProperty("stateName")
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@JsonProperty("commonName")
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	@JsonProperty("serialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@JsonProperty("locality")
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("surname")
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@JsonProperty("givenName")
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	@JsonProperty("initials")
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}

	@JsonProperty("pseudonym")
	public String getPseudonym() {
		return pseudonym;
	}
	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	@JsonProperty("generationQualifier")
	public String getGenerationQualifier() {
		return generationQualifier;
	}
	public void setGenerationQualifier(String generationQualifier) {
		this.generationQualifier = generationQualifier;
	}

	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonProperty("allValues")
	public Map<String, List<String>> getAllValues() {
		return allValues;
	}
	public void setAllValues(Map<String, List<String>> allValues) {
		this.allValues = allValues;
	}
}
