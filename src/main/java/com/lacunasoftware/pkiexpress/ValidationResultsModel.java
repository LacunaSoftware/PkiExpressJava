package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationResultsModel {
	private List<ValidationItemModel> passedChecks = new ArrayList<ValidationItemModel>();
	private List<ValidationItemModel> errors = new ArrayList<ValidationItemModel>();
	private List<ValidationItemModel> warnings = new ArrayList<ValidationItemModel>();


	@JsonProperty("passedChecks")
	public List<ValidationItemModel> getPassedChecks() {
		return passedChecks;
	}
	public void setPassedChecks(List<ValidationItemModel> passedChecks) {
		this.passedChecks = passedChecks;
	}

	@JsonProperty("errors")
	public List<ValidationItemModel> getErrors() {
		return errors;
	}
	public void setErrors(List<ValidationItemModel> errors) {
		this.errors = errors;
	}

	@JsonProperty("warnings")
	public List<ValidationItemModel> getWarnings() {
		return warnings;
	}
	public void setWarnings(List<ValidationItemModel> warnings) {
		this.warnings = warnings;
	}
}
