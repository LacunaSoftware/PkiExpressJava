package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class PadesVisualAutoPositioningModel {
	public enum HorizontalDirectionEnum {
		LeftToRight, RightToLeft
	}

	public enum VerticalDirectionEnum {
		TopDown, BottomUp
	}

	private PadesVisualRectangleModel container = null;
	private PadesSizeModel signatureRectangleSize = null;
	private Double rowSpacing = null;
	private VerticalDirectionEnum verticalDirection = null;
	private HorizontalDirectionEnum horizontalDirection = null;

	@JsonProperty("container")
	public PadesVisualRectangleModel getContainer() {
		return container;
	}
	public void setContainer(PadesVisualRectangleModel container) {
		this.container = container;
	}

	@JsonProperty("signatureRectangleSize")
	public PadesSizeModel getSignatureRectangleSize() {
		return signatureRectangleSize;
	}
	public void setSignatureRectangleSize(PadesSizeModel signatureRectangleSize) {
		this.signatureRectangleSize = signatureRectangleSize;
	}

	@JsonProperty("rowSpacing")
	public Double getRowSpacing() {
		return rowSpacing;
	}
	public void setRowSpacing(Double rowSpacing) {
		this.rowSpacing = rowSpacing;
	}

	@JsonProperty("horizontalDirection")
	public HorizontalDirectionEnum getHorizontalDirection() {
		return horizontalDirection;
	}
	public void setHorizontalDirection(HorizontalDirectionEnum horizontalDirection) {
		this.horizontalDirection = horizontalDirection;
	}

	@JsonProperty("verticalDirection")
	public VerticalDirectionEnum getVerticalDirection() {
		return verticalDirection;
	}
	public void setVerticalDirection(VerticalDirectionEnum verticalDirection) {
		this.verticalDirection = verticalDirection;
	}
}
