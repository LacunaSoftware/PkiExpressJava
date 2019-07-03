package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class PadesVisualImageModel {
	public enum HorizontalAlignEnum {
		Left, Center, Right,
	}

	public enum VerticalAlignEnum {
		Top, Center, Bottom,
	}


	private ResourceContentOrReference resource = null;
	private Integer opacity = null;
	private HorizontalAlignEnum horizontalAlign = null;
	private VerticalAlignEnum verticalAlign = null;


	@JsonProperty("resource")
	public ResourceContentOrReference getResource() {
		return resource;
	}
	public void setResource(ResourceContentOrReference resource) {
		this.resource = resource;
	}

	@JsonProperty("opacity")
	public Integer getOpacity() {
		return opacity;
	}
	public void setOpacity(Integer opacity) {
		this.opacity = opacity;
	}

	@JsonProperty("horizontalAlign")
	public HorizontalAlignEnum getHorizontalAlign() {
		return horizontalAlign;
	}
	public void setHorizontalAlign(HorizontalAlignEnum horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	@JsonProperty("verticalAlign")
	public VerticalAlignEnum getVerticalAlign() {
		return verticalAlign;
	}
	public void setVerticalAlign(VerticalAlignEnum verticalAlign) {
		this.verticalAlign = verticalAlign;
	}
}
