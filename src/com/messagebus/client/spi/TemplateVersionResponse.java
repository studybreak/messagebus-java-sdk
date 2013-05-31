package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateVersionResponse extends DefaultResponse {

    private String version;

    @JsonCreator
    public TemplateVersionResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("version") final String version) {
        super(statusCode, statusMessage, statusTime);
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
