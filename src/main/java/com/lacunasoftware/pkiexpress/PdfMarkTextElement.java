package com.lacunasoftware.pkiexpress;


import java.util.ArrayList;
import java.util.List;


public class PdfMarkTextElement extends PdfMarkElement<PdfMarkTextElement> {
	private List<PdfTextSection> textSections;
	private PadesHorizontalAlign align = PadesHorizontalAlign.Left;


	public PdfMarkTextElement() {
		super(PdfMarkElementType.Text);
		textSections = new ArrayList<PdfTextSection>();
	}

	public PdfMarkTextElement(PadesVisualRectangle relativeContainer, List<PdfTextSection> sections) {
		super(PdfMarkElementType.Text, relativeContainer);
		textSections = sections;
	}


	@Override
	public PdfMarkElementModel toModel() {
		PdfMarkElementModel model = super.toModel();
		model.setAlign(PdfMarkElementModel.AlignEnum.valueOf(align.toString()));
		List<PdfTextSectionModel> textSectionsModels = new ArrayList<PdfTextSectionModel>();
		for (PdfTextSection section : textSections) {
			textSectionsModels.add(section.toModel());
		}
		model.setTextSections(textSectionsModels);
		return model;
	}

	//region FluentApi

	public PdfMarkTextElement alignTextLeft() {
		this.align = PadesHorizontalAlign.Left;
		return this;
	}

	public PdfMarkTextElement alignTextRight() {
		this.align = PadesHorizontalAlign.Right;
		return this;
	}

	public PdfMarkTextElement alignTextCenter() {
		this.align = PadesHorizontalAlign.Center;
		return this;
	}

	public PdfMarkTextElement addSection(PdfTextSection section) {
		this.textSections.add(section);
		return this;
	}

	public PdfMarkTextElement addSection(String text) {
		this.textSections.add(new PdfTextSection(text));
		return this;
	}

	//endregion

	public List<PdfTextSection> getTextSections() {
		return textSections;
	}

	public void setTextSections(List<PdfTextSection> textSections) {
		this.textSections = textSections;
	}

	public PadesHorizontalAlign getAlign() {
		return align;
	}

	public void setAlign(PadesHorizontalAlign align) {
		this.align = align;
	}
}
