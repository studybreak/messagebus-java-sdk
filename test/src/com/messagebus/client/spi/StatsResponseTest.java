/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package com.messagebus.client.spi;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class StatsResponseTest extends TestCase {


    public void testJsonFormat() throws Exception {
        final String returnedJson =
                "{" +
                        "\"statusCode\":200," +
                        "\"statusMessage\":\"test 1\"," +
                        "\"statusTime\":\"2011-09-19T22:40:45.123Z\"," +
                        "\"stats\":" +
                        "{\"msgsAttemptedCount\":456, \"complaintCount\":0," +
                        "\"unsubscribeCount\":0," +
                        "\"clickCount\":22," +
                        "\"uniqueClickCount\":11," +
                        "\"openCount\":44," +
                        "\"uniqueOpenCount\":33" +
                        "}," +
                        " \"smtp\":" +
                        "{\"acceptCount\":0, \"bounceCount\":0," +
                        "    \"deferralCount\":789, \"rejectCount\":0" +
                        "}," +
                        " \"filter\":" +
                        "{\"spamCount\":0, \"rcptUnsubscribeCount\":0," +
                        "\"rcptComplaintCount\":0, \"rcptBadMailboxCount\":123" +
                        "}}";


        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
        final StatsResponse response = (StatsResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, StatsResponse.class);

        assertEquals(200, response.getStatusCode());
        assertEquals("test 1", response.getStatusMessage());
        assertEquals("2011-09-19T22:40:45.123", sdf.format(response.getStatusTime()));

        final StatsFilter fiter = response.getFilter();
        assertEquals(123, fiter.getRcptBadMailboxCount().intValue());
        final StatsDetail detail = response.getStats();
        assertEquals(456, detail.getMsgsAttemptedCount().intValue());
        assertEquals(22, detail.getClickCount().intValue());
        assertEquals(11, detail.getUniqueClickCount().intValue());
        assertEquals(44, detail.getOpenCount().intValue());
        assertEquals(33, detail.getUniqueOpenCount().intValue());

        final StatsSmtp smtp = response.getSmtp();
        assertEquals(789, smtp.getDeferralCount().intValue());

    }
}