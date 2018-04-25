package com.lacunasoftware.pkiexpress;

public enum StandardSignaturePolicies {

    // CAdES policies
    PkiBrazilCadesAdrBasica("ad-rb"),
    PkiBrazilCadesAdrBasicaWithRevocationValues("ad-rb-rv"),
    PkiBrazilCadesAdrTempo("ad-rt"),
    PkiBrazilCadesAdrCompleta("ad-rc"),
    CadesBes("cades"),
    CadesBesWithRevocationValues("cades-rv"),
    CadesT("cades-t"),

    // PAdES policies
    PadesBasic("pades"),
    PadesT("pades-t"),

    // XML policies
    NFePadraoNacional("nfe"),
    XadesBes("xades"),
    XmlDSigBasic("basic"),
    PkiBrazilXadesAdrBasica("ad-rb"),
    PkiBrazilXadesAdrTempo("ad-rt");

    private final String value;

    StandardSignaturePolicies(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(StandardSignaturePolicies other) {
        return this.value.equals(other.getValue());
    }

    public static boolean requireTimestamp(StandardSignaturePolicies policy) {

        if (policy == null) {
            return false;
        }

        return policy.equals(PkiBrazilCadesAdrTempo) ||
                policy.equals(PkiBrazilCadesAdrCompleta) ||
                policy.equals(CadesT) ||
                policy.equals(PadesT) ||
                policy.equals(PkiBrazilXadesAdrTempo);
    }
}
