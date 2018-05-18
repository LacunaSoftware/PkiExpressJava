package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

public class CadesSignatureModel extends SignatureModel {

    public enum EncapsulatedContentTypeEnum {
        Data,  SignedData,  EnvelopedData,  DigestedData,  EncryptedData,  AuthenticatedData,  TstInfo,
    }
    private EncapsulatedContentTypeEnum encapsulatedContentType = null;
    private Boolean hasEncapsulatedContent = null;
    private List<CadesSignerModel> signers = new ArrayList<CadesSignerModel>();
    private FileModel encapsulatedContent = null;

    public EncapsulatedContentTypeEnum getEncapsulatedContentType() {
        return encapsulatedContentType;
    }
    public void setEncapsulatedContentType(EncapsulatedContentTypeEnum encapsulatedContentType) {
        this.encapsulatedContentType = encapsulatedContentType;
    }

    public Boolean getHasEncapsulatedContent() {
        return hasEncapsulatedContent;
    }
    public void setHasEncapsulatedContent(Boolean hasEncapsulatedContent) {
        this.hasEncapsulatedContent = hasEncapsulatedContent;
    }

    public List<CadesSignerModel> getSigners() {
        return signers;
    }
    public void setSigners(List<CadesSignerModel> signers) {
        this.signers = signers;
    }

    public FileModel getEncapsulatedContent() {
        return encapsulatedContent;
    }
    public void setEncapsulatedContent(FileModel encapsulatedContent) {
        this.encapsulatedContent = encapsulatedContent;
    }
}
