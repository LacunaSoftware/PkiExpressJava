package com.lacunasoftware.pkiexpress;

public class SignatureAlgorithmAndValue {

	private SignatureAlgorithm algorithm;
	private byte[] value;
	private String hexValue;

	SignatureAlgorithmAndValue(SignatureAlgorithmAndValueModel model) {
		this.algorithm = model.getAlgorithm();
		this.value = Util.decodeBase64(model.getValue());
		this.hexValue = model.getHexValue();
	}

	/**
	 * Returns the signature algorithm.
	 * @return the signature algorithm to be used
	 */
	public SignatureAlgorithm getAlgorithm() {
		return algorithm;
	}

	/**
	 * Returns the signature value.
	 * @return the signature value
	 */
	public byte[] getValue() {
		return value;
	}

	/**
	 * Returns the signature value on hexadecimal representation.
	 * @return the signature value on hexadecimal representation.
	 */
	public String getHexValue() {
		return hexValue;
	}
}
