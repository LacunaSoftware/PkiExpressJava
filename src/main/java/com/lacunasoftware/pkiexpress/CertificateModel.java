package com.lacunasoftware.pkiexpress;


import java.util.Date;


public class CertificateModel {
	private NameModel subjectName = null;
	private String emailAddress = null;
	private NameModel issuerName = null;
	private String issuerDisplayName = null;
	private String serialNumber = null;
	private Date validityStart = null;
	private Date validityEnd = null;
	private CertificateModel issuer = null;
	private PkiBrazilCertificateModel pkiBrazil = null;
	private PkiItalyCertificateModel pkiItaly = null;
	private String binaryThumbprintSHA256 = null;
	private String thumbprint = null;
	private String subjectCommonName = null;
	private String subjectDisplayName = null;


	public NameModel getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(NameModel subjectName) {
		this.subjectName = subjectName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public NameModel getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(NameModel issuerName) {
		this.issuerName = issuerName;
	}

	public String getIssuerDisplayName() {
		return issuerDisplayName;
	}

	public void setIssuerDisplayName(String issuerDisplayName) {
		this.issuerDisplayName = issuerDisplayName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getValidityStart() {
		return validityStart;
	}

	public void setValidityStart(Date validityStart) {
		this.validityStart = validityStart;
	}

	public Date getValidityEnd() {
		return validityEnd;
	}

	public void setValidityEnd(Date validityEnd) {
		this.validityEnd = validityEnd;
	}

	public CertificateModel getIssuer() {
		return issuer;
	}

	public void setIssuer(CertificateModel issuer) {
		this.issuer = issuer;
	}

	public PkiBrazilCertificateModel getPkiBrazil() {
		return pkiBrazil;
	}

	public void setPkiBrazil(PkiBrazilCertificateModel pkiBrazil) {
		this.pkiBrazil = pkiBrazil;
	}

	public PkiItalyCertificateModel getPkiItaly() {
		return pkiItaly;
	}

	public void setPkiItaly(PkiItalyCertificateModel pkiItaly) {
		this.pkiItaly = pkiItaly;
	}

	public String getBinaryThumbprintSHA256() {
		return binaryThumbprintSHA256;
	}

	public void setBinaryThumbprintSHA256(String binaryThumbprintSHA256) {
		this.binaryThumbprintSHA256 = binaryThumbprintSHA256;
	}

	public String getThumbprint() {
		return thumbprint;
	}

	public void setThumbprint(String thumbprint) {
		this.thumbprint = thumbprint;
	}

	public String getSubjectCommonName() {
		return subjectCommonName;
	}

	public void setSubjectCommonName(String subjectCommonName) {
		this.subjectCommonName = subjectCommonName;
	}

	public String getSubjectDisplayName() {
		return subjectDisplayName;
	}

	public void setSubjectDisplayName(String subjectDisplayName) {
		this.subjectDisplayName = subjectDisplayName;
	}
}
