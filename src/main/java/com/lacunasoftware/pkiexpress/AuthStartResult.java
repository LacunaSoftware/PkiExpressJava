package com.lacunasoftware.pkiexpress;

public class AuthStartResult {

    private String nonce;
    private String digestAlgorithm;
    private String digestAlgorithmOid;

    AuthStartResult(String toSignData, String digestAlgorithmName, String digestAlgorithmOid) {
        this.nonce = toSignData;
        this.digestAlgorithm = digestAlgorithmName;
        this.digestAlgorithmOid = digestAlgorithmOid;
    }

    AuthStartResult(AuthStartModel model) {
        this(
            model.getToSignData(),
            model.getDigestAlgorithmName(),
            model.getDigestAlgorithmOid()
        );
    }

    public String getNonce() {
        return nonce;
    }

    public String getDigestAlgorithm() {
        return digestAlgorithm;
    }

    public String getDigestAlgorithmOid() {
        return digestAlgorithmOid;
    }
}
