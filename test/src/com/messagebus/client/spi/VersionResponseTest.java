/*
 * Copyright (c) 2013 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package com.messagebus.client.spi;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class VersionResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String returnedJson = "{\"statusCode\":404,\"statusMessage\":\"Test message\",\"unknownField\":0,\"statusTime\":\"" + sdf.format(date) + "\", \"APIName\":\"Message Bus API Server\", \"APIVersion\":\"1.0.27\"}";

        final VersionResponse response = (VersionResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, VersionResponse.class);

        assertEquals(404, response.getStatusCode());
        assertEquals("Test message", response.getStatusMessage());
        assertEquals(date, response.getStatusTime());
        assertEquals("Message Bus API Server", response.getApiName());
        assertEquals("1.0.27", response.getApiVersion());

    }
}
