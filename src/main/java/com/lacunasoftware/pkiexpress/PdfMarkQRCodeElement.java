package com.lacunasoftware.pkiexpress;


public class PdfMarkQRCodeElement extends PdfMarkElement<PdfMarkQRCodeElement> {

    private String qrCodeData;
    private boolean drawQuietZones;

    public PdfMarkQRCodeElement() {
        super(PdfMarkElementType.QRCode);
    }

    public PdfMarkQRCodeElement(PadesVisualRectangle relativeContainer, String qrCodeData) {
        super(PdfMarkElementType.QRCode, relativeContainer);
        this.qrCodeData = qrCodeData;
    }

    @Override
    public PdfMarkElementModel toModel() {
        PdfMarkElementModel model = super.toModel();
        model.setQrCodeData(qrCodeData);
        model.setQrCodeDrawQuietZones(drawQuietZones);
        return model;
    }

    //region FluentApi

    public PdfMarkQRCodeElement withQRCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
        return this;
    }

    public PdfMarkQRCodeElement drawQuietZones() {
        this.drawQuietZones = true;
        return this;
    }

    //endregion

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    public boolean isDrawQuietZones() {
        return drawQuietZones;
    }

    public void setDrawQuietZones(boolean drawQuietZones) {
        this.drawQuietZones = drawQuietZones;
    }
}
