package com.lacunasoftware.pkiexpress;


import java.math.BigInteger;
import java.security.Key;
import java.util.Date;


/**
 * Contains information about a X.509 public key certificate
 */
public class PKCertificate {
	private Name subjectName;
	private String emailAddress;
	private Name issuerName;
	private BigInteger serialNumber;
	private Date validityStart;
	private Date validityEnd;
	private PkiBrazilCertificateFields pkiBrazil;
	private PkiItalyCertificateFields pkiItaly;
	private PKCertificate issuer;
	private byte[] binaryThumbprintSHA256;
	private String thumbprint;
	private KeyUsage keyUsage;


	PKCertificate(CertificateModel model) {
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
		if (model.getIssuer() != null) {
			this.issuer = new PKCertificate(model.getIssuer());
		}
		this.binaryThumbprintSHA256 = Util.decodeBase64(model.getBinaryThumbprintSHA256());
		this.thumbprint = model.getThumbprint();
		if (model.getKeyUsage() != null) {
			this.keyUsage = new KeyUsage(model.getKeyUsage());
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

	public PKCertificate getIssuer() {
		return issuer;
	}

	public byte[] getBinaryThumbprintSHA256() {
		return binaryThumbprintSHA256;
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
}
