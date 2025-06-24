package com.lacunasoftware.pkiexpress;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendXmlArchivingCommandResult {
    @JsonProperty("extended")
    private boolean extended;

    @JsonProperty("currentEndDateTime")
    private Date currentEndDateTime;

    @JsonProperty("currentArchiveTimestamp")
    private CadesTimestampModel currentArchiveTimestamp;

    @JsonProperty("extendedEndDateTime")
    private Date extendedEndDateTime;

    @JsonProperty("extendedArchiveTimestamp")
    private CadesTimestampModel extendedArchiveTimestamp;

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public Date getCurrentEndDateTime() {
        return currentEndDateTime;
    }

    public void setCurrentEndDateTime(Date currentEndDateTime) {
        this.currentEndDateTime = currentEndDateTime;
    }

    public CadesTimestampModel getCurrentArchiveTimestamp() {
        return currentArchiveTimestamp;
    }

    public void setCurrentArchiveTimestamp(CadesTimestampModel currentArchiveTimestamp) {
        this.currentArchiveTimestamp = currentArchiveTimestamp;
    }

    public Date getExtendedEndDateTime() {
        return extendedEndDateTime;
    }

    public void setExtendedEndDateTime(Date extendedEndDateTime) {
        this.extendedEndDateTime = extendedEndDateTime;
    }

    public CadesTimestampModel getExtendedArchiveTimestamp() {
        return extendedArchiveTimestamp;
    }

    public void setExtendedArchiveTimestamp(CadesTimestampModel extendedArchiveTimestamp) {
        this.extendedArchiveTimestamp = extendedArchiveTimestamp;
    }
}
