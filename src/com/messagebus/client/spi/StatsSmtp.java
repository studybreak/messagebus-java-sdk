package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsSmtp {

    @JsonCreator
    public StatsSmtp(
            @JsonProperty("acceptCount") final Integer acceptCount,
            @JsonProperty("bounceCount") final Integer bounceCount,
            @JsonProperty("deferralCount") final Integer deferralCount,
            @JsonProperty("rejectCount") final Integer rejectCount,
            @JsonProperty("errorCount") final Integer errorCount
    ) {
        this.acceptCount = acceptCount;
        this.bounceCount = bounceCount;
        this.deferralCount = deferralCount;
        this.rejectCount = rejectCount;
        this.errorCount = errorCount;
    }

    private Integer acceptCount = null;
    private Integer bounceCount = null;
    private Integer deferralCount = null;
    private Integer rejectCount = null;
    private Integer errorCount = null;

    public Integer getAcceptCount() {
        return acceptCount;
    }

    public Integer getDeferralCount() {
        return deferralCount;
    }

    public Integer getRejectCount() {
        return rejectCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public Integer getBounceCount() {
        return bounceCount;

    }

}