package com.lacunasoftware.pkiexpress;


public class PdfContainerDefinition {

	public static class Initial {
		private PadesVisualRectangle container;


		Initial() {
			this.container = new PadesVisualRectangle();
		}


		public FixedWidth width(double width) {
			container.setWidth(width);
			return new FixedWidth(container);
		}

		public VarWidth varWidth() {
			return new VarWidth(container);
		}

		public WidthDefined fullWidth() {
			container.setLeft(0.0);
			container.setRight(0.0);
			return new WidthDefined(container);
		}

		public FixedHeight height(double height) {
			container.setHeight(height);
			return new FixedHeight(container);
		}

		public VarHeight varHeight() {
			return new VarHeight(container);
		}

		public HeightDefined fullHeight() {
			container.setTop(0.0);
			container.setBottom(0.0);
			return new HeightDefined(container);
		}

		public VarWidthAndHeight varWidthAndHeight() {
			return new VarWidthAndHeight(container);
		}

		public PadesVisualRectangle full() {
			container.setTop(0.0);
			container.setRight(0.0);
			container.setBottom(0.0);
			container.setLeft(0.0);
			return container;
		}
	}

	public static class FixedWidth {
		private PadesVisualRectangle container;


		FixedWidth(PadesVisualRectangle container) {
			this.container = container;
		}


		public WidthDefined anchorLeft() {
			return anchorLeft(0);
		}

		public WidthDefined anchorLeft(double margin) {
			container.setLeft(margin);
			return new WidthDefined(container);
		}

		public WidthDefined anchorRight() {
			return anchorRight(0);
		}

		public WidthDefined anchorRight(double margin) {
			container.setRight(margin);
			return new WidthDefined(container);
		}

		public WidthDefined center() {
			return new WidthDefined(container);
		}

	}

	public static class VarWidth {
		private PadesVisualRectangle container;


		VarWidth(PadesVisualRectangle container) {
			this.container = container;
		}


		public WidthDefined margins(double leftMargin, double rightMargin) {
			container.setLeft(leftMargin);
			container.setRight(rightMargin);
			return new WidthDefined(container);
		}

		public WidthDefined margins(double leftAndRightMargins) {
			container.setLeft(leftAndRightMargins);
			container.setRight(leftAndRightMargins);
			return new WidthDefined(container);
		}
	}

	public static class FixedHeight {
		private PadesVisualRectangle container;


		FixedHeight(PadesVisualRectangle container) {
			this.container = container;
		}


		public HeightDefined anchorTop() {
			return anchorTop(0.0);
		}

		public HeightDefined anchorTop(double margin) {
			container.setTop(margin);
			return new HeightDefined(container);
		}

		public HeightDefined anchorBottom() {
			return anchorBottom(0.0);
		}

		public HeightDefined anchorBottom(double margin) {
			container.setBottom(margin);
			return new HeightDefined(container);
		}

		public HeightDefined center() {
			return new HeightDefined(container);
		}

	}

	public static class VarHeight {
		private PadesVisualRectangle container;


		VarHeight(PadesVisualRectangle container) {
			this.container = container;
		}


		public HeightDefined margins(double topMargin, double bottomMargin) {
			container.setTop(topMargin);
			container.setBottom(bottomMargin);
			return new HeightDefined(container);
		}

		public HeightDefined margins(double topAndBottomMargin) {
			container.setTop(topAndBottomMargin);
			container.setBottom(topAndBottomMargin);
			return new HeightDefined(container);
		}

	}

	public static class WidthDefined {
		private PadesVisualRectangle container;


		WidthDefined(PadesVisualRectangle container) {
			this.container = container;
		}


		public WidthDefinedFixedHeight height(double height) {
			container.setHeight(height);
			return new WidthDefinedFixedHeight(container);
		}

		public WidthDefinedVarHeight varHeight() {
			return new WidthDefinedVarHeight(container);
		}

