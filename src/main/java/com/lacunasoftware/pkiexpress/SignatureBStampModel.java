package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SignatureBStampModel {
	public enum BlockchainsEnum {
		Bitcoin
	}

	private List<DigestAlgorithmAndValueModel> documentDigests = new ArrayList<DigestAlgorithmAndValueModel>();
	private List<DigestAlgorithmAndValueModel> indexDigests = new ArrayList<DigestAlgorithmAndValueModel>();
	private FileModel indexFile = null;
	private BlockchainsEnum blockchain = null;
	private String transactionId = null;
	private Long blockNumber = null;
	private Date blockDate = null;


	@JsonProperty("documentDigests")
	public List<DigestAlgorithmAndValueModel> getDocumentDigests() {
		return documentDigests;
	}
	public void setDocumentDigests(List<DigestAlgorithmAndValueModel> documentDigests) {
		this.documentDigests = documentDigests;
	}

	@JsonProperty("indexDigests")
	public List<DigestAlgorithmAndValueModel> getIndexDigests() {
		return indexDigests;
	}
	public void setIndexDigests(List<DigestAlgorithmAndValueModel> indexDigests) {
		this.indexDigests = indexDigests;
	}

	@JsonProperty("indexFile")
	public FileModel getIndexFile() {
		return indexFile;
	}
	public void setIndexFile(FileModel indexFile) {
		this.indexFile = indexFile;
	}

	@JsonProperty("blockchain")
	public BlockchainsEnum getBlockchain() {
		return blockchain;
	}
	public void setBlockchain(BlockchainsEnum blockchain) {
		this.blockchain = blockchain;
	}

	@JsonProperty("transactionId")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@JsonProperty("blockNumber")
	public long getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(long blockNumber) {
		this.blockNumber = blockNumber;
	}

	@JsonProperty("blockDate")
	public Date getBlockDate() {
		return blockDate;
	}
	public void setBlockDate(Date blockDate) {
		this.blockDate = blockDate;
	}
}
