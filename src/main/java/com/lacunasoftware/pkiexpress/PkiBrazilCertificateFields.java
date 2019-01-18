package com.lacunasoftware.pkiexpress;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Class containing the ICP-Brasil policy specific certificate fields
 */
public class PkiBrazilCertificateFields {
	private PkiBrazilCertificateTypes certificateType;
	private String cpf;
	private String cnpj;
	private String responsavel;
	private Date dateOfBirth;
	private String companyName;
	private String rgNumero;
	private String rgEmissor;
	private String rgEmissorUF;
	private String oabNumero;
	private String oabUF;


	PkiBrazilCertificateFields(PkiBrazilCertificateModel model) {
		this.certificateType = PkiBrazilCertificateTypes.valueOf(model.getCertificateType().toString());
		this.cpf = model.getCpf();
		this.cnpj = model.getCnpj();
		this.responsavel = model.getResponsavel();
		if (model.getDateOfBirth() != null) {
			try {
				this.dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(model.getDateOfBirth());
			} catch (ParseException e) {
				throw new RuntimeException("Could not parse dateOfBirth: " + model.getDateOfBirth(), e);
			}
		}
		this.companyName = model.getCompanyName();
		this.rgNumero = model.getRgNumero();
		this.rgEmissor = model.getRgEmissor();
		this.rgEmissorUF = model.getRgEmissorUF();
		this.oabNumero = model.getOabNumero();
		this.oabUF = model.getOabUF();
	}


	public PkiBrazilCertificateTypes getCertificateType() {
		return certificateType;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCpfFormatted() {
		if (cpf == null || cpf.length() == 0) {
			return "";
		}
		if (!cpf.matches("^\\d{11}$")) {
			return cpf;
		}
		return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getCnpjFormatted() {
		if (cnpj == null || cnpj.length() == 0) {
			return "";
		}
		if (!cnpj.matches("^\\d{14}$")) {
			return cnpj;
		}
		return String.format("%s.%s.%s/%s-%s", cnpj.substring(0, 2), cnpj.substring(2, 5), cnpj.substring(5, 8), cnpj.substring(8, 12), cnpj.substring(12));
	}

	public String getResponsavel() {
		return responsavel;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getRGNumero() {
		return rgNumero;
	}

	public String getRGEmissor() {
		return rgEmissor;
	}

	public String getRGEmissorUF() {
		return rgEmissorUF;
	}

	public String getOabNumero() {
		return oabNumero;
	}

	public String getOabUF() {
		return oabUF;
	}
}
