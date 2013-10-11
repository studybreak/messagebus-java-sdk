package com.messagebus.client.spi;


import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportCreateResponse extends DefaultResponse {

    @JsonCreator
    public ReportCreateResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("reportKey") final String reportKey,
            @JsonProperty("reportQuota") final int reportQuota,
            @JsonProperty("reportQuotaRemaining") final int reportQuotaRemaining) {

        super(statusCode, statusMessage, statusTime);

        this.reportKey = reportKey;
        this.reportStatus = reportStatus;
        this.reportQuota = reportQuota;
        this.reportQuotaRemaining = reportQuotaRemaining;
    }

    public String getReportKey() {
        return reportKey;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public int getReportQuota() {
        return reportQuota;
    }

    public int getReportQuotaRemaining() {
        return reportQuotaRemaining;
    }

    private String reportKey;
    private String reportStatus;
    private int reportQuota;
    private int reportQuotaRemaining;

}
