package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FileModel {
	private String mimeType = null;
	private String content = null;
	private String blobToken = null;
	private String url = null;


	@JsonProperty("mimeType")
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@JsonProperty("blobToken")
	public String getBlobToken() {
		return blobToken;
	}
	public void setBlobToken(String blobToken) {
		this.blobToken = blobToken;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
