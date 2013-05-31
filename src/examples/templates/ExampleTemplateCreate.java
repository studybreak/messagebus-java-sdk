/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package examples.templates;

import com.messagebus.client.MessageBusFactory;
import com.messagebus.client.spi.*;
import com.messagebus.client.v4.client.MessageBusTemplatesClient;
import com.messagebus.client.v4.model.MessageBusException;

import java.util.HashMap;
import java.util.Map;

/**
 * This example creates a Template.
 *
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 *
 * messagebus-java-sdk>  mvn clean install
 *
 * To run this example use Maven. From the top directory :
 *
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.templates.ExampleTemplateCreate"
 *
 */
public class ExampleTemplateCreate {

    private MessageBusTemplatesClient messageBusTemplatesClient;

    public ExampleTemplateCreate(MessageBusTemplatesClient messageBusTemplatesClient) {
        this.messageBusTemplatesClient = messageBusTemplatesClient;
    }

    public static void main(String[] args) {

        final ExampleTemplateCreate exampleTemplate = new ExampleTemplateCreate(MessageBusFactory.createTemplatesClient("<YOUR_API_KEY>", "https://templates-v4-jy01-prod.messagebus.com"));

        exampleTemplate.exampleCreateTemplate();

    }

    public void exampleCreateTemplate() {
        try {

            final TemplateVersionResponse templateVersionResponse = this.messageBusTemplatesClient.templateVersion();
            if (templateVersionResponse.getStatusCode() == 200) {
                System.out.println(String.format("Using Template version: %s", templateVersionResponse.getVersion()));
            } else {
                System.out.println(String.format("Could not fetch the template version. Status message: %s", templateVersionResponse.getStatusMessage()));
            }

            final Map<String, String> customHeaders = new HashMap<String, String>();
            customHeaders.put("X-MessageBus-SDK", "messagebus-java-sdk");

            final TemplateCreateRequest template = new TemplateCreateRequest();
            template.setCustomHeaders(customHeaders);
            template.setToName("{{rcpt_name}}");
            template.setToEmail("{{rcpt_email}}");
            template.setFromName("{{sender_name}}");
            template.setFromEmail("{{sender_email}}");
            template.setReturnPath("bounces@bounces.example.com");
            template.setSubject("Hello {{{rcpt_name}}}");
            template.setPlaintextBody("This is the plaintextBody! This is a {{text_value}}");
            template.setHtmlBody("<p>This is the htmlBody!</p>");
            template.setSessionKey("{{session_key}}");

            TemplateCreateResponse templateCreateResponse = this.messageBusTemplatesClient.createTemplate(template);

            if (templateCreateResponse.getStatusCode() == 201) {

                System.out.println(String.format("Template created with key:%s", templateCreateResponse.getTemplateKey()));
                System.out.println("Templates associated with this account:");

                final TemplateResponse templateDetail = this.messageBusTemplatesClient.getTemplate(templateCreateResponse.getTemplateKey());
                System.out.println(String.format("Template detail from specific get template call: ", templateDetail.getTemplate().getSessionKey()));

                final TemplatesResponse templates = this.messageBusTemplatesClient.getTemplates();

                for (TemplateResponseResult templateResult : templates.getTemplates()) {
                    System.out.println(String.format("Template key:%s", templateResult.getTemplateKey()));

                }
            } else {
                System.out.println(String.format("Could not create Template. Status message: %s", templateCreateResponse.getStatusMessage()));
            }
        } catch (final MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }
}