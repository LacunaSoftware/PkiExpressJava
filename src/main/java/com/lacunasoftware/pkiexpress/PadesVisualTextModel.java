package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
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


	@JsonProperty("fontSize")
	public Double getFontSize() {
		return fontSize;
	}
	public void setFontSize(Double fontSize) {
		this.fontSize = fontSize;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("includeSigningTime")
	public Boolean getIncludeSigningTime() {
		return includeSigningTime;
	}
	public void setIncludeSigningTime(Boolean includeSigningTime) {
		this.includeSigningTime = includeSigningTime;
	}

	@JsonProperty("horizontalAlign")
	public HorizontalAlignEnum getHorizontalAlign() {
		return horizontalAlign;
	}
	public void setHorizontalAlign(HorizontalAlignEnum horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	@JsonProperty("container")
	public PadesVisualRectangleModel getContainer() {
		return container;
	}
	public void setContainer(PadesVisualRectangleModel container) {
		this.container = container;
	}

	@JsonProperty("signingTimeFormat")
	public String getSigningTimeFormat() {
		return signingTimeFormat;
	}
	public void setSigningTimeFormat(String signingTimeFormat) {
		this.signingTimeFormat = signingTimeFormat;
	}
}
