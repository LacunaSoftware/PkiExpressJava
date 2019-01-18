package com.lacunasoftware.pkiexpress;


public class PadesVisualTextModel {
	public enum HorizontalAlignEnum {
		Left, Right,
	}


	private Double fontSize = null;
	private String text = null;
	private Boolean includeSigningTime = null;
	private HorizontalAlignEnum horizontalAlign = null;
	private PadesVisualRectangleModel container = null;
	private String signingTimeFormat = null;


	public Double getFontSize() {
		return fontSize;
	}

	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIncludeSigningTime() {
		return includeSigningTime;
	}

	public void setIncludeSigningTime(Boolean includeSigningTime) {
		this.includeSigningTime = includeSigningTime;
	}

	public HorizontalAlignEnum getHorizontalAlign() {
		return horizontalAlign;
	}

	public void setHorizontalAlign(HorizontalAlignEnum horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	public PadesVisualRectangleModel getContainer() {
		return container;
	}

	public void setContainer(PadesVisualRectangleModel container) {
		this.container = container;
	}

	public String getSigningTimeFormat() {
		return signingTimeFormat;
	}

	public void setSigningTimeFormat(String signingTimeFormat) {
		this.signingTimeFormat = signingTimeFormat;
	}
}
