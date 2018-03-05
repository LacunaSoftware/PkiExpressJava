package com.lacunasoftware.pkiexpress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadesTimestampModel extends CadesSignatureModel {

    private Date genTime = null;
    private String serialNumber = null;
    private DigestAlgorithmAndValueModel messageImprint = null;

    public Date getGenTime() {
        return genTime;
    }
    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DigestAlgorithmAndValueModel getMessageImprint() {
        return messageImprint;
    }
    public void setMessageImprint(DigestAlgorithmAndValueModel messageImprint) {
        this.messageImprint = messageImprint;
    }
}
