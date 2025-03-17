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
	 * Returns the digest algorithm.
	 * @return the algorithm to be used in the signature
	 */
	public SignatureAlgorithm getAlgorithm() {
		return algorithm;
	}

	/**
	 * Returns the digest value.
	 * @return the digest value
	 */
	public byte[] getValue() {
		return value;
	}

	/**
	 * Returns the digest value on hexadecimal representation.
	 * @return the digest value on hexadecimal representation.
	 */
	public String getHexValue() {
		return hexValue;
	}
}
