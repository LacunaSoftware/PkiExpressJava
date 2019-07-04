package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PdfMarkImageModel {
	private Double opacity = null;
	private ResourceContentOrReference resource = null;


	@JsonProperty("opacity")
	public Double getOpacity() {
		return opacity;
	}
	public void setOpacity(Double opacity) {
		this.opacity = opacity;
	}

	@JsonProperty("resource")
	public ResourceContentOrReference getResource() {
		return resource;
	}
	public void setResource(ResourceContentOrReference resource) {
		this.resource = resource;
	}
}
