package com.lacunasoftware.pkiexpress;

public class SignatureAlgorithmAndValue {

	private SignatureAlgorithmIdentifier algorithmIdentifier;
	private byte[] value;
	private String hexValue;

	SignatureAlgorithmAndValue(SignatureAlgorithmAndValueModel model) {

		this.algorithmIdentifier = model.getAlgorithmIdentifier();
		this.value = Util.decodeBase64(model.getValue());
		this.hexValue = model.getHexValue();
	}

	/**
	 * Returns the digest algorithm.
	 */
	public SignatureAlgorithmIdentifier getAlgorithmIdentifier() {
		return algorithmIdentifier;
	}

	/**
	 * Returns the digest value.
	 */
	public byte[] getValue() {
		return value;
	}

	/**
	 * Returns the digest value on hexadecimal representation.
	 */
	public String getHexValue() {
		return hexValue;
	}
}
