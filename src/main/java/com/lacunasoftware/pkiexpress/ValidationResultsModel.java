package com.lacunasoftware.pkiexpress;


import java.util.ArrayList;
import java.util.List;


public class ValidationResultsModel {
	private List<ValidationItemModel> passedChecks = new ArrayList<ValidationItemModel>();
	private List<ValidationItemModel> errors = new ArrayList<ValidationItemModel>();
	private List<ValidationItemModel> warnings = new ArrayList<ValidationItemModel>();


	public List<ValidationItemModel> getPassedChecks() {
		return passedChecks;
	}

	public void setPassedChecks(List<ValidationItemModel> passedChecks) {
		this.passedChecks = passedChecks;
	}

	public List<ValidationItemModel> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationItemModel> errors) {
		this.errors = errors;
	}

	public List<ValidationItemModel> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<ValidationItemModel> warnings) {
		this.warnings = warnings;
	}
}
