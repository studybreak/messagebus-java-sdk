package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

public class ClickResponse extends DefaultResponse {
    private final List<ClickResponseResult> clicks;

    @JsonCreator
    public ClickResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("clicks") final List<ClickResponseResult> clicks) {
        super(statusCode, statusMessage, statusTime);
        this.clicks = clicks;

    }

    public List<ClickResponseResult> getClicks() {
        return this.clicks;
    }
}