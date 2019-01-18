package com.lacunasoftware.pkiexpress;


public class PdfHelper {

	public PdfContainerDefinition.Initial container() {
		return new PdfContainerDefinition.Initial();
	}

	public PdfMark mark() {
		return new PdfMark();
	}

	public PdfMarkTextElement textElement() {
		return new PdfMarkTextElement();
	}

	public PdfMarkImageElement imageElement() {
		return new PdfMarkImageElement();
	}

	public PdfMarkQRCodeElement qrCodeElement() {
		return new PdfMarkQRCodeElement();
	}

	public PdfTextSection textSection() {
		return textSection(null);
	}

	public PdfTextSection textSection(String text) {
		return new PdfTextSection(text);
	}
}
