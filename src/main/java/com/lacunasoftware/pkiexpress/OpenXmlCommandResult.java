package com.lacunasoftware.pkiexpress;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenXmlCommandResult {
    private List<XmlSignatureModel> signers;

    @JsonProperty("signers")
    public List<XmlSignatureModel> getSigners() {
        return signers;
    }

    public void setSigners(List<XmlSignatureModel> signers) {
        this.signers = signers;
    }
} 