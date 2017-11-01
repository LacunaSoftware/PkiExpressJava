package com.lacunasoftware.pkiexpress;

/**
 * Positioning class for PAdES signature visual representation
 */
public abstract class PadesVisualPositioning {

    private int pageNumber;
    private PadesMeasurementUnits measurementUnits = PadesMeasurementUnits.Centimeters;
    private PadesPageOptimization pageOptimization;

    public PadesVisualPositioning() {
    }

    public PadesVisualPositioning(int pageNumber, PadesMeasurementUnits measurementUnits) {
        this.pageNumber = pageNumber;
        this.measurementUnits = measurementUnits;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the page number of the document to insert the signature visual representation.
     * @param pageNumber The document page number to insert the signature visual representation.  Negative values
     *                   denote pages counted from the end of the document (-1 means last page). Zero denotes that, if
     *                   no signatures are present, the signature should be inserted in a new page appended to the
     *                   document.
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public PadesMeasurementUnits getMeasurementUnits() {
        return measurementUnits;
    }

    /**
     * Sets the measurement units of the elements sizes and positions set
     * @param measurementUnits The measurement units of the elements sizes and positions set
     */
    public void setMeasurementUnits(PadesMeasurementUnits measurementUnits) {
        this.measurementUnits = measurementUnits;
    }

    public PadesPageOptimization getPageOptimization() {
        return pageOptimization;
    }

    /**
     * Sets the page optimization options
     * @param pageOptimization Page optimization options
     */
    public void setPageOptimization(PadesPageOptimization pageOptimization) {
        this.pageOptimization = pageOptimization;
    }

    PadesVisualPositioningModel toModel() {
        PadesVisualPositioningModel model = new PadesVisualPositioningModel();
        model.setPageNumber(pageNumber);
        model.setMeasurementUnits(PadesVisualPositioningModel.MeasurementUnitsEnum.valueOf(measurementUnits.toString()));
        if (pageOptimization != null) {
            model.setPageOptimization(pageOptimization.toModel());
        }
        return model;
    }
}

