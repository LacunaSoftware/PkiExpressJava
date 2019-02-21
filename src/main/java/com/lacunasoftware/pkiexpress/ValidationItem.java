package com.lacunasoftware.pkiexpress;


/**
 * An item of validation.
 */
public class ValidationItem {
	private ValidationItemTypes type;
	private String message;
	private String detail;
	private ValidationResults innerValidationResults;


	ValidationItem(ValidationItemModel model) {
		this.type = ValidationItemTypes.valueOf(model.getType().toString());
		this.message = model.getMessage();
		this.detail = model.getDetail();
		if (model.getInnerValidationResults() != null) {
			this.innerValidationResults = new ValidationResults(model.getInnerValidationResults());
		}
	}


	public ValidationItemTypes getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getDetail() {
		return detail;
	}

	public ValidationResults getInnerValidationResults() {
		return innerValidationResults;
	}

	@Override
	public String toString() {
		return toString(0);
	}

	String toString(int identationLevel) {
		String tab = Util.repeatChar('\t', identationLevel);
		StringBuilder text = new StringBuilder();
		//text.append(tab);
		text.append(message);
		if (!Util.isNullOrEmpty(detail)) {
			text.append(String.format(" (%s)", detail));
		}
		if (innerValidationResults != null) {
			text.append('\n');
			text.append(innerValidationResults.toString(identationLevel + 1));
		}
		return text.toString();
	}
}
