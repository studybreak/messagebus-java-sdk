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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchEmailSendRequestTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final Map<String, String> customHeaders1 = new HashMap<String, String>();
        customHeaders1.put("custom-header-1", "custom value 1");
        customHeaders1.put("custom-header-2", "custom value 2");

        final BatchEmailMessageRequestItem emailMessage1 = new BatchEmailMessageRequestItem();
        final BatchEmailMessageRequestItem emailMessage2 = new BatchEmailMessageRequestItem();

        emailMessage1.setToEmail("to1@example.com");
        emailMessage1.setToName("To Name 1");
        emailMessage1.setFromEmail("from1@example.com");
        emailMessage1.setFromName("From Name 1");
        emailMessage1.setSubject("Test Subject 1");
        emailMessage1.setPlaintextBody("Test Plain Text Body 1");
        emailMessage1.setHtmlBody("Test Html Body 1");
        emailMessage1.setCustomHeaders(customHeaders1);
        // testing unset sessionKey --- should return null

        emailMessage2.setToEmail("to2@example.com");
        emailMessage2.setToName("To Name 2");
        emailMessage2.setFromEmail("from2@example.com");
        emailMessage2.setFromName("From Name 2");
        emailMessage2.setSubject("Test Subject 2");
        emailMessage2.setPlaintextBody("Test Plain Text Body 2");
        emailMessage2.setHtmlBody("Test Html Body 2");
        emailMessage2.setCustomHeaders(new HashMap<String, String>());
        emailMessage2.setSessionKey("DDEE5E70DC3611E1AE283ED96188709B");

        final List<BatchEmailMessageRequestItem> emailList = new ArrayList<BatchEmailMessageRequestItem>();

        emailList.add(emailMessage1);
        emailList.add(emailMessage2);

        final BatchEmailSendRequest sendRequest = new BatchEmailSendRequest(
                emailList);

        final String correctJson = "{\"messages\":[{\"customHeaders\":{\"custom-header-2\":\"custom value 2\",\"custom-header-1\":\"custom value 1\"}," +
                "\"fromEmail\":\"from1@example.com\",\"fromName\":\"From Name 1\",\"htmlBody\":\"Test Html Body 1\",\"plaintextBody\":\"Test Plain Text Body 1\"," +
                "\"subject\":\"Test Subject 1\",\"toEmail\":\"to1@example.com\",\"toName\":\"To Name 1\",\"sessionKey\":null},{\"customHeaders\":{}," +
                "\"fromEmail\":\"from2@example.com\",\"fromName\":\"From Name 2\",\"htmlBody\":\"Test Html Body 2\",\"plaintextBody\":\"Test Plain Text Body 2\"," +
                "\"subject\":\"Test Subject 2\",\"toEmail\":\"to2@example.com\",\"toName\":\"To Name 2\",\"sessionKey\":\"DDEE5E70DC3611E1AE283ED96188709B\"}]}";

        assertEquals(correctJson, JsonFormatHelper.toWireFormat(sendRequest));

    }
}
