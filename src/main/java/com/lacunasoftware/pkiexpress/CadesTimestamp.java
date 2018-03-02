package com.lacunasoftware.pkiexpress;

import java.math.BigInteger;
import java.util.Date;

/**
 * Represents a CAdES timestamp, which is itself a CAdES signature.
 */
public class CadesTimestamp extends CadesSignature {

    private Date genTime;
    private BigInteger serialNumber;
    private DigestAlgorithmAndValue messageImprint;

    CadesTimestamp(CadesTimestampModel model) {
        super(model);
        this.genTime = model.getGenTime();
        this.serialNumber = new BigInteger(model.getSerialNumber());
        this.messageImprint = new DigestAlgorithmAndValue(model.getMessageImprint());
    }

    public Date getGenTime() {
        return genTime;
    }

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    public DigestAlgorithmAndValue getMessageImprint() {
        return messageImprint;
    }
}
