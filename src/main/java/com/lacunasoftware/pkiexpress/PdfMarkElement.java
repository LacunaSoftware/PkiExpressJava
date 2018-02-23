package com.lacunasoftware.pkiexpress;


public abstract class PdfMarkElement<T extends PdfMarkElement<T>> {

    protected PdfMarkElementType elementType;
    protected PadesVisualRectangle relativeContainer;
    protected int rotation = 0;
    protected double opacity = 100;

    public PdfMarkElement(PdfMarkElementType type) {
        elementType = type;
    }

    public PdfMarkElement(PdfMarkElementType type, PadesVisualRectangle relativeContainer) {
        this(type);
        this.relativeContainer = relativeContainer;
    }

    public PdfMarkElementModel toModel() {
        PdfMarkElementModel model = new PdfMarkElementModel();
        model.setElementType(PdfMarkElementModel.ElementTypeEnum.valueOf(elementType.toString()));
        if (relativeContainer != null) {
            model.setRelativeContainer(relativeContainer.toModel());
        }
        model.setRotation(rotation);
        model.setOpacity(opacity);
        return model;
    }

    //region FluentApi

    @SuppressWarnings("unchecked")
    public T onContainer(PadesVisualRectangle relativeContainer) {
        this.relativeContainer = relativeContainer;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T withRotation(int rotation) {
        this.rotation = rotation;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T rotate90Clockwise() {
        this.rotation = 270;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T rotate90Counterclockwise() {
        this.rotation = 90;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T rotate180() {
        this.rotation = 180;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T withOpacity(double opacity) {
        this.opacity = opacity;
        return (T) this;
    }

    //endregion

    public PdfMarkElementType getElementType() {
        return elementType;
    }
    public void setElementType(PdfMarkElementType elementType) {
        this.elementType = elementType;
    }

    public PadesVisualRectangle getRelativeContainer() {
        return relativeContainer;
    }
    public void setRelativeContainer(PadesVisualRectangle relativeContainer) {
        this.relativeContainer = relativeContainer;
    }

    public int getRotation() {
        return rotation;
    }
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public double getOpacity() {
        return opacity;
    }
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

}
