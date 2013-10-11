package com.messagebus.client.spi;

import com.messagebus.client.v5.client.MessageBusReportingClient;
import junit.framework.TestCase;

import java.util.Date;

public class StatsReportRequestTest extends TestCase {

    public void testJsonFormat() throws Exception {
        Date startDate = new Date(1379696846544L);
        Date endDate = new Date(startDate.getTime()-1000*60*60*24*7);
        StatsReportRequest statsReportRequest= new StatsReportRequest(startDate,endDate,"TESTCHANNELKEY","TESTSESSIONKEY", MessageBusReportingClient.ReportFormat.CSV);

        final String correctJson = "{\"startDate\":\"2013-09-20T17:07:26Z\",\"endDate\":\"2013-09-13T17:07:26Z\",\"channelKey\":\"TESTCHANNELKEY\",\"sessionKey\":\"TESTSESSIONKEY\",\"format\":\"CSV\",\"reportType\":\"stats\"}";

        assertEquals(correctJson, JsonFormatHelper.toWireFormat(statsReportRequest));

    }
}
