/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package com.messagebus.client.spi;

import junit.framework.TestCase;

public class SessionsResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final String returnedJson = "{"
                + "\"statusCode\":202,\"statusMessage\":\"some kinda message!\","
                + "\"count\":\"2\","
                + "\"results\":[{\"sessionKey\":5, \"sessionName\":\"frith session\"},"
                + "{\"sessionKey\":\"6\",\"sessionName\":\"cutler session\"}]}";

        final SessionsResponse response = (SessionsResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, SessionsResponse.class);

        assertEquals(202, response.getStatusCode());
        assertEquals("some kinda message!", response.getStatusMessage());
        assertEquals("count", 2, response.getCount());

        assertEquals("results", 2, response.getResults().size());
        assertEquals("sessionKey", "5", response.getResults().get(0).getSessionKey());
        assertEquals("sessionName", "cutler session", response.getResults().get(1).getSessionName());
    }

}
