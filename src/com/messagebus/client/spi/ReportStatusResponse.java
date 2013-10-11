package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportStatusResponse extends DefaultResponse {

    @JsonCreator
    public ReportStatusResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("reportStatus") final String reportStatus) {

        super(statusCode, statusMessage, statusTime);

        this.reportStatus = reportStatus;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public boolean isSuccessful(){
        return reportStatus!=null && reportStatus.equals("done");
    }

    public boolean isEmpty(){
        return reportStatus!=null && reportStatus.equals("nodata");
    }

    public boolean isFailed(){
        return reportStatus!=null && reportStatus.equals("failed");
    }

    public boolean isFinished() {
        return isSuccessful() || isEmpty() || isFailed();
    }

    private String reportStatus=null;

}
