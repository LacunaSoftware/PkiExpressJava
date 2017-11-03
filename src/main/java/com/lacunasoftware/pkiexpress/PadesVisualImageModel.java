package com.lacunasoftware.pkiexpress;


class PadesVisualImageModel {

    private ResourceContentOrReference resource = null;
    private Integer opacity = null;
    public enum HorizontalAlignEnum {
        Left,  Center,  Right,
    };
    private HorizontalAlignEnum horizontalAlign = null;
    public enum VerticalAlignEnum {
        Top,  Center,  Bottom,
    };
    private VerticalAlignEnum verticalAlign = null;

    public ResourceContentOrReference getResource() {
        return resource;
    }
    public void setResource(ResourceContentOrReference resource) {
        this.resource = resource;
    }


    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = opacity;
    }

    public HorizontalAlignEnum getHorizontalAlign() {
        return horizontalAlign;
    }

    public void setHorizontalAlign(HorizontalAlignEnum horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    public VerticalAlignEnum getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(VerticalAlignEnum verticalAlign) {
        this.verticalAlign = verticalAlign;
    }
}