package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class XmlElementModel {
    private String localName;
    private List<XmlAttributeModel> attributes = new ArrayList<XmlAttributeModel>();
    private String namespaceUri;

    @JsonProperty("localName")
    public String getLocalName() {
        return localName;
    }
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @JsonProperty("attributes")
    public List<XmlAttributeModel> getAttributes() {
        return attributes;
    }
    public void setAttributes(List<XmlAttributeModel> attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("namespaceUri")
    public String getNamespaceUri() {
        return namespaceUri;
    }
    public void setNamespaceUri(String namespaceUri) {
        this.namespaceUri = namespaceUri;
    }
} 