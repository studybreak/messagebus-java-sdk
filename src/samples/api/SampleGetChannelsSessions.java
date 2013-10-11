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
import com.messagebus.client.spi.ChannelsResponse;
import com.messagebus.client.spi.ChannelsResponseResult;
import com.messagebus.client.spi.SessionsResponse;
import com.messagebus.client.spi.SessionsResponseResult;
import com.messagebus.client.v5.client.MessageBusApiClient;
import com.messagebus.client.v5.model.MessageBusException;

/**
 * This example fetches Sessions and Channels on an Account.
 *
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 *
 * messagebus-java-sdk>  mvn clean install
 *
 * To run this example use Maven. From the top directory :
 *
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.api.ExampleGetChannelsSessions"
 *
 */

public class SampleGetChannelsSessions {

    private MessageBusApiClient messageBusApiClient;

    public SampleGetChannelsSessions(MessageBusApiClient messageBusApiClient) {
        this.messageBusApiClient = messageBusApiClient;
    }

    public static void main(final String[] args) {

        SampleGetChannelsSessions exampleGetChannelsSessions = new SampleGetChannelsSessions(MessageBusFactory
                .createApiClient("<YOUR_API_KEY>", "https://api.messagebus.com"));

        exampleGetChannelsSessions.exampleGetChannelsAndSessions();

    }

    public void exampleGetChannelsAndSessions() {

        try {
            final ChannelsResponse channels = messageBusApiClient.getChannels();

            if (channels.getStatusCode() == 200) {
                System.out.println("Successfully fetched channels");

                for (ChannelsResponseResult channelsResponseResult : channels.getResults()) {
                    System.out.println(String.format("Channel Name: %s. Channel Key: %s. Default Session Key: %s ", channelsResponseResult.getChannelName(), channelsResponseResult.getChannelKey(), channelsResponseResult.getDefaultSessionKey()));

                    final SessionsResponse sessions = messageBusApiClient.getSessions(channelsResponseResult.getChannelKey());
                    System.out.println(String.format("Sessions for Channel Name %s are: ", channelsResponseResult.getChannelName()));

                    for (SessionsResponseResult session : sessions.getResults()) {
                        System.out.println(String.format("Session Key %s with Name %s for the above Channel.", session.getSessionKey(), session.getSessionName()));
                    }
                }

            } else {
                System.out.println(String.format("Could not fetch channels: %s", channels.getStatusMessage()));
            }

        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }
}

