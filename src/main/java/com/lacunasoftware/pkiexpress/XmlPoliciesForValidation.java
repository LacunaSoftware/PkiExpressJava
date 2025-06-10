package com.lacunasoftware.pkiexpress;

public enum XmlPoliciesForValidation {

    /**
     * Standard XmlDSig policy (default).
     * Signature policies available for validation (-p <policy>):
     * nfe                              Brazilian "Nota Fiscal Eletronica (NF-e)".
     * pki-brazil                       ICP-Brasil's XAdES policies.
     * pki-brazil-with-cert-protection  ICP-Brasil's XAdES policies with certificate protection
     *                                   (accepts only timestamp policies).
     * xades-brazil                     XAdES policies.
     * xml-dsig-basic                   Standard XmlDSig policy (default).
     */
    XML_DSIG_BASIC("xml-dsig-basic"),
    NFE("nfe"),
    PKI_BRAZIL_WITH_CERT_PROTECTION("pki-brazil-with-cert-protection"),
    PKI_BRAZIL("pki-brazil"),
    XADES_BASIC("xades-basic");

    private final String value;

    XmlPoliciesForValidation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
} 