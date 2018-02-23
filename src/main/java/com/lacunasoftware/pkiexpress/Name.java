package com.lacunasoftware.pkiexpress;

/**
 * A X.509 Name
 */
public class Name {

    private String country;
    private String organization;
    private String organizationUnit;
    private String dnQualifier;
    private String stateName;
    private String commonName;
    private String serialNumber;
    private String locality;
    private String title;
    private String surname;
    private String givenName;
    private String initials;
    private String pseudonym;
    private String generationQualifier;
    private String emailAddress;

    Name(NameModel model) {
        this.emailAddress = model.getEmailAddress();
        this.stateName = model.getStateName();
        this.commonName = model.getCommonName();
        this.serialNumber = model.getSerialNumber();
        this.locality = model.getLocality();
        this.title = model.getTitle();
        this.surname = model.getSurname();
        this.givenName = model.getGivenName();
        this.initials = model.getInitials();
        this.pseudonym = model.getPseudonym();
        this.generationQualifier = model.getGenerationQualifier();
        this.country = model.getCountry();
        this.organization = model.getOrganization();
        this.organizationUnit = model.getOrganizationUnit();
        this.dnQualifier = model.getDnQualifier();
    }

    public String getCountry() {
        return country;
    }

    public String getOrganization() {
        return organization;
    }

    public String getOrganizationUnit() {
        return organizationUnit;
    }

    public String getDnQualifier() {
        return dnQualifier;
    }

    public String getStateName() {
        return stateName;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getLocality() {
        return locality;
    }

    public String getTitle() {
        return title;
    }

    public String getSurname() {
        return surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getInitials() {
        return initials;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public String getGenerationQualifier() {
        return generationQualifier;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
