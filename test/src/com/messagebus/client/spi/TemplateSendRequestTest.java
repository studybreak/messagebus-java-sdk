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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateSendRequestTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final Map<String, String> templateMergeFields1 = new HashMap<String, String>();
        templateMergeFields1.put("rcpt_email", "bob@example.com");
        templateMergeFields1.put("rcpt_name", "Bob");
        templateMergeFields1.put("subject", "Sample Message with HTML body.");
        templateMergeFields1.put("text_value", "test #1");
        templateMergeFields1.put("sender_email", "alice@example.com");
        templateMergeFields1.put("sender_name", "Alice Waters");
        templateMergeFields1.put("sessionKey", "sessionKeyHere");

        final Map<String, String> templateMergeFields2 = new HashMap<String, String>();
        templateMergeFields2.put("rcpt_email", "jane@example.com");
        templateMergeFields2.put("rcpt_name", "Jane");
        templateMergeFields2.put("subject", "Sample Message with HTML body.");
        templateMergeFields2.put("text_value", "test #2");
        templateMergeFields2.put("sender_email", "alice@example.com");
        templateMergeFields2.put("sender_name", "Alice Waters");
        templateMergeFields2.put("sessionKey", "sessionKeyHere");

        final List<Map<String, String>> mergeFields = new ArrayList<Map<String, String>>();
        mergeFields.add(templateMergeFields1);
        mergeFields.add(templateMergeFields2);

        final TemplateSendRequest templateSendRequest = new TemplateSendRequest("templateKey", mergeFields);

        assertEquals("{\"templateKey\":\"templateKey\",\"messages\":[{\"rcpt_email\":\"bob@example.com\",\"text_value\":\"test #1\",\"subject\":\"Sample Message with HTML body.\",\"sessionKey\":\"sessionKeyHere\",\"sender_email\":\"alice@example.com\",\"sender_name\":\"Alice Waters\",\"rcpt_name\":\"Bob\"},{\"rcpt_email\":\"jane@example.com\",\"text_value\":\"test #2\",\"subject\":\"Sample Message with HTML body.\",\"sessionKey\":\"sessionKeyHere\",\"sender_email\":\"alice@example.com\",\"sender_name\":\"Alice Waters\",\"rcpt_name\":\"Jane\"}]}", JsonFormatHelper.toWireFormat(templateSendRequest));

    }
}
