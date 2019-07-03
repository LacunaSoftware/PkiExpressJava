package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorModel {
	private Integer red = null;
	private Integer green = null;
	private Integer blue = null;
	private Double alpha = null;


	@JsonProperty("red")
	public Integer getRed() {
		return red;
	}
	public void setRed(Integer red) {
		this.red = red;
	}

	@JsonProperty("green")
	public Integer getGreen() {
		return green;
	}
	public void setGreen(Integer green) {
		this.green = green;
	}

	@JsonProperty("blue")
	public Integer getBlue() {
		return blue;
	}
	public void setBlue(Integer blue) {
		this.blue = blue;
	}

	@JsonProperty("alpha")
	public Double getAlpha() {
		return alpha;
	}
	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}
}
