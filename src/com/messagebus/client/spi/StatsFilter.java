package com.messagebus.client.spi;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsFilter {

    @JsonCreator
    public StatsFilter(@JsonProperty("rcptChannelBlockCount") final Integer rcptChannelBlockCount,
                       @JsonProperty("rcptBadMailboxCount") final Integer rcptBadMailboxCount) {
        this.rcptChannelBlockCount = rcptChannelBlockCount;
        this.rcptBadMailboxCount = rcptBadMailboxCount;
    }

    private Integer rcptChannelBlockCount = null;
    private Integer rcptBadMailboxCount = null;

    public Integer getRcptChannelBlockCount() {
        return rcptChannelBlockCount;
    }

    public Integer getRcptBadMailboxCount() {
        return rcptBadMailboxCount;
    }

}