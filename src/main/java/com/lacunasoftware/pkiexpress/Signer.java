package com.lacunasoftware.pkiexpress;


import java.io.IOException;
import java.nio.file.Path;

abstract class Signer extends PkiExpressOperator {

    protected Path outputFilePath;
    protected String certThumb;


    public Signer(PkiExpressConfig config) {
        super(config);
    }

    public Signer() throws IOException {
        this(new PkiExpressConfig());
    }

    public void setOutputFile(Path path) {
        this.outputFilePath = path;
    }

    public void setCertificateThumbprint(String certThumb) {
        this.certThumb = certThumb;
    }

}
