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
import java.util.TimeZone;

public class SessionsCreateResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final String returnedJson = "{\"statusCode\":202," +
                "\"statusMessage\":\"testMessage\",\"statusTime\":\"2011-09-19T22:40:45.123\"," +
                "\"sessionKey\":\"443B4842DB2D11E1B144F3EF6188709B\"}";

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        final SessionsCreateResponse response = (SessionsCreateResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, SessionsCreateResponse.class);

        assertEquals(202, response.getStatusCode());
        assertEquals("testMessage", response.getStatusMessage());
        assertEquals("2011-09-19T22:40:45.123", sdf.format(response.getStatusTime()));
        assertEquals("443B4842DB2D11E1B144F3EF6188709B", response.getSessionKey());
    }
}
