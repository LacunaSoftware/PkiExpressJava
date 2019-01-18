package com.lacunasoftware.pkiexpress;


import java.awt.*;


public class PdfTextSection {
	private String text;
	private PdfTextStyle style;
	private Color color;
	private Double fontSize = null;


	public PdfTextSection() {
		this(null, Color.BLACK, PdfTextStyle.Normal, null);
	}

	public PdfTextSection(String text) {
		this(text, Color.BLACK, PdfTextStyle.Normal, null);
	}

	public PdfTextSection(String text, Color color) {
		this(text, color, PdfTextStyle.Normal, null);
	}

	public PdfTextSection(String text, Color color, PdfTextStyle style) {
		this(text, color, style, null);
	}

	public PdfTextSection(String text, Color color, PdfTextStyle style, Double fontSize) {
		this.text = text;
		this.color = color;
		this.style = style;
		this.fontSize = fontSize;
	}


	public PdfTextSectionModel toModel() {
		PdfTextSectionModel model = new PdfTextSectionModel();
		model.setStyle(PdfTextSectionModel.StyleEnum.valueOf(style.toString()));
		model.setText(text);
		model.setColor(Util.convertColorToModel(color));
		model.setFontSize(fontSize);
		return model;
	}

	//region FluentApi

	public PdfTextSection withFontSize(double fontSize) {
		this.fontSize = fontSize;
		return this;
	}

	public PdfTextSection withText(String text) {
		this.text = text;
		return this;
	}

	public PdfTextSection bold() {
		this.style = PdfTextStyle.Bold;
		return this;
	}

	public PdfTextSection italic() {
		this.style = PdfTextStyle.Italic;
		return this;
	}

	public PdfTextSection withColor(Color color) {
		this.color = color;
		return this;
	}

	//endregion

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public PdfTextStyle getStyle() {
		return style;
	}

	public void setStyle(PdfTextStyle style) {
		this.style = style;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Double getFontSize() {
		return fontSize;
	}

	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}
}
