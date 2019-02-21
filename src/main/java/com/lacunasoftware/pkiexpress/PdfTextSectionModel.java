package com.lacunasoftware.pkiexpress;


public class PdfTextSectionModel {
	public enum StyleEnum {
		Normal, Bold, Italic,
	}

	private String text = null;
	private StyleEnum style = null;
	private ColorModel color = null;
	private Double fontSize = null;


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public StyleEnum getStyle() {
		return style;
	}

	public void setStyle(StyleEnum style) {
		this.style = style;
	}

	public ColorModel getColor() {
		return color;
	}

	public void setColor(ColorModel color) {
		this.color = color;
	}

	public Double getFontSize() {
		return fontSize;
	}

	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
}
