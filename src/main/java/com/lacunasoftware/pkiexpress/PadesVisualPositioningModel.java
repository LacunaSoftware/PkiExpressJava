package com.lacunasoftware.pkiexpress;


class PadesVisualPositioningModel {
	public enum MeasurementUnitsEnum {
		Centimeters, PdfPoints,
	}

	private Integer pageNumber = null;
	private MeasurementUnitsEnum measurementUnits = null;
	private PadesPageOptimizationModel pageOptimization = null;
	private PadesVisualAutoPositioningModel auto = null;
	private PadesVisualRectangleModel manual = null;


	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public MeasurementUnitsEnum getMeasurementUnits() {
		return measurementUnits;
	}

	public void setMeasurementUnits(MeasurementUnitsEnum measurementUnits) {
		this.measurementUnits = measurementUnits;
	}

	public PadesPageOptimizationModel getPageOptimization() {
		return pageOptimization;
	}

	public void setPageOptimization(PadesPageOptimizationModel pageOptimization) {
		this.pageOptimization = pageOptimization;
	}

	public PadesVisualAutoPositioningModel getAuto() {
		return auto;
	}

	public void setAuto(PadesVisualAutoPositioningModel auto) {
		this.auto = auto;
	}

	public PadesVisualRectangleModel getManual() {
		return manual;
	}

	public void setManual(PadesVisualRectangleModel manual) {
		this.manual = manual;
	}
}
