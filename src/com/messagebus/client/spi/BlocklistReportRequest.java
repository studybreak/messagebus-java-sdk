package com.messagebus.client.spi;


import com.messagebus.client.v5.client.MessageBusReportingClient;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
public class BlocklistReportRequest {

    public BlocklistReportRequest(
            @JsonProperty("channelKey") String channelKey,@JsonProperty("format") MessageBusReportingClient.ReportFormat format) {
        this.channelKey = channelKey;
        this.format=format.name();
    }

    public BlocklistReportRequest(@JsonProperty("format") MessageBusReportingClient.ReportFormat format) {
        this.format=format.name();
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

    private String channelKey=null;
    private String format=null;
    private final String reportType="blocklist";

}
