package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PdfMarkModel {
	public enum PageOptionEnum {
		AllPages, SinglePage, SinglePageFromEnd, NewPage,
	}

	private PadesVisualRectangleModel container = null;
	private Double borderWidth = null;
	private ColorModel borderColor = null;
	private ColorModel backgroundColor = null;
	private List<PdfMarkElementModel> elements = new ArrayList<PdfMarkElementModel>();
	private PageOptionEnum pageOption = null;
	private Integer pageOptionNumber = null;


	@JsonProperty("container")
	public PadesVisualRectangleModel getContainer() {
		return container;
	}
	public void setContainer(PadesVisualRectangleModel container) {
		this.container = container;
	}

	@JsonProperty("borderWidth")
	public Double getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(Double borderWidth) {
		this.borderWidth = borderWidth;
	}

	@JsonProperty("borderColor")
	public ColorModel getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(ColorModel borderColor) {
		this.borderColor = borderColor;
	}

	@JsonProperty("backgroundColor")
	public ColorModel getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(ColorModel backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@JsonProperty("elements")
	public List<PdfMarkElementModel> getElements() {
		return elements;
	}
	public void setElements(List<PdfMarkElementModel> elements) {
		this.elements = elements;
	}

	@JsonProperty("pageOption")
	public PageOptionEnum getPageOption() {
		return pageOption;
	}
	public void setPageOption(PageOptionEnum pageOption) {
		this.pageOption = pageOption;
	}

	@JsonProperty("pageOptionNumber")
	public Integer getPageOptionNumber() {
		return pageOptionNumber;
	}
	public void setPageOptionNumber(Integer pageOptionNumber) {
		this.pageOptionNumber = pageOptionNumber;
	}
}
