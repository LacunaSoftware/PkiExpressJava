package com.lacunasoftware.pkiexpress;


class PadesPageOptimizationModel {

    public enum PaperSizeEnum {
        Custom,  A0,  A1,  A2,  A3,  A4,  A5,  A6,  A7,  A8,  Letter,  Legal,  Ledger,
    };
    private PaperSizeEnum paperSize = null;
    private PadesSizeModel customPaperSize = null;
    public enum PageOrientationEnum {
        Auto,  Portrait,  Landscape,
    };
    private PageOrientationEnum pageOrientation = null;


    public PaperSizeEnum getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(PaperSizeEnum paperSize) {
        this.paperSize = paperSize;
    }

    public PadesSizeModel getCustomPaperSize() {
        return customPaperSize;
    }

    public void setCustomPaperSize(PadesSizeModel customPaperSize) {
        this.customPaperSize = customPaperSize;
    }

    public PageOrientationEnum getPageOrientation() {
        return pageOrientation;
    }

    public void setPageOrientation(PageOrientationEnum pageOrientation) {
        this.pageOrientation = pageOrientation;
    }
}
