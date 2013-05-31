package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelsResponseResult {

    private String channelName;
    private String channelKey;
    private Boolean isDefault;
    private String defaultSessionKey;

    @JsonCreator
    public ChannelsResponseResult(
            @JsonProperty("channelName") String channelName,
            @JsonProperty("channelKey") String channelKey,
            @JsonProperty("isDefault") Boolean isDefault,
            @JsonProperty("defaultSessionKey") String defaultSessionKey) {

        this.channelName = channelName;
        this.channelKey = channelKey;
        this.isDefault = isDefault;
        this.defaultSessionKey = defaultSessionKey;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public boolean isDefault() {
        if (isDefault == null)
            return false;
        return isDefault.booleanValue();
    }

    public String getDefaultSessionKey() {
        return defaultSessionKey;
    }
}

