package com.lacunasoftware.pkiexpress;


import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


public class PdfMark {
	private PadesVisualRectangle container;
	private double borderWidth;
	private Color borderColor = Color.BLACK;
	private Color backgroundColor;
	private List<PdfMarkElement> elements = new ArrayList<PdfMarkElement>();
	private PdfMarkPageOptions pageOption = PdfMarkPageOptions.AllPages;
	private int pageOptionNumber;


	public PdfMark() {
		backgroundColor = new Color(255, 255, 255, 0);
	}


	public PdfMarkModel toModel() {
		PdfMarkModel model = new PdfMarkModel();
		model.setContainer(container.toModel());
		model.setBackgroundColor(Util.convertColorToModel(backgroundColor));
		model.setBorderColor(Util.convertColorToModel(borderColor));
		model.setBorderWidth(borderWidth);
		model.setPageOption(PdfMarkModel.PageOptionEnum.valueOf(pageOption.toString()));
		model.setPageOptionNumber(pageOptionNumber);
		List<PdfMarkElementModel> elementsModels = new ArrayList<PdfMarkElementModel>();
		for (PdfMarkElement element : elements) {
			elementsModels.add(element.toModel());
		}
		model.setElements(elementsModels);
		return model;
	}

	//region FluentApi

	public PdfMark onContainer(PadesVisualRectangle container) {
		this.container = container;
		return this;
	}

	public PdfMark withBorderWidth(double borderWidth) {
		this.borderWidth = borderWidth;
		return this;
	}

	public PdfMark onAllPages() {
		this.pageOption = PdfMarkPageOptions.AllPages;
		return this;
	}

	public PdfMark onNewPage() {
		this.pageOption = PdfMarkPageOptions.NewPage;
		return this;
	}

	public PdfMark onSinglePage(int pageNumber) {
		this.pageOption = PdfMarkPageOptions.SinglePage;
		this.pageOptionNumber = pageNumber;
		return this;
	}

	public PdfMark onSinglePageFromEnd(int pageNumber) {
		this.pageOption = PdfMarkPageOptions.SinglePageFromEnd;
		this.pageOptionNumber = pageNumber;
		return this;
	}

	public PdfMark addElement(PdfMarkElement element) {
		this.elements.add(element);
		return this;
	}

	public PdfMark withBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		return this;
	}

	public PdfMark withBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}

	//endregion

	public PadesVisualRectangle getContainer() {
		return container;
	}

	public void setContainer(PadesVisualRectangle container) {
		this.container = container;
	}

	public double getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(double borderWidth) {
		this.borderWidth = borderWidth;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public List<PdfMarkElement> getElements() {
		return elements;
	}

	public void setElements(List<PdfMarkElement> elements) {
		this.elements = elements;
	}

	public PdfMarkPageOptions getPageOption() {
		return pageOption;
	}

	public void setPageOption(PdfMarkPageOptions pageOption) {
		this.pageOption = pageOption;
	}

	public int getPageOptionNumber() {
		return pageOptionNumber;
	}

	public void setPageOptionNumber(int pageOptionNumber) {
		this.pageOptionNumber = pageOptionNumber;
	}
}
