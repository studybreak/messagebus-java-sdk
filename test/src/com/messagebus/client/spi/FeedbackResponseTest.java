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

public class FeedbackResponseTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final String returnedJson = "{\"bounces\":[],\"complaints\":[],\"unsubscribes\":[],\"opens\":[],\"clicks\":[],\"statusCode\":200,\"statusMessage\":\"\",\"statusTime\":\"2013-04-01T05:00:54.379Z\"}";

        final FeedbackResponse feedbackResponse = (FeedbackResponse) JsonFormatHelper
                .fromWireFormat(returnedJson, FeedbackResponse.class);

        assertEquals(feedbackResponse.getStatusCode(), 200);
        assertEquals(feedbackResponse.getBounces().size(), 0);
        assertEquals(feedbackResponse.getComplaints().size(), 0);
        assertEquals(feedbackResponse.getClicks().size(), 0);
        assertEquals(feedbackResponse.getOpens().size(), 0);
    }
}
