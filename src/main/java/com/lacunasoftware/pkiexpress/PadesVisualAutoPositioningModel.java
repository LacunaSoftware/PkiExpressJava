package com.lacunasoftware.pkiexpress;


class PadesVisualAutoPositioningModel {
	private PadesVisualRectangleModel container = null;
	private PadesSizeModel signatureRectangleSize = null;
	private Double rowSpacing = null;


	public PadesVisualRectangleModel getContainer() {
		return container;
	}

	public void setContainer(PadesVisualRectangleModel container) {
		this.container = container;
	}

	public PadesSizeModel getSignatureRectangleSize() {
		return signatureRectangleSize;
	}

	public void setSignatureRectangleSize(PadesSizeModel signatureRectangleSize) {
		this.signatureRectangleSize = signatureRectangleSize;
	}

	public Double getRowSpacing() {
		return rowSpacing;
	}

	public void setRowSpacing(Double rowSpacing) {
		this.rowSpacing = rowSpacing;
	}
}
