package com.lacunasoftware.pkiexpress;


import java.util.ArrayList;
import java.util.List;


public class ChangesFileModel {
	List<PdfMarkModel> marks = new ArrayList<PdfMarkModel>();
	String measurementUnits;
	PadesPageOptimizationModel pageOptimization;


	public List<PdfMarkModel> getMarks() {
		return marks;
	}

	public void setMarks(List<PdfMarkModel> marks) {
		this.marks = marks;
	}

	public String getMeasurementUnits() {
		return measurementUnits;
	}

	public void setMeasurementUnits(String measurementUnits) {
		this.measurementUnits = measurementUnits;
	}

	public PadesPageOptimizationModel getPageOptimization() {
		return pageOptimization;
	}

	public void setPageOptimization(PadesPageOptimizationModel pageOptimization) {
		this.pageOptimization = pageOptimization;
	}
}
