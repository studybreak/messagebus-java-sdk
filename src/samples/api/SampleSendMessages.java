/*
 * Copyright (c) 2013 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package samples.api;

import com.messagebus.client.MessageBusFactory;
import com.messagebus.client.spi.BatchEmailMessageRequestItem;
import com.messagebus.client.spi.BatchEmailResponse;
import com.messagebus.client.spi.BatchEmailResult;
import com.messagebus.client.spi.BatchEmailSendRequest;
import com.messagebus.client.v5.client.MessageBusApiClient;
import com.messagebus.client.v5.model.MessageBusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This example sends messages on a session.
 *
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 *
 * messagebus-java-sdk>  mvn clean install
 *
 * To run this example use Maven. From the top directory :
 *
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.api.ExampleSendMessages"
 *
 */
public class SampleSendMessages {

    private MessageBusApiClient messageBusApiClient;

    public SampleSendMessages(MessageBusApiClient messageBusApiClient) {
        this.messageBusApiClient = messageBusApiClient;
    }

    public static void main(String[] args) {

        final SampleSendMessages exampleSend = new SampleSendMessages(MessageBusFactory.createApiClient("<YOUR_API_KEY>", "https://api.messagebus.com"));

        exampleSend.exampleSendMessages();

    }

    public void exampleSendMessages() {
        try {

            final String sessionKey = "<YOUR_SESSION_KEY>";

            final ArrayList<BatchEmailMessageRequestItem> messageBusEmails = new ArrayList<BatchEmailMessageRequestItem>();

            final Map<String, String> customHeaders = new HashMap<String, String>();
            customHeaders.put("x-messagebus-sdk", "java-sdk");

            final BatchEmailMessageRequestItem messageBusEmail1 = new BatchEmailMessageRequestItem();

            messageBusEmail1.setToEmail("bobby@example.com");
            messageBusEmail1.setReturnPath("bounces@example.com");
            messageBusEmail1.setToName("Bobby Flay");
            messageBusEmail1.setFromEmail("alice@example.com");
            messageBusEmail1.setFromName("Alice Waters");
            messageBusEmail1.setSubject("Sample Message with HTML body.");
            messageBusEmail1.setCustomHeaders(customHeaders);
            messageBusEmail1.setPlaintextBody("This is the plain text body.");
            messageBusEmail1.setHtmlBody("This is the <b>HTML</b>body.");
            messageBusEmail1.setSessionKey(sessionKey);

            messageBusEmails.add(messageBusEmail1);

            final BatchEmailMessageRequestItem messageBusEmail2 = new BatchEmailMessageRequestItem();

            messageBusEmail2.setToEmail("jamie@example.com");
            messageBusEmail2.setReturnPath("bounces@example.com");
            messageBusEmail2.setToName("Jamie Lauren");
            messageBusEmail2.setFromEmail("alice@example.com");
            messageBusEmail2.setFromName("Alice Waters");
            messageBusEmail2.setSubject("Sample Message with HTML body.");
            messageBusEmail2.setCustomHeaders(customHeaders);
            messageBusEmail2.setPlaintextBody("This is the plain text body.");
            messageBusEmail2.setHtmlBody("This is the <b>HTML</b>body.");
            messageBusEmail2.setSessionKey(sessionKey);

            messageBusEmails.add(messageBusEmail2);

            final BatchEmailSendRequest batchEmailSendRequest = new BatchEmailSendRequest(messageBusEmails);

            final BatchEmailResponse batchEmailResponse = this.messageBusApiClient.sendMessages(batchEmailSendRequest);

            if (batchEmailResponse.getStatusCode() == 202) {

                final List<BatchEmailResult> results = batchEmailResponse.getResults();

                System.out.println("Send Message results: ");

                for (BatchEmailResult result : results) {
                    System.out.println(String.format("To %s Message Id: %s Message Status: %s", result.getToEmail(), result.getMessageId(), result.getMessageStatus()));
                }
            } else {
                System.out.println(String.format("Could not Send Message. Status message: %s", batchEmailResponse.getStatusMessage()));
            }
        } catch (final MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }

}
