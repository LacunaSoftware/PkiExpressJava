package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CAdES signature.
 */
public class CadesSignature {

    protected EncapsulatedContentTypes encapsulatedContentType;
    protected boolean hasEncapsulatedContent;
    protected List<CadesSignerInfo> signers = new ArrayList<CadesSignerInfo>();
    protected byte[] encapsulatedContent;

    CadesSignature(String encapsulatedContentType, boolean hasEncapsulatedContent, List<CadesSignerModel> signers, FileModel encapsulatedContent) {
        this.encapsulatedContentType = EncapsulatedContentTypes.valueOf(encapsulatedContentType);
        this.hasEncapsulatedContent = hasEncapsulatedContent;
        for (CadesSignerModel signerModel : signers) {
            this.signers.add(new CadesSignerInfo(signerModel));
        }
        if (encapsulatedContent != null) {
            this.encapsulatedContent = Util.decodeBase64(encapsulatedContent.getContent());
        }
    }

    CadesSignature(CadesSignatureModel model) {
        this(model.getEncapsulatedContentType().toString(), model.getHasEncapsulatedContent(), model.getSigners(), model.getEncapsulatedContent());
    }

    public EncapsulatedContentTypes getEncapsulatedContentType() {
        return encapsulatedContentType;
    }

    public boolean getHasEncapsulatedContent() {
        return hasEncapsulatedContent;
    }

    public List<CadesSignerInfo> getSigners() {
        return signers;
    }

    public byte[] getEncapsulatedContent() {
        return encapsulatedContent;
    }
}