/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package com.messagebus.client.impl;

import com.messagebus.client.spi.StatsResponse;
import com.messagebus.client.v4.client.MessageBusStatsClient;
import com.messagebus.client.v4.model.MessageBusException;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Date;

/**
 *
 * Client for querying on delivery errors, unsubscribes and email statistics
 *
 */
public class DefaultStatsClient extends SimpleHttpClient implements MessageBusStatsClient {

    private static final String EMAIL_STATS_FOR_ACCOUNT = "stats/email";
    private static final String EMAIL_STATS_FOR_CHANNEL = "stats/email/channel/%s";
    private static final String EMAIL_STATS_FOR_SESSION = "stats/email/channel/%s/session/%s";

    /**
     * Constructs a Statistics Client using the default domain.
     * @param apiKey
     */
    public DefaultStatsClient(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs a Statistics Client using the specified domain.
     * @param apiKey
     * @param domain
     */
    public DefaultStatsClient(final String apiKey, final String domain) {
        super(apiKey, domain);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return StatsDetail, Smtp and Filter details for the account.
     * @throws MessageBusException
     */
    @Override
    public StatsResponse retrieveStatsForAccount(
            Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupDateQueryMap(
                startDate, endDate);
        StatsResponse response = (StatsResponse) this.jerseyBasedRequest(
                DefaultStatsClient.EMAIL_STATS_FOR_ACCOUNT, HttpMethod.GET, queryMap, null,
                StatsResponse.class);
        return response;
    }

    /**
     *
     * @return StatsDetail, Smtp and Filter details for the account.
     * @throws MessageBusException
     */
    @Override
    public StatsResponse retrieveStatsForAccount() throws MessageBusException {
        return retrieveStatsForAccount(null, null);
    }

    /**
     *
     * @param channelKey
     * @param startDate
     * @param endDate
     * @return StatsDetail, Smtp and Filter details for the channel.
     * @throws MessageBusException
     */
    @Override
    public StatsResponse retrieveStatsForChannel(
            String channelKey, Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupDateQueryMap(
                startDate, endDate);
        StatsResponse response = (StatsResponse) this.jerseyBasedRequest(
                String.format(DefaultStatsClient.EMAIL_STATS_FOR_CHANNEL, channelKey), HttpMethod.GET, queryMap, null,
                StatsResponse.class);
        return response;
    }

    /**
     *
     * @param channelKey
     * @return StatsDetail, Smtp and Filter details for the channel.
     * @throws MessageBusException
     */
    @Override
    public StatsResponse retrieveStatsForChannel(String channelKey) throws MessageBusException {
        return retrieveStatsForChannel(channelKey, null, null);
    }

    /**
     *
     * @param channelKey
     * @param sessionKey
     * @param startDate
     * @param endDate
     * @return StatsDetail, Smtp and Filter details for the session.
     * @throws MessageBusException
     */
    @Override
    public StatsResponse retrieveStatsForSession(
            String channelKey, String sessionKey, Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupDateQueryMap(
                startDate, endDate);
        StatsResponse response = (StatsResponse) this.jerseyBasedRequest(
                String.format(DefaultStatsClient.EMAIL_STATS_FOR_SESSION, channelKey, sessionKey), HttpMethod.GET, queryMap, null,
                StatsResponse.class);
        return response;
    }

    /**
     *
     * @param channelKey
     * @param sessionKey
     * @return StatsDetail, Smtp and Filter details for the session.
     * @throws MessageBusException
     */
    @Override
    public StatsResponse retrieveStatsForSession(String channelKey, String sessionKey) throws MessageBusException {
        return retrieveStatsForSession(channelKey, sessionKey, null, null);
    }
}
