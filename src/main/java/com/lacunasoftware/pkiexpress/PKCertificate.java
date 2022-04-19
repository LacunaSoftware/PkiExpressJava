package com.lacunasoftware.pkiexpress;


import java.math.BigInteger;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains information about a X.509 public key certificate
 */
public class PKCertificate {
	private byte[] content;
	private Name subjectName;
	private String emailAddress;
	private Name issuerName;
	private BigInteger serialNumber;
	private Date validityStart;
	private Date validityEnd;
	private PkiArgentinaCertificateFields pkiArgentina;
	private PkiBrazilCertificateFields pkiBrazil;
	private PkiEcuadorCertificateFields pkiEcuador;
	private PkiItalyCertificateFields pkiItaly;
	private PkiParaguayCertificateFields pkiParaguay;
	private PkiPeruCertificateFields pkiPeru;
	private PKCertificate issuer;
	private byte[] binaryThumbprintSHA256;
	private String thumbprint;
	private KeyUsage keyUsage;
	private List<CertificatePolicy> certificatePolicies = new ArrayList<CertificatePolicy>();


	PKCertificate(CertificateModel model) {
		this.content = Util.decodeBase64(model.getContent());
		this.subjectName = new Name(model.getSubjectName());
		this.emailAddress = model.getEmailAddress();
		this.issuerName = new Name(model.getIssuerName());
		this.serialNumber = new BigInteger(model.getSerialNumber());
		this.validityStart = model.getValidityStart();
		this.validityEnd = model.getValidityEnd();
		if (model.getPkiBrazil() != null) {
			this.pkiBrazil = new PkiBrazilCertificateFields(model.getPkiBrazil());
		}
		if (model.getPkiItaly() != null) {
			this.pkiItaly = new PkiItalyCertificateFields(model.getPkiItaly());
		}
		if (model.getPkiArgentina() != null) {
			this.pkiArgentina = new PkiArgentinaCertificateFields(model.getPkiArgentina());
		}
		if (model.getPkiEcuador() != null) {
			this.pkiEcuador = new PkiEcuadorCertificateFields(model.getPkiEcuador());
		}
		if (model.getPkiParaguay() != null) {
			this.pkiParaguay = new PkiParaguayCertificateFields(model.getPkiParaguay());
		}
		if (model.getPkiPeru() != null) {
			this.pkiPeru = new PkiPeruCertificateFields(model.getPkiPeru());
		}
		if (model.getIssuer() != null) {
			this.issuer = new PKCertificate(model.getIssuer());
		}
		this.binaryThumbprintSHA256 = Util.decodeBase64(model.getBinaryThumbprintSHA256());
		this.thumbprint = model.getThumbprint();
		if (model.getKeyUsage() != null) {
			this.keyUsage = new KeyUsage(model.getKeyUsage());
		}
		if (model.getCertificatePolicies() != null) {
			for (CertificatePolicyModel policyModel : model.getCertificatePolicies()) {
				this.certificatePolicies.add(new CertificatePolicy(policyModel));
			}
		}
	}

	public Name getSubjectName() {
		return subjectName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Name getIssuerName() {
		return issuerName;
	}

	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	public Date getValidityStart() {
		return validityStart;
	}

	public Date getValidityEnd() {
		return validityEnd;
	}

	/**
	 * Gets the ICP-Brasil specific certificate fields
	 *
	 * @return ICP-Brasil specific certificate fields
	 */
	public PkiBrazilCertificateFields getPkiBrazil() {
		return pkiBrazil;
	}

	/**
	 * Gets PKI-Italy specific fields
	 *
	 * @return PKI-Italy specific fields
	 */
	public PkiItalyCertificateFields getPkiItaly() {
		return pkiItaly;
	}

	public PkiArgentinaCertificateFields getPkiArgentina() {
		return pkiArgentina;
	}

	public PkiEcuadorCertificateFields getPkiEcuador() {
		return pkiEcuador;
	}

	public PkiParaguayCertificateFields getPkiParaguay() {
		return pkiParaguay;
	}

	public PkiPeruCertificateFields getPkiPeru() {
		return pkiPeru;
	}

	public PKCertificate getIssuer() {
		return issuer;
	}

	public byte[] getBinaryThumbprintSHA256() {
		return binaryThumbprintSHA256;
	}

	public byte[] getContent(){
		return content;
	}

	public String getThumbprint() {
		return thumbprint;
	}

	public KeyUsage getKeyUsage() {
		return keyUsage;
	}

	public void setKeyUsage(KeyUsage keyUsage) {
		this.keyUsage = keyUsage;
	}

	public List<CertificatePolicy> getCertificatePolicies() {
		return this.certificatePolicies;
	}
}
