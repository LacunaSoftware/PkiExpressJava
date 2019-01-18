package com.lacunasoftware.pkiexpress;


/**
 * Rectangle dimensions values
 */
public class PadesVisualRectangle {
	private Double left;
	private Double top;
	private Double right;
	private Double bottom;
	private Double width;
	private Double height;


	public PadesVisualRectangle() {
	}


	public Double getLeft() {
		return left;
	}

	/**
	 * Sets the left margin length
	 *
	 * @param left Left margin length
	 */
	public void setLeft(Double left) {
		this.left = left;
	}

	public Double getTop() {
		return top;
	}

	/**
	 * Sets the top margin length
	 *
	 * @param top Top margin length
	 */
	public void setTop(Double top) {
		this.top = top;
	}

	public Double getRight() {
		return right;
	}

	/**
	 * Sets the right margin length
	 *
	 * @param right Right margin length
	 */
	public void setRight(Double right) {
		this.right = right;
	}

	public Double getBottom() {
		return bottom;
	}

	/**
	 * Sets the bottom margin length
	 *
	 * @param bottom Bottom margin length
	 */
	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}

	public Double getWidth() {
		return width;
	}

	/**
	 * Sets the width length
	 *
	 * @param width Width length
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	/**
	 * Sets the Height length
	 *
	 * @param height Height length
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	public void setWidthCentered(double width) {
		this.width = width;
		this.left = null;
		this.right = null;
	}

	public void setWidthLeftAnchored(double width, double left) {
		this.width = width;
		this.left = left;
		this.right = null;
	}

	public void setWidthRightAnchored(double width, double right) {
		this.width = width;
		this.left = null;
		this.right = right;
	}

	public void setHorizontalStretch(double left, double right) {
		this.width = null;
		this.left = left;
		this.right = right;
	}

	public void setHeightCentered(double height) {
		this.height = height;
		this.top = null;
		this.bottom = null;
	}

	public void setHeightTopAnchored(double height, double top) {
		this.height = height;
		this.top = top;
		this.bottom = null;
	}

	public void setHeightBottomAnchored(double height, double bottom) {
		this.height = height;
		this.top = null;
		this.bottom = bottom;
	}

	public void setVerticalStretch(double top, double bottom) {
		this.height = null;
		this.top = top;
		this.bottom = bottom;
	}

	public PadesVisualRectangleModel toModel() {
		PadesVisualRectangleModel model = new PadesVisualRectangleModel();
		model.setLeft(left);
		model.setTop(top);
		model.setRight(right);
		model.setBottom(bottom);
		model.setWidth(width);
		model.setHeight(height);
		return model;
	}
}

