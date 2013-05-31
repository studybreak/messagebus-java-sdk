/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package examples.api;

import com.messagebus.client.MessageBusFactory;
import com.messagebus.client.spi.SessionsCreateResponse;
import com.messagebus.client.v4.client.MessageBusApiClient;
import com.messagebus.client.v4.model.MessageBusException;

/**
 * This example creates a Session for a Channel on an Account.
 *
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 *
 * messagebus-java-sdk>  mvn clean install
 *
 * To run this example use Maven. From the top directory :
 *
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.api.ExampleCreateSession"
 *
 */
public class ExampleCreateSession {

    private MessageBusApiClient messageBusApiClient;

    public ExampleCreateSession(MessageBusApiClient messageBusApiClient) {
        this.messageBusApiClient = messageBusApiClient;
    }

    public static void main(final String[] args) {

        ExampleCreateSession exampleCreateSession = new ExampleCreateSession(MessageBusFactory
                .createApiClient("<YOUR_API_KEY>", "https://api-v4.messagebus.com"));

        exampleCreateSession.exampleCreateSession();

    }

    public void exampleCreateSession() {
        final String channelKey = "<YOUR_CHANNEL_KEY>";
        final String sessionName = "Example Session";

        try {
            final SessionsCreateResponse session = messageBusApiClient.createSession(channelKey,
                    sessionName);

            if (session.getStatusCode() == 202) {
                System.out.println(String.format("Successfully created session with key: %s with name: %s", session.getSessionKey(), session.getSessionName()));

            } else {
                System.out.println(String.format("Could not create session: %s", session.getStatusMessage()));
            }

        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }
}

