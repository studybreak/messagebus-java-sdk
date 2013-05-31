package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class SessionsCreateResponse extends DefaultResponse {

    private String sessionKey;

    private String sessionName;

    @JsonCreator
    public SessionsCreateResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("sessionKey") final String sessionKey,
            @JsonProperty("sessionName") final String sessionName) {

        super(statusCode, statusMessage, statusTime);

        this.sessionKey = sessionKey;
        this.sessionName = sessionName;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public String getSessionName() {
        return this.sessionName;
    }
}