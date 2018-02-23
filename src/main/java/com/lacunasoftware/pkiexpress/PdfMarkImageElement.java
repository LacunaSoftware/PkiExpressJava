package com.lacunasoftware.pkiexpress;

public class PdfMarkImageElement extends PdfMarkElement<PdfMarkImageElement> {
    private PdfMarkImage image;

    public PdfMarkImageElement() { super(PdfMarkElementType.Image); }

    public PdfMarkImageElement(PadesVisualRectangle relativeContainer, PdfMarkImage image) {
        super(PdfMarkElementType.Image, relativeContainer);
        this.image = image;
    }

    @Override
    public PdfMarkElementModel toModel() {
        PdfMarkElementModel model = super.toModel();
        model.setImage(image.toModel());
        return model;
    }

    //region FluentApi

    public PdfMarkImageElement withImage(PdfMarkImage image) {
        this.image = image;
        return this;
    }

    public PdfMarkImageElement withImage(byte[] imageContent, String mimeType) {
        this.image = new PdfMarkImage(imageContent, mimeType);
        return this;
    }

    //endregion

    public PdfMarkImage getImage() {
        return image;
    }
    public void setImage(PdfMarkImage image) {
        this.image = image;
    }
}
