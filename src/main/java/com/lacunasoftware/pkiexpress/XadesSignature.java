package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a XAdES signature.
 */
public class XadesSignature {
    protected List<XadesSignerInfo> signers = new ArrayList<XadesSignerInfo>();

    XadesSignature(XadesSignatureModel model) {
        for (XadesSignerModel signerModel : model.getSigners()) {
            signers.add(new XadesSignerInfo(signerModel));
        }
    }

    public List<XadesSignerInfo> getSigners() {
        return signers;
    }
} 