		public PadesVisualRectangle fullHeight() {
			container.setTop(0.0);
			container.setBottom(0.0);
			return container;
		}
	}

	public static class HeightDefined {
		private PadesVisualRectangle container;


		HeightDefined(PadesVisualRectangle container) {
			this.container = container;
		}


		public HeightDefinedFixedWidth width(double width) {
			container.setWidth(width);
			return new HeightDefinedFixedWidth(container);
		}

		public HeightDefinedVarWidth varWidth() {
			return new HeightDefinedVarWidth(container);
		}

		public PadesVisualRectangle fullWidth() {
			container.setLeft(0.0);
			container.setRight(0.0);
			return container;
		}
	}

	public static class WidthDefinedFixedHeight {
		private PadesVisualRectangle container;


		WidthDefinedFixedHeight(PadesVisualRectangle container) {
			this.container = container;
		}


		public PadesVisualRectangle anchorTop() {
			return anchorTop(0.0);
		}

		public PadesVisualRectangle anchorTop(double margin) {
			container.setTop(margin);
			return container;
		}

		public PadesVisualRectangle anchorBottom() {
			return anchorBottom(0.0);
		}

		public PadesVisualRectangle anchorBottom(double margin) {
			container.setBottom(margin);
			return container;
		}

		public PadesVisualRectangle center() {
			return container;
		}

	}

	public static class WidthDefinedVarHeight {
		private PadesVisualRectangle container;


		WidthDefinedVarHeight(PadesVisualRectangle container) {
			this.container = container;
		}


		public PadesVisualRectangle margins(double topMargin, double bottomMargin) {
			container.setTop(topMargin);
			container.setBottom(bottomMargin);
			return container;
		}

		public PadesVisualRectangle margins(double topAndBottomMargin) {
			container.setTop(topAndBottomMargin);
			container.setBottom(topAndBottomMargin);
			return container;
		}

	}

	public static class HeightDefinedFixedWidth {
		private PadesVisualRectangle container;


		HeightDefinedFixedWidth(PadesVisualRectangle container) {
			this.container = container;
		}


		public PadesVisualRectangle anchorLeft() {
			return anchorLeft(0.0);
		}

		public PadesVisualRectangle anchorLeft(double margin) {
			container.setLeft(margin);
			return container;
		}

		public PadesVisualRectangle anchorRight() {
			return anchorRight(0.0);
		}

		public PadesVisualRectangle anchorRight(double margin) {
			container.setRight(margin);
			return container;
		}

		public PadesVisualRectangle center() {
			return container;
		}

	}

	public static class HeightDefinedVarWidth {
		private PadesVisualRectangle container;


		HeightDefinedVarWidth(PadesVisualRectangle container) {
			this.container = container;
		}


		public PadesVisualRectangle margins(double leftMargin, double rightMargin) {
			container.setLeft(leftMargin);
			container.setRight(rightMargin);
			return container;
		}

		public PadesVisualRectangle margins(double leftAndRightMargins) {
			container.setLeft(leftAndRightMargins);
			container.setRight(leftAndRightMargins);
			return container;
		}

	}

	public static class VarWidthAndHeight {
		private PadesVisualRectangle container;


		VarWidthAndHeight(PadesVisualRectangle container) {
			this.container = container;
		}


		public PadesVisualRectangle margins(double topMargin, double rightMargin, double bottomMargin, double leftMargin) {
			container.setTop(topMargin);
			container.setRight(rightMargin);
			container.setBottom(bottomMargin);
			container.setLeft(leftMargin);
			return container;
		}

		public PadesVisualRectangle margins(double topAndBottomMargins, double leftAndRightMargins) {
			return margins(topAndBottomMargins, leftAndRightMargins, topAndBottomMargins, leftAndRightMargins);
		}

		public PadesVisualRectangle margins(double margins) {
			return margins(margins, margins);
		}

	}
}
