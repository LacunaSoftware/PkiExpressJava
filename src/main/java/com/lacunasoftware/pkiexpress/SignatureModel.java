package com.lacunasoftware.pkiexpress;

public class SignatureModel {

    private FileModel auditPackage;
    private SignatureBStampModel bStamp;

    public FileModel getAuditPackage() {
        return auditPackage;
    }

    public void setAuditPackage(FileModel auditPackage) {
        this.auditPackage = auditPackage;
    }

    public SignatureBStampModel getbStamp() {
        return bStamp;
    }

    public void setbStamp(SignatureBStampModel bStamp) {
        this.bStamp = bStamp;
    }
}
