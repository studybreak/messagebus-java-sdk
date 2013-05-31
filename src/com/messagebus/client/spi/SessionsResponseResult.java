package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionsResponseResult {

    private String sessionKey;
    private String sessionName;
    private boolean isDefault = false;

    @JsonCreator
    public SessionsResponseResult(
            @JsonProperty("sessionKey") String sessionKey,
            @JsonProperty("sessionName") String sessionName,
            @JsonProperty("isDefault") Boolean isDefault) {

        this.sessionKey = sessionKey;
        this.sessionName = sessionName;
        if (isDefault != null)
            this.isDefault = isDefault.booleanValue();
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getSessionName() {
        return sessionName;
    }

}

