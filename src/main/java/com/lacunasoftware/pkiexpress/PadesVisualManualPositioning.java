package com.lacunasoftware.pkiexpress;


/**
 * Manual positioning preference for PAdES signature visual representation.
 */
public class PadesVisualManualPositioning extends PadesVisualPositioning {
	private PadesVisualRectangle signatureRectangle;


	/**
	 * Creates empty preference
	 */
	public PadesVisualManualPositioning() {
		super();
	}

	/**
	 * Creates manual positioning preferences
	 *
	 * @param pageNumber         The document page number to insert the signature visual representation.  Negative values
	 *                           denote pages counted from the end of the document (-1 means last page). Zero denotes that, if
	 *                           no signatures are present, the signature should be inserted in a new page appended to the
	 *                           document.
	 * @param measurementUnits   The measurement units of the elements sizes and positions set
	 * @param signatureRectangle The signature visual representation rectangle position to be placed in the document
	 */
	public PadesVisualManualPositioning(int pageNumber, PadesMeasurementUnits measurementUnits, PadesVisualRectangle signatureRectangle) {
		super(pageNumber, measurementUnits);
		this.signatureRectangle = signatureRectangle;
	}


	public PadesVisualRectangle getSignatureRectangle() {
		return signatureRectangle;
	}

	/**
	 * Sets the signature visual representation rectangle dimensions to be placed in the document
	 *
	 * @param signatureRectangle The signature visual representation rectangle dimensions to be placed in the document
	 */
	public void setSignatureRectangle(PadesVisualRectangle signatureRectangle) {
		this.signatureRectangle = signatureRectangle;
	}

	@Override
	PadesVisualPositioningModel toModel() {
		PadesVisualPositioningModel model = super.toModel();
		model.setManual(signatureRectangle.toModel());
		return model;
	}
}
