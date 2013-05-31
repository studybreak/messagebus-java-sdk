/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package com.messagebus.client;

import com.messagebus.client.impl.DefaultApiClient;
import com.messagebus.client.impl.DefaultFeedbackClient;
import com.messagebus.client.impl.DefaultStatsClient;
import com.messagebus.client.impl.DefaultTemplatesClient;
import com.messagebus.client.v4.client.MessageBusApiClient;
import com.messagebus.client.v4.client.MessageBusFeedbackClient;
import com.messagebus.client.v4.client.MessageBusStatsClient;
import com.messagebus.client.v4.client.MessageBusTemplatesClient;

/**
 * Factory class for returning default instances of Message Bus examples classes
 */
public class
        MessageBusFactory {

    /**
     * Returns a MessageBusApiClient for sending email and getting account info
     *
     * @param apiKey
     * @return default api examples
     */
    public static MessageBusApiClient createApiClient(final String apiKey) {
        return new DefaultApiClient(apiKey);
    }

    /**
     * Returns a MessageBusApiClient for sending email and getting account info
     *
     * @param apiKey
     * @param domain
     * @return default api examples
     */
    public static MessageBusApiClient createApiClient(final String apiKey, final String domain) {
        return new DefaultApiClient(apiKey, domain);
    }

    /**
     * Returns a MessageBusStatsClient for getting statistical information about accounts, channels and sessions
     *
     * @param apiKey
     * @return default stats examples
     */
    public static MessageBusStatsClient createStatsClient(final String apiKey) {
        return new DefaultStatsClient(apiKey);
    }

    /**
     * Returns a MessageBusStatsClient for getting statistical information about accounts, channels and sessions
     *
     * @param apiKey
     * @param domain
     * @return default stats examples
     */
    public static MessageBusStatsClient createStatsClient(final String apiKey, final String domain) {
        return new DefaultStatsClient(apiKey, domain);
    }

    /**
     * Returns a MessageBusTemplatesClient for template email sending
     *
     * @param apiKey
     * @return default templates examples
     */
    public static MessageBusTemplatesClient createTemplatesClient(final String apiKey) {
        return new DefaultTemplatesClient(apiKey);
    }

    /**
     * Returns a MessageBusTemplatesClient for template email sending
     *
     * @param apiKey
     * @param domain
     * @return default templates examples
     */
    public static MessageBusTemplatesClient createTemplatesClient(final String apiKey, final String domain) {
        return new DefaultTemplatesClient(apiKey, domain);
    }

    /**
     * Returns a MessageBusFeedbackClient for getting all message feedback
     *
     * @param apiKey
     * @return default feedback examples
     */
    public static MessageBusFeedbackClient createFeedbackClient(final String apiKey) {
        return new DefaultFeedbackClient(apiKey);
    }

    /**
     * Returns a MessageBusFeedbackClient for getting all message feedback
     *
     * @param apiKey
     * @param domain
     * @return default feedback examples
     */
    public static MessageBusFeedbackClient createFeedbackClient(final String apiKey, final String domain) {
        return new DefaultFeedbackClient(apiKey, domain);
    }

}
