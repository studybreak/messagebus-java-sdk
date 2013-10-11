package com.messagebus.client.spi;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;


public class ReportCreateResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {
        final String returnedJson =
                "{\n" +
                        "    \"statusCode\"           : 201,\n" +
                        "    \"statusMessage\"        : \"Report request received.\",\n" +
                        "    \"statusTime\"           : \"2011-09-19T22:40:45.123\",\n" +
                        "    \"reportKey\"            : \"SampleReportKey\",\n" +
                        "    \"reportStatus\"         : \"created\",\n" +
                        "    \"reportQuota\"          : 86400,\n" +
                        "    \"reportQuotaRemaining\" : 86399\n" +
                        "}";

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
        final ReportCreateResponse response = (ReportCreateResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, ReportCreateResponse.class);

        assertEquals(201, response.getStatusCode());
        assertEquals("Report request received.", response.getStatusMessage());
        assertEquals("2011-09-19T22:40:45.123", sdf.format(response.getStatusTime()));
        assertEquals("SampleReportKey", response.getReportKey());
        assertEquals(86400, response.getReportQuota());
        assertEquals(86399, response.getReportQuotaRemaining());

    }
}
