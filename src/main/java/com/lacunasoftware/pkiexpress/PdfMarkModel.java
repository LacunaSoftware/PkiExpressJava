package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.List;

public class PdfMarkModel {

    private PadesVisualRectangleModel container = null;
    private Double borderWidth = null;
    private ColorModel borderColor = null;
    private ColorModel backgroundColor = null;
    private List<PdfMarkElementModel> elements = new ArrayList<PdfMarkElementModel>();
    public enum PageOptionEnum {
        AllPages,  SinglePage,  SinglePageFromEnd,  NewPage,
    }
    private PageOptionEnum pageOption = null;
    private Integer pageOptionNumber = null;

    public PadesVisualRectangleModel getContainer() {
        return container;
    }
    public void setContainer(PadesVisualRectangleModel container) {
        this.container = container;
    }

    public Double getBorderWidth() {
        return borderWidth;
    }
    public void setBorderWidth(Double borderWidth) {
        this.borderWidth = borderWidth;
    }

    public ColorModel getBorderColor() {
        return borderColor;
    }
    public void setBorderColor(ColorModel borderColor) {
        this.borderColor = borderColor;
    }

    public ColorModel getBackgroundColor() {
        return backgroundColor;
    }
    public void setBackgroundColor(ColorModel backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<PdfMarkElementModel> getElements() {
        return elements;
    }
    public void setElements(List<PdfMarkElementModel> elements) {
        this.elements = elements;
    }

    public PageOptionEnum getPageOption() {
        return pageOption;
    }
    public void setPageOption(PageOptionEnum pageOption) {
        this.pageOption = pageOption;
    }

    public Integer getPageOptionNumber() {
        return pageOptionNumber;
    }
    public void setPageOptionNumber(Integer pageOptionNumber) {
        this.pageOptionNumber = pageOptionNumber;
    }
}
