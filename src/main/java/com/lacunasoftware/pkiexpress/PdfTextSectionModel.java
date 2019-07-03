package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PdfTextSectionModel {
	public enum StyleEnum {
		Normal, Bold, Italic,
	}

	private String text = null;
	private StyleEnum style = null;
	private ColorModel color = null;
	private Double fontSize = null;


	@JsonProperty("text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("style")
	public StyleEnum getStyle() {
		return style;
	}
	public void setStyle(StyleEnum style) {
		this.style = style;
	}

	@JsonProperty("color")
	public ColorModel getColor() {
		return color;
	}
	public void setColor(ColorModel color) {
		this.color = color;
	}

	@JsonProperty("fontSize")
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
}
