package com.messagebus.client.spi;


import com.messagebus.client.v5.client.MessageBusReportingClient;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@JsonPropertyOrder(alphabetic = true)
public class FeedbackReportRequest {

    SimpleDateFormat sdf = null;

    void initDateFormatter(){
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }


    public FeedbackReportRequest(
            MessageBusReportingClient.FeedbackReportScopeType scope,
            Date startDate,
            Date endDate,
            MessageBusReportingClient.ReportFormat format
    ) {
        initDateFormatter();
        this.format=format.name();

        this.scope = scope.name().toLowerCase();
        if (startDate!=null)
            this.startDate=sdf.format(startDate);
        if (endDate!=null)
            this.endDate=sdf.format(endDate);
    }

    public FeedbackReportRequest(
            MessageBusReportingClient.FeedbackReportScopeType scope,
            Date startDate,
            Date endDate,
            String channelKey,
            MessageBusReportingClient.ReportFormat format
    ) {
        initDateFormatter();
        this.channelKey = channelKey;
        this.format=format.name();
        this.scope = scope.name().toLowerCase();
        if (startDate!=null)
            this.startDate=sdf.format(startDate);
        if (endDate!=null)
            this.endDate=sdf.format(endDate);
    }

    public FeedbackReportRequest(
            @JsonProperty("scope") MessageBusReportingClient.FeedbackReportScopeType scope,
            @JsonProperty("startDate") Date startDate,
            @JsonProperty("endDate") Date endDate,
            @JsonProperty("channelKey") String channelKey,
            @JsonProperty("sessionKey") String sessionKey,
            @JsonProperty("format") MessageBusReportingClient.ReportFormat format
            ) {
        initDateFormatter();
        this.channelKey = channelKey;
        this.sessionKey=sessionKey;
        this.format=format.name();
        this.scope = scope.name().toLowerCase();
        if (startDate!=null)
            this.startDate=sdf.format(startDate);
        if (endDate!=null)
            this.endDate=sdf.format(endDate);
    }

    public String getReportType() {
        return reportType;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    private final String reportType="feedback";
    private String channelKey=null;
    private String sessionKey=null;
    private String format=null;
    private String scope=null;
    private String startDate=null;
    private String endDate=null;

}
