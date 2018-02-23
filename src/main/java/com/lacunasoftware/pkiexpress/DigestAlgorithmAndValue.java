package com.lacunasoftware.pkiexpress;

/**
 * Represents a digest algorithm and value.
 */
public class DigestAlgorithmAndValue {

    private DigestAlgorithm algorithm;
    private byte[] value;
    private String hexValue;

    DigestAlgorithmAndValue(DigestAlgorithmAndValueModel model) {
        this.algorithm = DigestAlgorithm.getInstanceByApiModel(model.getAlgorithm());
        this.value = Util.decodeBase64(model.getValue());
        this.hexValue = model.getHexValue();
    }

    /**
     * Returns the digest algorithm.
     */
    public DigestAlgorithm getAlgorithm() {
        return algorithm;
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
