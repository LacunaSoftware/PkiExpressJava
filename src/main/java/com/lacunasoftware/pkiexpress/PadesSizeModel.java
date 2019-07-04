package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class PadesSizeModel {
	private Double height = null;
	private Double width = null;


	@JsonProperty("height")
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}

	@JsonProperty("width")
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
}
