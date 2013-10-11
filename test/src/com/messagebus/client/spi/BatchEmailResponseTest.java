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

import java.util.List;

public class BatchEmailResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final String returnedJson = "{"
                + "\"statusCode\":202,\"statusMessage\":\"\","
                + "\"statusTime\":\"2011-09-19T22:40:45.123Z\","
                + "\"successCount\":2,\"failureCount\":0,"
                + "\"unknownField\":0,"
                + "\"results\":[{"
                + "\"toEmail\":\"mark@example.com\","
                + "\"messageId\":\"97d6f730c835012e8d4640406818e8c7\","
                + "\"messageStatus\":0},{"
                + "\"toEmail\":\"john@example.com\","
                + "\"messageId\":\"97d7dd80c835012e8d4640406818e8c7\","
                + "\"messageStatus\":0,"
                + "\"unknownField\":0" +
                "}]}";

        final BatchEmailResponse response = (BatchEmailResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, BatchEmailResponse.class);

        assertEquals(202, response.getStatusCode());

        final List<BatchEmailResult> resultsArray = response.getResults();
        assertEquals("mark@example.com", resultsArray.get(0).getToEmail());
        assertEquals("john@example.com", resultsArray.get(1).getToEmail());

    }
}