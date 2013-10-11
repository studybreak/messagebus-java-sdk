package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionsRenameResponse extends DefaultResponse {

    private String sessionName = null;

    public String getSessionName() {
        return sessionName;
    }

    @JsonCreator
    public SessionsRenameResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("sessionName") final String sessionName) {

        super(statusCode, statusMessage, statusTime);
        this.sessionName = sessionName;
    }
}