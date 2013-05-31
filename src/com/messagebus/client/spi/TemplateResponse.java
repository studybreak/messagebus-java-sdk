package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateResponse extends DefaultResponse {

    private final TemplateResponseResult template;

    @JsonCreator
    public TemplateResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("template") final TemplateResponseResult template) {
        super(statusCode, statusMessage, statusTime);
        this.template = template;

    }

    public TemplateResponseResult getTemplate() {
        return template;
    }
}