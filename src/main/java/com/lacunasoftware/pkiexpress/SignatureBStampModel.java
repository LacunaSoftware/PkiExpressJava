package com.lacunasoftware.pkiexpress;

import java.util.Date;
import java.util.List;

public class SignatureBStampModel {

    public enum BlockchainsEnum {
        Bitcoin
    }
    private List<DigestAlgorithmAndValueModel> documentDigests;
    private List<DigestAlgorithmAndValueModel> indexDigests;
    private FileModel indexFile;
    private BlockchainsEnum blockchain;
    private String transactionId;
    private long blockNumber;
    private Date blockDate;

    public List<DigestAlgorithmAndValueModel> getDocumentDigests() {
        return documentDigests;
    }

    public void setDocumentDigests(List<DigestAlgorithmAndValueModel> documentDigests) {
        this.documentDigests = documentDigests;
    }

    public List<DigestAlgorithmAndValueModel> getIndexDigests() {
        return indexDigests;
    }

    public void setIndexDigests(List<DigestAlgorithmAndValueModel> indexDigests) {
        this.indexDigests = indexDigests;
    }

    public FileModel getIndexFile() {
        return indexFile;
    }

    public void setIndexFile(FileModel indexFile) {
        this.indexFile = indexFile;
    }

    public BlockchainsEnum getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(BlockchainsEnum blockchain) {
        this.blockchain = blockchain;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Date getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(Date blockDate) {
        this.blockDate = blockDate;
    }
}
