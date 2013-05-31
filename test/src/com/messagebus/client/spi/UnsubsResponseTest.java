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
import java.util.List;
import java.util.TimeZone;

public class UnsubsResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {
        final String returnedJson =
                "{" +
                        "\"statusCode\":200," +
                        "\"statusMessage\":\"test 1\"," +
                        "\"statusTime\":\"2011-09-19T22:40:45.123Z\"," +
                        "\"unsubs\":[" +
                        "{\"email\":\"someone@example.com\",\"count\":2}," +
                        "{\"email\":\"john@exmaple.com\",\"count\":2}" +
                        "]}";


        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
        final UnsubscribesResponse response = (UnsubscribesResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, UnsubscribesResponse.class);

        assertEquals(200, response.getStatusCode());
        assertEquals("test 1", response.getStatusMessage());
        assertEquals("2011-09-19T22:40:45.123", sdf.format(response.getStatusTime()));

        List<UnsubscribeResponseResult> unsubList = response.getUnsubs();
        assertEquals(2, unsubList.size());
        assertEquals("someone@example.com", unsubList.get(0).getEmail());
        assertEquals(2, unsubList.get(0).getCount().intValue());

    }
}