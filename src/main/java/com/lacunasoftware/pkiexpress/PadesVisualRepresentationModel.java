package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class PadesVisualRepresentationModel {
	private PadesVisualTextModel text = null;
	private PadesVisualImageModel image = null;
	private PadesVisualPositioningModel position = null;


	@JsonProperty("text")
	public PadesVisualTextModel getText() {
		return text;
	}
	public void setText(PadesVisualTextModel text) {
		this.text = text;
	}

	@JsonProperty("image")
	public PadesVisualImageModel getImage() {
		return image;
	}
	public void setImage(PadesVisualImageModel image) {
		this.image = image;
	}

	@JsonProperty("position")
	public PadesVisualPositioningModel getPosition() {
		return position;
	}
	public void setPosition(PadesVisualPositioningModel position) {
		this.position = position;
	}
}
