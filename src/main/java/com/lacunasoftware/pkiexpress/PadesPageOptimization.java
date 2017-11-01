package com.lacunasoftware.pkiexpress;

/**
 * Page format optimization options for PAdES signature visual representation result.
 */
public class PadesPageOptimization {

    private PadesPaperSize paperSize;
    private PadesSize customPaperSize;
    private PadesPageOrientation pageOrientation;

    /**
     * Page optimization with default options
     */
    public PadesPageOptimization() {
        pageOrientation = PadesPageOrientation.Auto;
    }

    /**
     * Page optimization for specific paper size.
     * @param paperSize Paper size standard.
     */
    public PadesPageOptimization(PadesPaperSize paperSize) {
        this();
        this.paperSize = paperSize;
    }

    /**
     * Page optimization for custom paper size (that its not covered by PadesPaperSize standard options).
     * @param customPaperSize Custom paper size width and height
     */
    public PadesPageOptimization(PadesSize customPaperSize) {
        this();
        this.paperSize = PadesPaperSize.Custom;
        this.customPaperSize = customPaperSize;
    }

    public PadesPaperSize getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(PadesPaperSize paperSize) {
        this.paperSize = paperSize;
    }

    public PadesSize getCustomPaperSize() {
        return customPaperSize;
    }

    /**
     * Sets a custom paper size (that its not covered by PadesPaperSize standard options)
     * @param customPaperSize Custom paper size width and height
     */
    public void setCustomPaperSize(PadesSize customPaperSize) {
        this.customPaperSize = customPaperSize;
        this.paperSize = PadesPaperSize.Custom;
    }

    public PadesPageOrientation getPageOrientation() {
        return pageOrientation;
    }

    public void setPageOrientation(PadesPageOrientation pageOrientation) {
        this.pageOrientation = pageOrientation;
    }

    public PadesPageOptimizationModel toModel() {
        PadesPageOptimizationModel model = new PadesPageOptimizationModel();
        if (paperSize != null) {
            model.setPaperSize(PadesPageOptimizationModel.PaperSizeEnum.valueOf(paperSize.toString()));
        }
        if (paperSize == PadesPaperSize.Custom) {
            if (customPaperSize != null) {
                model.setCustomPaperSize(customPaperSize.toModel());
            } else {
                throw new IllegalStateException("The paperSize is set to Custom but no customPaperSize was set");
            }
        }
        if (pageOrientation != null) {
            model.setPageOrientation(PadesPageOptimizationModel.PageOrientationEnum.valueOf(pageOrientation.toString()));
        }
        return model;
    }
}

