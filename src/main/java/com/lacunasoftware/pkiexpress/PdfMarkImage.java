package com.lacunasoftware.pkiexpress;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfMarkImage {

    private String url;
    private String content;
    private String mimeType;
    private int alpha = 100;

    public PdfMarkImage() {
    }

    public PdfMarkImage(byte[] imageContent, String mimeType) {
        this();
        content = Util.encodeBase64(imageContent);
        this.mimeType = mimeType;
    }

    public PdfMarkImage(String imageUrl, String mimeType) {
        this();
        url = imageUrl;
        this.mimeType = mimeType;
    }

    public void setContentFromFile(InputStream stream) throws IOException {
        content = Util.encodeBase64(Util.readStream(stream));
    }

    public void setContentFromFile(byte[] content) throws IOException {
        setContentFromFile(new ByteArrayInputStream(content, 0, content.length));
    }

    public void setContentFromFile(Path path) throws IOException {
        content = Util.encodeBase64(Files.readAllBytes(path));
    }

    public void setContentFromFile(String filePath) throws IOException {
        setContentFromFile(Paths.get(filePath));
    }

    public PdfMarkImageModel toModel() {
        PdfMarkImageModel model = new PdfMarkImageModel();
        model.setOpacity(alpha/1.0); // opacity in Rest PKI is 0-100
        if (content != null) {
            ResourceContentOrReference resource = new ResourceContentOrReference();
            resource.setContent(content);
            resource.setMimeType(mimeType);
            model.setResource(resource);
        } else if (url != null && url.length() > 0) {
            ResourceContentOrReference resource = new ResourceContentOrReference();
            resource.setUrl(url);
            resource.setMimeType(mimeType);
            model.setResource(resource);
        } else {
            throw new RuntimeException("The image content was not set, neither its URL");
        }

        return model;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getMimeType() {
        return mimeType;
    }
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getAlpha() {
        return alpha;
    }
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
