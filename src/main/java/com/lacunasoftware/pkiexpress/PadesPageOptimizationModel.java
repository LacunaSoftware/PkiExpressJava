package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class PadesPageOptimizationModel {
	public enum PaperSizeEnum {
		Custom, A0, A1, A2, A3, A4, A5, A6, A7, A8, Letter, Legal, Ledger,
	}

	public enum PageOrientationEnum {
		Auto, Portrait, Landscape,
	}

	private PaperSizeEnum paperSize = null;
	private PadesSizeModel customPaperSize = null;
	private PageOrientationEnum pageOrientation = null;


	@JsonProperty("paperSize")
	public PaperSizeEnum getPaperSize() {
		return paperSize;
	}
	public void setPaperSize(PaperSizeEnum paperSize) {
		this.paperSize = paperSize;
	}

	@JsonProperty("customPaperSize")
	public PadesSizeModel getCustomPaperSize() {
		return customPaperSize;
	}
	public void setCustomPaperSize(PadesSizeModel customPaperSize) {
		this.customPaperSize = customPaperSize;
	}

	@JsonProperty("pageOrientation")
	public PageOrientationEnum getPageOrientation() {
		return pageOrientation;
	}
	public void setPageOrientation(PageOrientationEnum pageOrientation) {
		this.pageOrientation = pageOrientation;
	}
}
