package com.lacunasoftware.pkiexpress;

public class KeyUsage {
	public static final int NONE = 0;
	public static final int ENCIPHER_ONLY = 1;
	public static final int CRL_SIGN = 2;
	public static final int KEY_CERT_SIGN = 4;
	public static final int KEY_AGREEMENT = 8;
	public static final int DATA_ENCIPHERMENT = 16;
	public static final int KEY_ENCIPHERMENT = 32;
	public static final int NON_REPUDIATION = 64;
	public static final int DIGITAL_SIGNATURE = 128;
	public static final int DECIPHER_ONLY = 32768;

	private int value;

	public KeyUsage(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public boolean contains(int flag) {
		return (this.value & flag) == flag;
	}

	public void add(int flag) {
		this.value = this.value | flag;
	}
}
