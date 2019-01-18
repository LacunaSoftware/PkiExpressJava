package com.lacunasoftware.pkiexpress;


/**
 * Class containing the PKI-Italy policy specific certificate fields
 */
public class PkiItalyCertificateFields {
	private PkiItalyCertificateTypes certificateType;
	private String codiceFiscale;
	private String idCarta;


	PkiItalyCertificateFields(PkiItalyCertificateModel model) {
		this.certificateType = PkiItalyCertificateTypes.valueOf(model.getCertificateType().toString());
		this.codiceFiscale = model.getCodiceFiscale();
		this.idCarta = model.getIdCarta();
	}


	public PkiItalyCertificateTypes getCertificateType() {
		return certificateType;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getIdCarta() {
		return idCarta;
	}
}
