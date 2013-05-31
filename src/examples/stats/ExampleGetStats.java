/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package examples.stats;

import com.messagebus.client.MessageBusFactory;
import com.messagebus.client.spi.StatsResponse;
import com.messagebus.client.v4.client.MessageBusStatsClient;
import com.messagebus.client.v4.model.MessageBusException;

import java.util.Calendar;
import java.util.Date;

/**
 * This example retrieves Statistics on an Account, Channel or Session.
 *
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 *
 * messagebus-java-sdk>  mvn clean install
 *
 * To run this example use Maven. From the top directory :
 *
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.stats.ExampleGetStats"
 *
 */
public class ExampleGetStats {

    private MessageBusStatsClient messageBusStatsClient;

    public ExampleGetStats(MessageBusStatsClient messageBusStatsClient) {
        this.messageBusStatsClient = messageBusStatsClient;
    }

    public static void main(final String[] args) {

        ExampleGetStats exampleGetStats = new ExampleGetStats(MessageBusFactory
                .createStatsClient("<YOUR_API_KEY>", "https://api-v4.messagebus.com"));

        exampleGetStats.exampleRetrieveAccountStats();
        exampleGetStats.exampleRetrieveChannelStats();
        exampleGetStats.exampleRetrieveSessionStats();

    }

    public void exampleRetrieveAccountStats() {

        final Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        final Date startDate = c.getTime();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        final Date endDate = c.getTime();

        try {
            StatsResponse statsResponse = messageBusStatsClient.retrieveStatsForAccount(startDate,
                    endDate);

            System.out.println(String.format("Account level stats from %s to %s:", startDate, endDate));
            if (statsResponse.getStatusCode() == 200) {

                System.out.println(String.format("Accept count: %d", statsResponse.getSmtp().getAcceptCount()));
                System.out.println(String.format("Bounce count: %d", statsResponse.getSmtp().getBounceCount()));
                System.out.println(String.format("Deferral count: %d", statsResponse.getSmtp().getDeferralCount()));


            } else {
                System.out.println(String.format("Could not get Stats. Status message: %s", statsResponse.getStatusMessage()));
            }
        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }

    public void exampleRetrieveChannelStats() {

        final String channelKey = "<YOUR_CHANNEL_KEY>";

        try {
            StatsResponse statsResponse = messageBusStatsClient.retrieveStatsForChannel(channelKey);

            System.out.println(String.format("Channel level stats for Channel Key %s:", channelKey));

            if (statsResponse.getStatusCode() == 200) {

                System.out.println(String.format("Accept count: %d", statsResponse.getSmtp().getAcceptCount()));
                System.out.println(String.format("Bounce count: %d", statsResponse.getSmtp().getBounceCount()));
                System.out.println(String.format("Deferral count: %d", statsResponse.getSmtp().getDeferralCount()));


            } else {
                System.out.println(String.format("Could not get Stats. Status message: %s", statsResponse.getStatusMessage()));
            }
        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }

    }

    public void exampleRetrieveSessionStats() {

        final String channelKey = "<YOUR_CHANNEL_KEY>";
        final String sessionKey = "<YOUR_SESSION_KEY>";

        try {
            StatsResponse statsResponse = messageBusStatsClient.retrieveStatsForSession(channelKey,
                    sessionKey);

            System.out.println(String.format("Session level stats for Channel Key %s and Session Key %s:", channelKey, sessionKey));

            if (statsResponse.getStatusCode() == 200) {

                System.out.println(String.format("Accept count: %d", statsResponse.getSmtp().getAcceptCount()));
                System.out.println(String.format("Bounce count: %d", statsResponse.getSmtp().getBounceCount()));
                System.out.println(String.format("Deferral count: %d", statsResponse.getSmtp().getDeferralCount()));


            } else {
                System.out.println(String.format("Could not get Stats. Status message: %s", statsResponse.getStatusMessage()));
            }
        } catch (MessageBusException e) {

            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }
}
