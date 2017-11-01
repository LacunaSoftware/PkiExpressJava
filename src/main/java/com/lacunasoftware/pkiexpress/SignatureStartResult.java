package com.lacunasoftware.pkiexpress;

public class SignatureStartResult {

    private String toSignHash;
    private String digestAlgorithm;
    private String digestAlgorithmOid;
    private String transferFile;

    public String getToSignHash() {
        return toSignHash;
    }

    public void setToSignHash(String toSignHash) {
        this.toSignHash = toSignHash;
    }

    public String getDigestAlgorithm() {
        return digestAlgorithm;
    }

    public void setDigestAlgorithm(String digestAlgorithm) {
        this.digestAlgorithm = digestAlgorithm;
    }

    public String getDigestAlgorithmOid() {
        return digestAlgorithmOid;
    }

    public void setDigestAlgorithmOid(String digestAlgorithmOid) {
        this.digestAlgorithmOid = digestAlgorithmOid;
    }

    public String getTransferFile() {
        return transferFile;
    }

    public void setTransferFile(String transferFile) {
        this.transferFile = transferFile;
    }
}
