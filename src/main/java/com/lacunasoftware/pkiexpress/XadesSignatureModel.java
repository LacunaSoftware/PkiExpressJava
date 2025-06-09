package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XadesSignatureModel extends SignatureModel {
    private List<XadesSignerModel> signers = new ArrayList<XadesSignerModel>();

    @JsonProperty("signers")
    public List<XadesSignerModel> getSigners() {
        return signers;
    }
    public void setSigners(List<XadesSignerModel> signers) {
        this.signers = signers;
    }
} 