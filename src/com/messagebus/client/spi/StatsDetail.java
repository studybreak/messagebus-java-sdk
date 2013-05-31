package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsDetail {

    @JsonCreator
    public StatsDetail(@JsonProperty("msgsAttemptedCount") final int msgsAttemptedCount,
                       @JsonProperty("complaintCount") final Integer complaintCount,
                       @JsonProperty("unsubscribeCount") final Integer unsubscribeCount,
                       @JsonProperty("clickCount") final Integer clickCount,
                       @JsonProperty("uniqueClickCount") final Integer uniqueClickCount,
                       @JsonProperty("openCount") final Integer openCount,
                       @JsonProperty("uniqueOpenCount") final Integer uniqueOpenCount
    ) {
        this.msgsAttemptedCount = msgsAttemptedCount;

        this.complaintCount = complaintCount;

        this.unsubscribeCount = unsubscribeCount;

        this.clickCount = clickCount;

        this.uniqueClickCount = uniqueClickCount;

        this.openCount = openCount;

        this.uniqueOpenCount = uniqueOpenCount;

    }

    private Integer msgsAttemptedCount = null;
    private Integer complaintCount = null;
    private Integer unsubscribeCount = null;
    private Integer clickCount = null;
    private Integer uniqueClickCount = null;
    private Integer openCount = null;
    private Integer uniqueOpenCount = null;

    public Integer getMsgsAttemptedCount() {
        return msgsAttemptedCount;
    }

    public Integer getComplaintCount() {
        return complaintCount;
    }

    public Integer getUnsubscribeCount() {
        return unsubscribeCount;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public Integer getUniqueClickCount() {
        return uniqueClickCount;
    }

    public Integer getOpenCount() {
        return openCount;
    }

    public Integer getUniqueOpenCount() {
        return uniqueOpenCount;
    }

}