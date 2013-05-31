package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateCreateResponse extends DefaultResponse {

    private String templateKey;

    @JsonCreator
    public TemplateCreateResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("templateKey") String templateKey
    ) {

        super(statusCode, statusMessage, statusTime);
        this.templateKey = templateKey;
    }

    public String getTemplateKey() {
        return templateKey;
    }
}
