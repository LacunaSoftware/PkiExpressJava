package com.lacunasoftware.pkiexpress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }
    public void setOrganizationUnit(String organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public String getDnQualifier() {
        return dnQualifier;
    }
    public void setDnQualifier(String dnQualifier) {
        this.dnQualifier = dnQualifier;
    }

    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCommonName() {
        return commonName;
    }
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocality() {
        return locality;
    }
    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getInitials() {
        return initials;
    }
    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getPseudonym() {
        return pseudonym;
    }
    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getGenerationQualifier() {
        return generationQualifier;
    }
    public void setGenerationQualifier(String generationQualifier) {
        this.generationQualifier = generationQualifier;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Map<String, List<String>> getAllValues() {
        return allValues;
    }
    public void setAllValues(Map<String, List<String>> allValues) {
        this.allValues = allValues;
    }
}
