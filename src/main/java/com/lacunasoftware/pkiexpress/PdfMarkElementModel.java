package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PdfMarkElementModel {
	public enum ElementTypeEnum {
		Text, Image, QRCode,
	}

	public enum AlignEnum {
		Left, Center, Right,
	}


	private ElementTypeEnum elementType = null;
	private PadesVisualRectangleModel relativeContainer = null;
	private Integer rotation = null;
	private List<PdfTextSectionModel> textSections = new ArrayList<PdfTextSectionModel>();
	private PdfMarkImageModel image = null;
	private String qrCodeData = null;
	private Boolean qrCodeDrawQuietZones = null;
	private AlignEnum align = null;
	private Double opacity = null;


	@JsonProperty("elementType")
	public ElementTypeEnum getElementType() {
		return elementType;
	}
	public void setElementType(ElementTypeEnum elementType) {
		this.elementType = elementType;
	}

	@JsonProperty("relativeContainer")
	public PadesVisualRectangleModel getRelativeContainer() {
		return relativeContainer;
	}
	public void setRelativeContainer(PadesVisualRectangleModel relativeContainer) {
		this.relativeContainer = relativeContainer;
	}

	@JsonProperty("rotation")
	public Integer getRotation() {
		return rotation;
	}
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	@JsonProperty("textSections")
	public List<PdfTextSectionModel> getTextSections() {
		return textSections;
	}
	public void setTextSections(List<PdfTextSectionModel> textSections) {
		this.textSections = textSections;
	}

	@JsonProperty("image")
	public PdfMarkImageModel getImage() {
		return image;
	}
	public void setImage(PdfMarkImageModel image) {
		this.image = image;
	}

	@JsonProperty("qrCodeData")
	public String getQrCodeData() {
		return qrCodeData;
	}
	public void setQrCodeData(String qrCodeData) {
		this.qrCodeData = qrCodeData;
	}

	@JsonProperty("qrCodeDrawQuietZones")
	public Boolean getQrCodeDrawQuietZones() {
		return qrCodeDrawQuietZones;
	}
	public void setQrCodeDrawQuietZones(Boolean qrCodeDrawQuietZones) {
		this.qrCodeDrawQuietZones = qrCodeDrawQuietZones;
	}

	@JsonProperty("align")
	public AlignEnum getAlign() {
		return align;
	}
	public void setAlign(AlignEnum align) {
		this.align = align;
	}

	@JsonProperty("opacity")
	public Double getOpacity() {
		return opacity;
	}
	public void setOpacity(Double opacity) {
		this.opacity = opacity;
	}
}
