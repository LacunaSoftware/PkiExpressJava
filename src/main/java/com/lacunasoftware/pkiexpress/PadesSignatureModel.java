package com.lacunasoftware.pkiexpress;

import java.util.List;
import java.util.ArrayList;

public class PadesSignatureModel {

    private List<PadesSignerModel> signers = new ArrayList<PadesSignerModel>();

    public List<PadesSignerModel> getSigners() {
        return signers;
    }
    public void setSigners(List<PadesSignerModel> signers) {
        this.signers = signers;
    }
}
