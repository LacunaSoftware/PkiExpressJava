package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangesFileModel {
	List<PdfMarkModel> marks = new ArrayList<PdfMarkModel>();
	String measurementUnits = null;
	PadesPageOptimizationModel pageOptimization = null;


	@JsonProperty("marks")
	public List<PdfMarkModel> getMarks() {
		return marks;
	}
	public void setMarks(List<PdfMarkModel> marks) {
		this.marks = marks;
	}

	@JsonProperty("measurementUnits")
	public String getMeasurementUnits() {
		return measurementUnits;
	}
	public void setMeasurementUnits(String measurementUnits) {
		this.measurementUnits = measurementUnits;
	}

	@JsonProperty("pageOptimization")
	public PadesPageOptimizationModel getPageOptimization() {
		return pageOptimization;
	}
	public void setPageOptimization(PadesPageOptimizationModel pageOptimization) {
		this.pageOptimization = pageOptimization;
	}
}
