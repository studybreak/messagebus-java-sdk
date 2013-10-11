package com.messagebus.client.spi;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

public class ReportStatusResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {
        final String returnedJson =
                "{\n" +
                        "    \"statusCode\"    : 200,\n" +
                        "    \"statusMessage\" : \"Report has completed.\",\n" +
                        "    \"statusTime\"    : \"2011-09-19T22:40:45.123\",\n" +
                        "    \"reportStatus\"  : \"done\"\n" +
                        "}";


        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
        final ReportStatusResponse response = (ReportStatusResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, ReportStatusResponse.class);

        assertEquals(200, response.getStatusCode());
        assertEquals("Report has completed.", response.getStatusMessage());
        assertEquals("2011-09-19T22:40:45.123", sdf.format(response.getStatusTime()));
        assertEquals("done",response.getReportStatus());
        assertEquals(false,response.isEmpty());
        assertEquals(false,response.isFailed());
        assertEquals(true,response.isFinished());
        assertEquals(true,response.isSuccessful());

    }
}
