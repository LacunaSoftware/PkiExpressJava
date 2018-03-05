package com.lacunasoftware.pkiexpress;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a digest algorithm
 */
public enum DigestAlgorithm {

    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512");

    private String name;

    private DigestAlgorithm(String name) {
        this.name = name;
    }

    static DigestAlgorithm getInstanceByApiModel(DigestAlgorithmAndValueModel.AlgorithmEnum model) {
        switch (model) {
            case MD5:
                return MD5;
            case SHA1:
                return SHA1;
            case SHA256:
                return SHA256;
            case SHA384:
                return SHA384;
            case SHA512:
                return SHA512;
            default:
                throw new RuntimeException("Unsupported digest algorithm: " + model); // should not happen
        }
    }

    /**
     * Returns the name of the digest algorithm.
     * @return Name of the digest algorithm.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a Service Provider Interface (SPI) corresponding to the digest algorithm.
     * @return An instance of the MessageDigest class (the SPI) corresponding to the digest algorithm.
     */
    public MessageDigest getSpi() {
        try {
            return MessageDigest.getInstance(name);
        } catch (NoSuchAlgorithmException e) {
            // should not happen
            throw new RuntimeException("Could not get MessageDigest instance for algorithm " + name, e);
        }
    }

    DigestAlgorithmAndValueModel.AlgorithmEnum getDigestAlgorithmAndValueModelEnum() {
        switch (this) {
            case MD5:
                return DigestAlgorithmAndValueModel.AlgorithmEnum.MD5;
            case SHA1:
                return DigestAlgorithmAndValueModel.AlgorithmEnum.SHA1;
            case SHA256:
                return DigestAlgorithmAndValueModel.AlgorithmEnum.SHA256;
            case SHA384:
                return DigestAlgorithmAndValueModel.AlgorithmEnum.SHA384;
            case SHA512:
                return DigestAlgorithmAndValueModel.AlgorithmEnum.SHA512;
            default:
                throw new RuntimeException(); // should not happen
        }
    }
}
