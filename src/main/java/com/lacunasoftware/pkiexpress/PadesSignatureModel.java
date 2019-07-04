package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PadesSignatureModel extends SignatureModel {
	private List<PadesSignerModel> signers = new ArrayList<PadesSignerModel>();


	@JsonProperty("signers")
	public List<PadesSignerModel> getSigners() {
		return signers;
	}
	public void setSigners(List<PadesSignerModel> signers) {
		this.signers = signers;
	}
}
