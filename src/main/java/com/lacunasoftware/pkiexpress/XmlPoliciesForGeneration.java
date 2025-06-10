package com.lacunasoftware.pkiexpress;

public enum XmlPoliciesForGeneration {
    BASIC("basic"),
    BASIC_SHA1("basic-sha1"),
    NFE("nfe"),
    COD_SHA1("cod-sha1"),
    COD_SHA256("cod-sha256"),
    XADES("xades"),
    ADRB("adrb"),
    ADRB_RV("adrb-rv"),
    ADRT("adrt"),
    ADRC("adrc"),
    ADRA("adra");

    private final String value;

    XmlPoliciesForGeneration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
} 