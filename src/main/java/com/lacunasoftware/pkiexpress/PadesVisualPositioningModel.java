package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class PadesVisualPositioningModel {
	public enum MeasurementUnitsEnum {
		Centimeters, PdfPoints,
	}

	private Integer pageNumber = null;
	private MeasurementUnitsEnum measurementUnits = null;
	private PadesPageOptimizationModel pageOptimization = null;
	private PadesVisualAutoPositioningModel auto = null;
	private PadesVisualRectangleModel manual = null;


	@JsonProperty("pageNumber")
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	@JsonProperty("measurementUnits")
	public MeasurementUnitsEnum getMeasurementUnits() {
		return measurementUnits;
	}
	public void setMeasurementUnits(MeasurementUnitsEnum measurementUnits) {
		this.measurementUnits = measurementUnits;
	}

	@JsonProperty("pageOptimization")
	public PadesPageOptimizationModel getPageOptimization() {
		return pageOptimization;
	}
	public void setPageOptimization(PadesPageOptimizationModel pageOptimization) {
		this.pageOptimization = pageOptimization;
	}

	@JsonProperty("auto")
	public PadesVisualAutoPositioningModel getAuto() {
		return auto;
	}
	public void setAuto(PadesVisualAutoPositioningModel auto) {
		this.auto = auto;
	}

	@JsonProperty("manual")
	public PadesVisualRectangleModel getManual() {
		return manual;
	}
	public void setManual(PadesVisualRectangleModel manual) {
		this.manual = manual;
	}
}
