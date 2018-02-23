package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

public class PdfMarkElementModel {

    public enum ElementTypeEnum {
        Text,  Image,  QRCode,
    }
    private ElementTypeEnum elementType = null;
    private PadesVisualRectangleModel relativeContainer = null;
    private Integer rotation = null;
    private List<PdfTextSectionModel> textSections = new ArrayList<PdfTextSectionModel>();
    private PdfMarkImageModel image = null;
    private String qrCodeData = null;
    private Boolean qrCodeDrawQuietZones = null;
    public enum AlignEnum {
        Left,  Center,  Right,
    }
    private AlignEnum align = null;
    private Double opacity = null;

    public ElementTypeEnum getElementType() {
        return elementType;
    }
    public void setElementType(ElementTypeEnum elementType) {
        this.elementType = elementType;
    }

    public PadesVisualRectangleModel getRelativeContainer() {
        return relativeContainer;
    }
    public void setRelativeContainer(PadesVisualRectangleModel relativeContainer) {
        this.relativeContainer = relativeContainer;
    }

    public Integer getRotation() {
        return rotation;
    }
    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    public List<PdfTextSectionModel> getTextSections() {
        return textSections;
    }
    public void setTextSections(List<PdfTextSectionModel> textSections) {
        this.textSections = textSections;
    }

    public PdfMarkImageModel getImage() {
        return image;
    }
    public void setImage(PdfMarkImageModel image) {
        this.image = image;
    }

    public String getQrCodeData() {
        return qrCodeData;
    }
    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    public Boolean getQrCodeDrawQuietZones() {
        return qrCodeDrawQuietZones;
    }
    public void setQrCodeDrawQuietZones(Boolean qrCodeDrawQuietZones) {
        this.qrCodeDrawQuietZones = qrCodeDrawQuietZones;
    }

    public AlignEnum getAlign() {
        return align;
    }
    public void setAlign(AlignEnum align) {
        this.align = align;
    }

    public Double getOpacity() {
        return opacity;
    }
    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }
}
