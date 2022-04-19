package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateModel {
	private String content = null;
	private NameModel subjectName = null;
	private String emailAddress = null;
	private NameModel issuerName = null;
	private String issuerDisplayName = null;
	private String serialNumber = null;
	private Date validityStart = null;
	private Date validityEnd = null;
	private CertificateModel issuer = null;
	private PkiArgentinaCertificateModel pkiArgentina = null;
	private PkiBrazilCertificateModel pkiBrazil = null;
	private PkiEcuadorCertificateModel pkiEcuador = null;
	private PkiItalyCertificateModel pkiItaly = null;
	private PkiParaguayCertificateModel pkiParaguay = null;
	private PkiPeruCertificateModel pkiPeru = null;
	private String binaryThumbprintSHA256 = null;
	private String thumbprint = null;
	private String subjectCommonName = null;
	private String subjectDisplayName = null;
	private Integer keyUsage = null;
	private List<CertificatePolicyModel> certificatePolicies = new ArrayList<CertificatePolicyModel>();

	@JsonProperty("content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@JsonProperty("subjectName")
	public NameModel getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(NameModel subjectName) {
		this.subjectName = subjectName;
	}

	@JsonProperty("emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonProperty("issuerName")
	public NameModel getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(NameModel issuerName) {
		this.issuerName = issuerName;
	}

	@JsonProperty("issuerDisplayName")
	public String getIssuerDisplayName() {
		return issuerDisplayName;
	}
	public void setIssuerDisplayName(String issuerDisplayName) {
		this.issuerDisplayName = issuerDisplayName;
	}

	@JsonProperty("serialNumber")
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@JsonProperty("validityStart")
	public Date getValidityStart() {
		return validityStart;
	}
	public void setValidityStart(Date validityStart) {
		this.validityStart = validityStart;
	}

	@JsonProperty("validityEnd")
	public Date getValidityEnd() {
		return validityEnd;
	}
	public void setValidityEnd(Date validityEnd) {
		this.validityEnd = validityEnd;
	}

	@JsonProperty("issuer")
	public CertificateModel getIssuer() {
		return issuer;
	}
	public void setIssuer(CertificateModel issuer) {
		this.issuer = issuer;
	}

	@JsonProperty("pkiBrazil")
	public PkiBrazilCertificateModel getPkiBrazil() {
		return pkiBrazil;
	}
	public void setPkiBrazil(PkiBrazilCertificateModel pkiBrazil) {
		this.pkiBrazil = pkiBrazil;
	}

	@JsonProperty("pkiItaly")
	public PkiItalyCertificateModel getPkiItaly() {
		return pkiItaly;
	}
	public void setPkiItaly(PkiItalyCertificateModel pkiItaly) {
		this.pkiItaly = pkiItaly;
	}

	@JsonProperty("pkiArgentina")
	public PkiArgentinaCertificateModel getPkiArgentina() {
		return pkiArgentina;
	}
	public void setPkiArgentina(PkiArgentinaCertificateModel pkiArgentina) {
		this.pkiArgentina = pkiArgentina;
	}

	@JsonProperty("pkiEcuador")
	public PkiEcuadorCertificateModel getPkiEcuador() {
		return pkiEcuador;
	}
	public void setPkiEcuador(PkiEcuadorCertificateModel pkiEcuador) {
		this.pkiEcuador = pkiEcuador;
	}

	@JsonProperty("pkiParaguay")
	public PkiParaguayCertificateModel getPkiParaguay() {
		return pkiParaguay;
	}
	public void setPkiParaguay(PkiParaguayCertificateModel pkiParaguay) {
		this.pkiParaguay = pkiParaguay;
	}

	@JsonProperty("pkiPeru")
	public PkiPeruCertificateModel getPkiPeru() {
		return pkiPeru;
	}
	public void setPkiPeru(PkiPeruCertificateModel pkiPeru) {
		this.pkiPeru = pkiPeru;
	}

	@JsonProperty("binaryThumbprintSHA256")
	public String getBinaryThumbprintSHA256() {
		return binaryThumbprintSHA256;
	}
	public void setBinaryThumbprintSHA256(String binaryThumbprintSHA256) {
		this.binaryThumbprintSHA256 = binaryThumbprintSHA256;
	}

	@JsonProperty("thumbprint")
	public String getThumbprint() {
		return thumbprint;
	}
	public void setThumbprint(String thumbprint) {
		this.thumbprint = thumbprint;
	}

	@JsonProperty("subjectCommonName")
	public String getSubjectCommonName() {
		return subjectCommonName;
	}
	public void setSubjectCommonName(String subjectCommonName) {
		this.subjectCommonName = subjectCommonName;
	}

	@JsonProperty("subjectDisplayName")
	public String getSubjectDisplayName() {
		return subjectDisplayName;
	}
	public void setSubjectDisplayName(String subjectDisplayName) {
		this.subjectDisplayName = subjectDisplayName;
	}

	@JsonProperty("keyUsage")
	public Integer getKeyUsage() {
		return keyUsage;
	}
	public void setKeyUsage(Integer keyUsage) {
		this.keyUsage = keyUsage;
	}

	@JsonProperty("certificatePolicies")
	public List<CertificatePolicyModel> getCertificatePolicies() {
		return this.certificatePolicies;
	}

	public void setCertificatePolicies(List<CertificatePolicyModel> certificatePolicies) {
		this.certificatePolicies = certificatePolicies;
	}

}
