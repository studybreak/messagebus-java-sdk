package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplatesResponse extends DefaultResponse {

    private final List<TemplateResponseResult> templates;

    @JsonCreator
    public TemplatesResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("templates") final List<TemplateResponseResult> templates) {
        super(statusCode, statusMessage, statusTime);
        this.templates = templates;

    }

    public List<TemplateResponseResult> getTemplates() {
        return templates;
    }
}