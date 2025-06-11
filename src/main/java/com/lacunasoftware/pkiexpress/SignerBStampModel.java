package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerBStampModel {
    private DigestAlgorithmAndValueModel signatureDigest;
    private List<DigestAlgorithmAndValueModel> crlsDigests = new ArrayList<>();
    private List<DigestAlgorithmAndValueModel> certificateDigests = new ArrayList<>();
    private Blockchains blockchain;
    private String transactionId;
    private Date transactionDate;

    @JsonProperty("signatureDigest")
    public DigestAlgorithmAndValueModel getSignatureDigest() {
        return signatureDigest;
    }
    public void setSignatureDigest(DigestAlgorithmAndValueModel signatureDigest) {
        this.signatureDigest = signatureDigest;
    }

    @JsonProperty("crlsDigests")
    public List<DigestAlgorithmAndValueModel> getCrlsDigests() {
        return crlsDigests;
    }
    public void setCrlsDigests(List<DigestAlgorithmAndValueModel> crlsDigests) {
        this.crlsDigests = crlsDigests;
    }

    @JsonProperty("certificateDigests")
    public List<DigestAlgorithmAndValueModel> getCertificateDigests() {
        return certificateDigests;
    }
    public void setCertificateDigests(List<DigestAlgorithmAndValueModel> certificateDigests) {
        this.certificateDigests = certificateDigests;
    }

    @JsonProperty("blockchain")
    public Blockchains getBlockchain() {
        return blockchain;
    }
    public void setBlockchain(Blockchains blockchain) {
        this.blockchain = blockchain;
    }

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("transactionDate")
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
} 