package com.lacunasoftware.pkiexpress;

/**
 * Represents one of the signatures in a PDF file.
 */
public class PadesSignerInfo extends CadesSignerInfo {

    private boolean isDocumentTimestamp;
    private String signatureFieldName;

    PadesSignerInfo(PadesSignerModel model) {
        super(model);
        this.isDocumentTimestamp = model.getIsDocumentTimestamp();
        this.signatureFieldName = model.getSignatureFieldName();
    }


    public boolean getIsDocumentTimestamp() {
        return isDocumentTimestamp;
    }

    public String getSignatureFieldName() {
        return signatureFieldName;
    }
}
