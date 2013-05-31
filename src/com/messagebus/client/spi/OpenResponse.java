package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

public class OpenResponse extends DefaultResponse {
    private final List<OpenResponseResult> opens;

    @JsonCreator
    public OpenResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("opens") final List<OpenResponseResult> opens) {
        super(statusCode, statusMessage, statusTime);
        this.opens = opens;

    }

    public List<OpenResponseResult> getOpens() {
        return this.opens;
    }
}