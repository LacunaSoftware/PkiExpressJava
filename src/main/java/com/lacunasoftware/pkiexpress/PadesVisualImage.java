package com.lacunasoftware.pkiexpress;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Image settings for PAdES signature visual representation
 */
public class PadesVisualImage {
	private String url;
	private byte[] content;
	private String mimeType;
	private int opacity;
	private PadesHorizontalAlign horizontalAlign;
	private PadesVerticalAlign verticalAlign;


	/**
	 * Creates empty Image with default settings
	 */
	public PadesVisualImage() {
		opacity = 100;
		horizontalAlign = PadesHorizontalAlign.Center;
		verticalAlign = PadesVerticalAlign.Center;
	}

	/**
	 * Creates Image with default settings from image content bytes
	 *
	 * @param imageContent
	 * @param imageMimeType
	 */
	public PadesVisualImage(byte[] imageContent, String imageMimeType) {
		content = imageContent;
		mimeType = imageMimeType;
		opacity = 100;
		horizontalAlign = PadesHorizontalAlign.Center;
		verticalAlign = PadesVerticalAlign.Center;
	}

	/**
	 * Creates Image with default settings from an image URL's
	 *
	 * @param imageUrl
	 * @param imageMimeType
	 */
	public PadesVisualImage(String imageUrl, String imageMimeType) {
		url = imageUrl;
		mimeType = imageMimeType;
		opacity = 100;
		horizontalAlign = PadesHorizontalAlign.Center;
		verticalAlign = PadesVerticalAlign.Center;
	}


	public String getUrl() {
		return url;
	}

	/**
	 * Sets the URL to download the image
	 *
	 * @param url Image URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getContent() {
		return content;
	}

	/**
	 * Sets the image content bytes
	 *
	 * @param imageContent Image content bytes
	 */
	public void setContent(byte[] imageContent) {
		this.content = imageContent;
	}

	/**
	 * Sets the image stream
	 *
	 * @param imageStream Image stream
	 * @throws IOException
	 */
	public void setContent(InputStream imageStream) throws IOException {
		this.content = Util.readStream(imageStream);
	}

	public void setContent(String imagePath) throws IOException {
		this.content = Files.readAllBytes(Paths.get(imagePath));
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getOpacity() {
		return opacity;
	}

	/**
	 * Sets the image opacity value between 0 and 100 (0 for transparent, 100 for opaque). If not set, default is 100.
	 *
	 * @param opacity Opacity value (0 is transparent, 100 is opaque).
	 */
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}

	public PadesHorizontalAlign getHorizontalAlign() {
		return horizontalAlign;
	}

	/**
	 * Sets the image horizontal alignment inside the signature rectangle
	 *
	 * @param horizontalAlign
	 */
	public void setHorizontalAlign(PadesHorizontalAlign horizontalAlign) {
		this.horizontalAlign = horizontalAlign;
	}

	public PadesVerticalAlign getVerticalAlign() {
		return verticalAlign;
	}

	/**
	 * Sets the image vertical alignment inside the signature rectangle
	 *
	 * @param verticalAlign
	 */
	public void setVerticalAlign(PadesVerticalAlign verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	public PadesVisualImageModel toModel() {
		PadesVisualImageModel model = new PadesVisualImageModel();
		ResourceContentOrReference resource = new ResourceContentOrReference();
		resource.setMimeType(mimeType);
		if (content != null) {
			resource.setContent(new ObjectMapper().convertValue(content, String.class));
		} else if (url != null) {
			resource.setUrl(url);
		} else {
			throw new IllegalStateException("The image content was not set, neither its URL");
		}
		model.setResource(resource);
		model.setOpacity(opacity);
		model.setHorizontalAlign(PadesVisualImageModel.HorizontalAlignEnum.valueOf(horizontalAlign.toString()));
		model.setVerticalAlign(PadesVisualImageModel.VerticalAlignEnum.valueOf(verticalAlign.toString()));
		return model;
	}
}

