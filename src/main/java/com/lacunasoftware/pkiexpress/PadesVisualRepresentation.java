package com.lacunasoftware.pkiexpress;

/**
 * PAdES signature visual representation specifications
 */
public class PadesVisualRepresentation {

    private PadesVisualText text;
    private PadesVisualImage image;
    private PadesVisualPositioning position;

    public PadesVisualRepresentation() {
    }

    /**
     * Creates a PAdES signature visual representation options
     * @param text The text specifications for the signature visual representation
     * @param image The image specifications for the signature visual representation
     * @param position The position specifications for the signature visual representation
     */
    public PadesVisualRepresentation(PadesVisualText text, PadesVisualImage image, PadesVisualPositioning position) {
        this.text = text;
        this.image = image;
        this.position = position;
    }

    public PadesVisualText getText() {
        return text;
    }

    /**
     * Sets the text specifications for the signature visual representation
     * @param text The text specifications for the signature visual representation
     */
    public void setText(PadesVisualText text) {
        this.text = text;
    }

    public PadesVisualImage getImage() {
        return image;
    }

    /**
     * Sets the image specifications for the signature visual representation
     * @param image The image spacification for the signature visual representation
     */
    public void setImage(PadesVisualImage image) {
        this.image = image;
    }

    public PadesVisualPositioning getPosition() {
        return position;
    }

    /**
     * Sets the position specifications for the signature visual representation
     * @param position The position specifications for the signature visual representation
     */
    public void setPosition(PadesVisualPositioning position) {
        this.position = position;
    }

    PadesVisualRepresentationModel toModel() {
        if (position == null) {
            throw new RuntimeException("The visual representation position was not set");
        }
        PadesVisualRepresentationModel model = new PadesVisualRepresentationModel();
        model.setPosition(position.toModel());
        if (text != null) {
            model.setText(text.toModel());
        }
        if (image != null) {
            model.setImage(image.toModel());
        }
        return model;
    }
}
