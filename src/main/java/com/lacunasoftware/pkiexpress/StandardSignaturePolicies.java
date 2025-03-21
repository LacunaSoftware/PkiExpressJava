package com.lacunasoftware.pkiexpress;


public enum StandardSignaturePolicies {
	// CAdES policies
	PkiBrazilCadesAdrBasica("adrb"),
	PkiBrazilCadesAdrBasicaWithRevocationValues("adrb-rv"),
	PkiBrazilCadesAdrTempo("adrt"),
	PkiBrazilCadesAdrCompleta("adrc"),
	PkiBrazilCadesAdrArquivamento("adra"),
	CadesBes("cades"),
	CadesBesWithRevocationValues("cades-rv"),
	CadesT("cades-t"),

	// PAdES policies
	PadesBasic("pades"),
	PadesBasicWithLTV("pades-ltv"),
	PadesT("pades-t"),
	PkiBrazilPadesAdrBasica("adrb"),
	PkiBrazilPadesAdrTempo("adrt"),
	PkiBrazilPadesAdrCompleta("adrc"),
	PkiBrazilPadesAdrArquivamento("adra"),

	// XML policies
	NFePadraoNacional("nfe"),
	XadesBes("xades"),
	XmlDSigBasic("basic"),
	PkiBrazilXadesAdrBasica("adrb"),
	PkiBrazilXadesAdrTempo("adrt"),
	PkiBrazilXadesAdrCompleta("adrc"),
	PkiBrazilXadesAdrArquivamento("adra"),
	CodWithSHA1("cod-sha1"),
	CodWithSHA256("cod-sha256");


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
				policy.equals(PkiBrazilCadesAdrArquivamento) ||
				policy.equals(CadesT) ||

				policy.equals(PadesT) ||
				policy.equals(PkiBrazilPadesAdrTempo) ||
				policy.equals(PkiBrazilPadesAdrCompleta) ||
				policy.equals(PkiBrazilPadesAdrArquivamento) ||

				policy.equals(PkiBrazilXadesAdrTempo) ||
				policy.equals(PkiBrazilXadesAdrCompleta) ||
				policy.equals(PkiBrazilXadesAdrArquivamento);
	}
}
