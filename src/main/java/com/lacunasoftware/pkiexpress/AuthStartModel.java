package com.lacunasoftware.pkiexpress;

class AuthStartModel {

    private String toSignData;
    private String digestAlgorithmName;
    private String digestAlgorithmOid;


    String getToSignData() {
        return toSignData;
    }

    void setToSignData(String toSignData) {
        this.toSignData = toSignData;
    }

    String getDigestAlgorithmName() {
        return digestAlgorithmName;
    }

    void setDigestAlgorithmName(String digestAlgorithmName) {
        this.digestAlgorithmName = digestAlgorithmName;
    }

    String getDigestAlgorithmOid() {
        return digestAlgorithmOid;
    }

    void setDigestAlgorithmOid(String digestAlgorithmOid) {
        this.digestAlgorithmOid = digestAlgorithmOid;
    }
}
