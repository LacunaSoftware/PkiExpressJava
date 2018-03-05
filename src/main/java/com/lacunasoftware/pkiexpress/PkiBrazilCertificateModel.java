package com.lacunasoftware.pkiexpress;

public class PkiBrazilCertificateModel {

    public enum CertificateTypeEnum {
        Unknown,  A1,  A2,  A3,  A4,  S1,  S2,  S3,  S4,  T3,  T4,
    };
    private CertificateTypeEnum certificateType = null;
    private String cpf = null;
    private String cnpj = null;
    private String responsavel = null;
    private String dateOfBirth = null;
    private String companyName = null;
    private String oabUF = null;
    private String oabNumero = null;
    private String rgEmissor = null;
    private String rgEmissorUF = null;
    private String rgNumero = null;

    public CertificateTypeEnum getCertificateType() {
        return certificateType;
    }
    public void setCertificateType(CertificateTypeEnum certificateType) {
        this.certificateType = certificateType;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOabUF() {
        return oabUF;
    }
    public void setOabUF(String oabUF) {
        this.oabUF = oabUF;
    }

    public String getOabNumero() {
        return oabNumero;
    }
    public void setOabNumero(String oabNumero) {
        this.oabNumero = oabNumero;
    }

    public String getRgEmissor() {
        return rgEmissor;
    }
    public void setRgEmissor(String rgEmissor) {
        this.rgEmissor = rgEmissor;
    }

    public String getRgEmissorUF() {
        return rgEmissorUF;
    }
    public void setRgEmissorUF(String rgEmissorUF) {
        this.rgEmissorUF = rgEmissorUF;
    }

    public String getRgNumero() {
        return rgNumero;
    }
    public void setRgNumero(String rgNumero) {
        this.rgNumero = rgNumero;
    }
}
