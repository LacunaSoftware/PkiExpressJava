package com.lacunasoftware.pkiexpress;


public class PadesVisualTextModel {

    private Double fontSize = null;
    private String text = null;
    private Boolean includeSigningTime = null;
    public enum HorizontalAlignEnum {
        Left,  Right,
    };
    private HorizontalAlignEnum horizontalAlign = null;
    private PadesVisualRectangleModel container = null;


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
}