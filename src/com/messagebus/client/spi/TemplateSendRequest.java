package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.util.List;
import java.util.Map;


@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
public class TemplateSendRequest {

    private String templateKey;
    private List<Map<String, String>> messages;

    @JsonCreator
    public TemplateSendRequest(
            @JsonProperty("templateKey") String templateKey,
            @JsonProperty("messages") List<Map<String, String>> messages) {

        this.templateKey = templateKey;
        this.messages = messages;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public List<Map<String, String>> getMessages() {
        return messages;
    }
}