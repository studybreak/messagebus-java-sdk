package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateListItemResult {

    public String templateKey;
    public Integer size;
    private Date modificationTime;

    @JsonCreator
    public TemplateListItemResult(
            @JsonProperty("templateKey") String templateKey,
            @JsonProperty("size") Integer size,
            @JsonProperty("modificationTime") Date modificationTime) {

        this.templateKey = templateKey;
        this.modificationTime=modificationTime;
        this.size=size;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public Integer getSize() {
        return size;
    }

}
