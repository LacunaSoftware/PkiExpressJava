package com.lacunasoftware.pkiexpress;

public class SignatureAlgorithmAndValueModel {

    private SignatureAlgorithmIdentifier algorithmIdentifier = null;
    private String value = null;
    private String hexValue = null;

    public SignatureAlgorithmIdentifier getAlgorithmIdentifier() {
        return algorithmIdentifier;
    }
    public void setAlgorithmIdentifier(SignatureAlgorithmIdentifier algorithmIdentifier) {
        this.algorithmIdentifier = algorithmIdentifier;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getHexValue() {
        return hexValue;
    }
    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;
    }
}
