package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class PadesVisualRectangleModel {
	private Double left = null;
	private Double top = null;
	private Double right = null;
	private Double bottom = null;
	private Double width = null;
	private Double height = null;


	@JsonProperty("left")
	public Double getLeft() {
		return left;
	}
	public void setLeft(Double left) {
		this.left = left;
	}

	@JsonProperty("top")
	public Double getTop() {
		return top;
	}
	public void setTop(Double top) {
		this.top = top;
	}

	@JsonProperty("right")
	public Double getRight() {
		return right;
	}
	public void setRight(Double right) {
		this.right = right;
	}

	@JsonProperty("bottom")
	public Double getBottom() {
		return bottom;
	}
	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}

	@JsonProperty("width")
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}

	@JsonProperty("height")
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
}
