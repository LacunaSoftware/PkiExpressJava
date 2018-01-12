package com.lacunasoftware.pkiexpress;


import java.io.IOException;
import java.nio.file.Path;

abstract class Signer extends PkiExpressOperator {

    protected Path outputFilePath;
    protected String certThumb;
    protected Boolean offline = false;


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

    public Boolean getOffline() {
        return offline;
    }

    public void setOffline(Boolean offline) {
        this.offline = offline;
    }
}
