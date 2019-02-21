package com.lacunasoftware.pkiexpress;


/**
 * Width and Height sizes for PAdES signature visual representation elements
 */
public class PadesSize {
	private double height;
	private double width;


	public PadesSize(double width, double height) {
		this.height = height;
		this.width = width;
	}


	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public PadesSizeModel toModel() {
		PadesSizeModel model = new PadesSizeModel();
		model.setWidth(width);
		model.setHeight(height);
		return model;
	}
}

