package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

public class ChannelsResponse extends DefaultResponse {

    private List<ChannelsResponseResult> results;
    private int count;

    @JsonCreator
    public ChannelsResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("count") final int count,
            @JsonProperty("results") final List<ChannelsResponseResult> results) {

        super(statusCode, statusMessage, statusTime);
        this.results = results;
        this.count = count;
    }

    public List<ChannelsResponseResult> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }
}