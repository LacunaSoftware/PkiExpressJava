package com.lacunasoftware.pkiexpress;


/**
 * Auto positioning options for PAdES signature visual representation.
 */
public class PadesVisualAutoPositioning extends PadesVisualPositioning {
	private PadesVisualRectangle container;
	private PadesSize signatureRectangleSize;
	private double rowSpacing;


	public PadesVisualAutoPositioning() {
		super();
	}

	/**
	 * Creates a custom auto positioning
	 *
	 * @param pageNumber             The document page number to insert the signature visual representation.  Negative values
	 *                               denote pages counted from the end of the document (-1 means last page). Zero denotes that, if
	 *                               no signatures are present, the signature should be inserted in a new page appended to the
	 *                               document.
	 * @param measurementUnits       The measurement units of the elements sizes and positions set
	 * @param container              The container area of the page that the signatures visual representation will be placed
	 * @param signatureRectangleSize The signature visual representation dimensions
	 * @param rowSpacing             The space between rows of signatures visual representations
	 */
	public PadesVisualAutoPositioning(int pageNumber, PadesMeasurementUnits measurementUnits, PadesVisualRectangle container, PadesSize signatureRectangleSize, double rowSpacing) {
		super(pageNumber, measurementUnits);
		this.container = container;
		this.signatureRectangleSize = signatureRectangleSize;
		this.rowSpacing = rowSpacing;
	}


	public PadesVisualRectangle getContainer() {
		return container;
	}

	/**
	 * Sets the container area of the page that the signatures visual representation will be placed
	 *
	 * @param container The container area of the page that the signatures visual representation will be placed
	 */
	public void setContainer(PadesVisualRectangle container) {
		this.container = container;
	}

	public PadesSize getSignatureRectangleSize() {
		return signatureRectangleSize;
	}

	/**
	 * Sets the signature visual representation dimensions
	 *
	 * @param signatureRectangleSize The signature visual representation dimensions
	 */
	public void setSignatureRectangleSize(PadesSize signatureRectangleSize) {
		this.signatureRectangleSize = signatureRectangleSize;
	}

	public double getRowSpacing() {
		return rowSpacing;
	}

	/**
	 * Sets the space between rows of signatures visual representations
	 *
	 * @param rowSpacing The space between rows of signatures visual representations
	 */
	public void setRowSpacing(double rowSpacing) {
		this.rowSpacing = rowSpacing;
	}

	@Override
	PadesVisualPositioningModel toModel() {
		PadesVisualPositioningModel model = super.toModel();
		PadesVisualAutoPositioningModel auto = new PadesVisualAutoPositioningModel();
		auto.setContainer(container.toModel());
		auto.setSignatureRectangleSize(signatureRectangleSize.toModel());
		auto.setRowSpacing(rowSpacing);
		model.setAuto(auto);
		return model;
	}
}
