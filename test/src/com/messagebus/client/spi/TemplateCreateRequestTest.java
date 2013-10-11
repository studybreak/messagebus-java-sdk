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

import java.util.HashMap;
import java.util.Map;

public class TemplateCreateRequestTest extends TestCase {

    public void testJsonFormat() throws Exception {

        final Map<String, String> customHeaders = new HashMap<String, String>();
        customHeaders.put("custom-header-1", "custom value 1");
        customHeaders.put("custom-header-2", "custom value 2");

        final Map<String, String> options = new HashMap<String, String>();
        options.put("option-1", "option value 1");
        options.put("option-2", "option value 2");

        TemplateCreateRequest templateCreateRequest = new TemplateCreateRequest();
        templateCreateRequest.setToEmail("to1@example.com");
        templateCreateRequest.setToName("To Name 1");
        templateCreateRequest.setFromEmail("from1@example.com");
        templateCreateRequest.setFromName("From Name 1");
        templateCreateRequest.setSubject("Test Subject 1");
        templateCreateRequest.setPlaintextBody("Test Plain Text Body 1");
        templateCreateRequest.setHtmlBody("Test Html Body 1");
        templateCreateRequest.setCustomHeaders(customHeaders);
        templateCreateRequest.setReturnPath("returnPath");
        templateCreateRequest.setSessionKey("DDEE5E70DC3611E1AE283ED96188709B");
        templateCreateRequest.setName("templateName");

        assertEquals("{\"customHeaders\":{\"custom-header-2\":\"custom value 2\",\"custom-header-1\":\"custom value 1\"}," +
                "\"fromEmail\":\"from1@example.com\",\"fromName\":\"From Name 1\",\"htmlBody\":\"Test Html Body 1\",\"name\":\"templateName\"," +
                "\"plaintextBody\":\"Test Plain Text Body 1\"," +
                "\"returnPath\":\"returnPath\",\"sessionKey\":\"DDEE5E70DC3611E1AE283ED96188709B\",\"subject\":\"Test Subject 1\"," +
                "\"toEmail\":\"to1@example.com\",\"toName\":\"To Name 1\"}", JsonFormatHelper.toWireFormat(templateCreateRequest));


    }
}
