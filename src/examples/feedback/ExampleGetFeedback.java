/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package examples.feedback;


import com.messagebus.client.MessageBusFactory;
import com.messagebus.client.spi.BouncesResponseResult;
import com.messagebus.client.spi.ClickResponseResult;
import com.messagebus.client.spi.FeedbackResponse;
import com.messagebus.client.spi.OpenResponseResult;
import com.messagebus.client.v4.client.MessageBusFeedbackClient;
import com.messagebus.client.v4.model.MessageBusException;

import java.util.HashSet;
import java.util.List;

/**
 * This example retrieves Feedback for an Account with specific email addresses.
 *
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 *
 * messagebus-java-sdk>  mvn clean install
 *
 * To run this example use Maven. From the top directory :
 *
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.feedback.ExampleGetFeedback"
 *
 */
public class ExampleGetFeedback {

    private MessageBusFeedbackClient messageBusFeedbackClient;

    public ExampleGetFeedback(MessageBusFeedbackClient messageBusFeedbackClient) {
        this.messageBusFeedbackClient = messageBusFeedbackClient;
    }

    public static void main(String[] args) {

        final ExampleGetFeedback exampleGetFeedback = new ExampleGetFeedback(MessageBusFactory.createFeedbackClient("<YOUR_API_KEY>", "https://api-v4.messagebus.com"));

        exampleGetFeedback.exampleGetAccountFeedback();
        exampleGetFeedback.exampleGetAccountFeedbackOpensAndClicksOnly();

    }

    public void exampleGetAccountFeedback() {

        try {
            final FeedbackResponse feedbackResponse = this.messageBusFeedbackClient.retrieveFeedbackForAccount();

            final List<OpenResponseResult> opens = feedbackResponse.getOpens();

            System.out.println("Opens: ");
            for (OpenResponseResult openResult : opens) {
                System.out.println(String.format("Email: %s Count: %s Last Event Time: %s ", openResult.getEmail(), openResult.getCount(), openResult.getLastEventTime()));
            }

            final List<BouncesResponseResult> bounces = feedbackResponse.getBounces();

            System.out.println("Bounces: ");
            for (BouncesResponseResult bounceResult : bounces) {
                System.out.println(String.format("Email: %s Count: %s Last Event Time: %s ", bounceResult.getEmail(), bounceResult.getCount(), bounceResult.getLastEventTime()));
            }


        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }

    }

    public void exampleGetAccountFeedbackOpensAndClicksOnly() {
        final HashSet<MessageBusFeedbackClient.ScopeType> scopeTypes = new HashSet<MessageBusFeedbackClient.ScopeType>();
        scopeTypes.add(MessageBusFeedbackClient.ScopeType.CLICKS);
        scopeTypes.add(MessageBusFeedbackClient.ScopeType.OPENS);
        try {
            final FeedbackResponse feedbackResponse = this.messageBusFeedbackClient.retrieveFeedbackForAccount(scopeTypes);

            final List<OpenResponseResult> opens = feedbackResponse.getOpens();

            System.out.println("Opens: ");
            for (OpenResponseResult openResult : opens) {
                System.out.println(String.format("Email: %s Count: %s Last Event Time: %s ", openResult.getEmail(), openResult.getCount(), openResult.getLastEventTime()));
            }

            final List<ClickResponseResult> clicks = feedbackResponse.getClicks();

            System.out.println("Clicks: ");
            for (ClickResponseResult click : clicks) {
                System.out.println(String.format("Email: %s Count: %s Last Event Time: %s ", click.getEmail(), click.getCount(), click.getLastEventTime()));
            }


        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }

    }

}
