package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
public class SessionsCreateRequest {

    private String sessionName;

    public SessionsCreateRequest(
            @JsonProperty("sessionName") String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
}