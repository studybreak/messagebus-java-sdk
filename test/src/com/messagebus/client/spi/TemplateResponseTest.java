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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TemplateResponseTest extends TestCase {

    static String formatDate(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    final String jsonStringTemplateVersionResponse200 = "{\"statusCode\":200,\"statusMessage\":\"Version\",\"version\":\"1.13.6\",\"statusTime\":\"2013-04-01T22:44:48.879Z\"}";

    final String jsonStringTemplateResponse202 = "{\"failureCount\":0,\"results\":[{\"messageId\":\"3d045430a2f411e2b63dbc764e049d62\",\"messageStatus\":0,\"toEmail\":\"bob@example.com\"}],\"statusCode\":202,\"statusMessage\":\"\",\"successCount\":1,\"statusTime\":\"2013-04-01T22:07:45.692Z\"}";

    final String jsonStringTemplateResponse207 = "{\"failureCount\":1,\"results\":[{\"messageId\":\"48ff3bf0a2f511e2b63dbc764e049d62\",\"messageStatus\":0,\"toEmail\":\"bob@example.com\"},{\"messageId\":\"48ff6300a2f511e2b63dbc764e049d62\",\"messageStatus\":1002,\"toEmail\":\"jane!!!@example.com\"}],\"statusCode\":207,\"statusMessage\":\"One or more emails not sent. See individual error messages.\",\"successCount\":1,\"statusTime\":\"2013-04-01T22:15:15.115Z\"}";

    final String jsonStringTemplateCreateResponse201 = "{\"templateKey\":\"f146c828-8ac3-49e3-9106-56b426bf618b\",\"statusCode\":201,\"statusMessage\":\"Template saved\",\"statusTime\":\"2013-04-01T21:58:34.890Z\"}";

    final String jsonStringTemplateNotFoundResponse401 = "{\"statusCode\":401,\"statusMessage\":\"Template not found.\",\"statusTime\":\"2013-04-01T22:19:57.533Z\"}";

    final String jsonStringTemplateRetrieveResponse200 = "{\"template\":{\"toEmail\":\"{{rcpt_email}}\",\"fromEmail\":\"{{sender_email}}\",\"subject\":\"Hello {{{rcpt_name}}}\",\"toName\":\"{{rcpt_name}}\",\"fromName\":\"{{sender_name}}\",\"returnPath\":\"{{return_path}}\",\"plaintextBody\":\"This is the plaintextBody! This is a {{text_value}}\",\"htmlBody\":\"<p>This is the htmlBody!</p>\",\"templateKey\":\"f146c828-8ac3-49e3-9106-56b426bf618b\",\"apiKey\":\"f7804640600811e280fbbc764e0445a1\",\"customHeaders\":{\"X-MessageBus-SDK\":\"messagebus-node-sdk\",\"envelope-sender\":\"{{return_path}}\",\"X-MessageBus-Template-Version\":\"1.13.6\"},\"sessionKey\":\"{{session_key}}\",\"creationTime\":\"2013-04-11T21:58:34.890Z\",\"options\":[]},\"statusCode\":200,\"statusMessage\":\"\",\"statusTime\":\"2013-04-12T00:09:07.723Z\"}";


    public void testVersion() throws Exception {

        final TemplateVersionResponse response = (TemplateVersionResponse) JsonFormatHelper
                .fromWireFormat(jsonStringTemplateVersionResponse200, TemplateVersionResponse.class);

        assertEquals("1.13.6", response.getVersion());
        assertEquals(200, response.getStatusCode());
        assertEquals("Version", response.getStatusMessage());
        assertEquals("2013-04-01T22:44:48.879", formatDate(response.getStatusTime()));
    }

    public void testFind202Response() throws Exception {

        final TemplateRetrieveResponse response202 = (TemplateRetrieveResponse) JsonFormatHelper
                .fromWireFormat(jsonStringTemplateRetrieveResponse200, TemplateRetrieveResponse.class);

        assertEquals(200, response202.getStatusCode());
        assertEquals("", response202.getStatusMessage());
        assertEquals("2013-04-12T00:09:07.723", formatDate(response202.getStatusTime()));
        TemplateResponseResult template = response202.getTemplate();

        assertEquals("f7804640600811e280fbbc764e0445a1", template.getApiKey());
        assertEquals("f146c828-8ac3-49e3-9106-56b426bf618b", template.getTemplateKey());
        assertEquals("Hello {{{rcpt_name}}}", template.getSubject());
        assertEquals("{{session_key}}", template.getSessionKey());
        assertEquals("2013-04-11T21:58:34.890", formatDate(template.getCreationTime()));
        assertEquals("{{sender_name}}", template.getFromName());
        assertEquals("{{sender_email}}", template.getFromEmail());
        assertEquals("{{rcpt_email}}", template.getToEmail());
        assertEquals("{{rcpt_name}}", template.getToName());
    }

    public void testFind401Response() throws Exception {

        final TemplateRetrieveResponse response401 = (TemplateRetrieveResponse) JsonFormatHelper
                .fromWireFormat(jsonStringTemplateNotFoundResponse401, TemplateRetrieveResponse.class);

        assertEquals(401, response401.getStatusCode());
        assertEquals("Template not found.", response401.getStatusMessage());
        assertEquals("2013-04-01T22:19:57.533", formatDate(response401.getStatusTime()));
    }

    public void testCreate201Response() throws Exception {

        final TemplateCreateResponse response201 = (TemplateCreateResponse) JsonFormatHelper
                .fromWireFormat(jsonStringTemplateCreateResponse201, TemplateCreateResponse.class);

        assertEquals(201, response201.getStatusCode());
        assertEquals("Template saved", response201.getStatusMessage());
        assertEquals("2013-04-01T21:58:34.890", formatDate(response201.getStatusTime()));
        assertEquals("f146c828-8ac3-49e3-9106-56b426bf618b", response201.getTemplateKey());
    }

    public void testSend202Response() throws Exception {

        final BatchEmailResponse response202 = (BatchEmailResponse) JsonFormatHelper
                .fromWireFormat(jsonStringTemplateResponse202, BatchEmailResponse.class);

        assertEquals(202, response202.getStatusCode());
        assertEquals("", response202.getStatusMessage());
        assertEquals("2013-04-01T22:07:45.692", formatDate(response202.getStatusTime()));
        List<BatchEmailResult> results = response202.getResults();

        assertEquals("3d045430a2f411e2b63dbc764e049d62", results.get(0).getMessageId());
        assertEquals("bob@example.com", results.get(0).getToEmail());
        assertEquals(0, results.get(0).getMessageStatus());

    }

    public void testSend207Response() throws Exception {

        final BatchEmailResponse response207 = (BatchEmailResponse) JsonFormatHelper
                .fromWireFormat(jsonStringTemplateResponse207, BatchEmailResponse.class);

        assertEquals(207, response207.getStatusCode());
        assertEquals("One or more emails not sent. See individual error messages.", response207.getStatusMessage());
        assertEquals("2013-04-01T22:15:15.115", formatDate(response207.getStatusTime()));
        assertEquals(1, response207.getFailureCount());
        assertEquals(1, response207.getSuccessCount());

        List<BatchEmailResult> results = response207.getResults();

        assertEquals("48ff3bf0a2f511e2b63dbc764e049d62", results.get(0).getMessageId());
        assertEquals("bob@example.com", results.get(0).getToEmail());
        assertEquals(0, results.get(0).getMessageStatus());

    }


}
