package com.lacunasoftware.pkiexpress;


class PadesVisualRepresentationModel {
	private PadesVisualTextModel text = null;
	private PadesVisualImageModel image = null;
	private PadesVisualPositioningModel position = null;


	public PadesVisualTextModel getText() {
		return text;
	}

	public void setText(PadesVisualTextModel text) {
		this.text = text;
	}

	public PadesVisualImageModel getImage() {
		return image;
	}

	public void setImage(PadesVisualImageModel image) {
		this.image = image;
	}

	public PadesVisualPositioningModel getPosition() {
		return position;
	}

	public void setPosition(PadesVisualPositioningModel position) {
		this.position = position;
	}
}
