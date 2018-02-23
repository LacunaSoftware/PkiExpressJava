package com.lacunasoftware.pkiexpress;

public class FileModel {

    private String mimeType = null;
    private String content = null;
    private String blobToken = null;
    private String url = null;

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

    public String getBlobToken() {
        return blobToken;
    }
    public void setBlobToken(String blobToken) {
        this.blobToken = blobToken;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
