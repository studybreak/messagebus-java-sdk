package com.messagebus.client.spi;

import com.messagebus.client.v5.client.MessageBusReportingClient;
import junit.framework.TestCase;

public class BlocklistReportRequestTest extends TestCase {

    public void testJsonFormat() throws Exception {

        BlocklistReportRequest blocklistReportRequest= new BlocklistReportRequest("TESTKEY", MessageBusReportingClient.ReportFormat.CSV);

        final String correctJson = "{\"channelKey\":\"TESTKEY\",\"format\":\"CSV\",\"reportType\":\"blocklist\"}";

        assertEquals(correctJson, JsonFormatHelper.toWireFormat(blocklistReportRequest));

    }
}
