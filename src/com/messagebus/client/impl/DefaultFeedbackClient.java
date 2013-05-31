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

import com.messagebus.client.spi.FeedbackResponse;
import com.messagebus.client.v4.client.MessageBusFeedbackClient;
import com.messagebus.client.v4.model.MessageBusException;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Date;
import java.util.Set;

/**
 *
 * Client for querying on email addresses associated with bounces, complaints, unsubscribes, clicks and opens.
 *
 */
public class DefaultFeedbackClient extends SimpleHttpClient implements MessageBusFeedbackClient {

    private static final String FEEDBACK_FOR_ACCOUNT = "feedback";
    private static final String FEEDBACK_FOR_CHANNEL = "feedback/channel/%s";
    private static final String FEEDBACK_FOR_SESSION = "feedback/channel/%s/session/%s";

    /**
     * Constructs a Feedback Client using the default domain.
     * @param apiKey
     */
    public DefaultFeedbackClient(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs a Feedback Client using the specified domain.
     * @param apiKey
     * @param domain
     */
    public DefaultFeedbackClient(final String apiKey, final String domain) {
        super(apiKey, domain);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return All feedback for the account.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForAccount(
            Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupDateQueryMap(
                startDate, endDate);
        return (FeedbackResponse) this.jerseyBasedRequest(
                DefaultFeedbackClient.FEEDBACK_FOR_ACCOUNT, HttpMethod.GET, queryMap, null,
                FeedbackResponse.class);
    }

    /**
     *
     * @return All feedback for the account.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForAccount() throws MessageBusException {
        return retrieveFeedbackForAccount(null, null);
    }

    /**
     *
     * @param channelKey
     * @param startDate
     * @param endDate
     * @return All feedback for the channel.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForChannel(
            String channelKey, Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupDateQueryMap(
                startDate, endDate);
        return (FeedbackResponse) this.jerseyBasedRequest(
                String.format(DefaultFeedbackClient.FEEDBACK_FOR_CHANNEL, channelKey), HttpMethod.GET, queryMap, null,
                FeedbackResponse.class);

    }

    /**
     *
     * @param channelKey
     * @return All feedback for the channel.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForChannel(String channelKey) throws MessageBusException {
        return retrieveFeedbackForChannel(channelKey, null, null);
    }

    /**
     *
     * @param channelKey
     * @param sessionKey
     * @param startDate
     * @param endDate
     * @return All feedback for the session.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForSession(
            String channelKey, String sessionKey, Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupDateQueryMap(
                startDate, endDate);
        return (FeedbackResponse) this.jerseyBasedRequest(
                String.format(DefaultFeedbackClient.FEEDBACK_FOR_SESSION, channelKey, sessionKey), HttpMethod.GET, queryMap, null,
                FeedbackResponse.class);
    }

    /**
     *
     * @param channelKey
     * @param sessionKey
     * @return All feedback for the session.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForSession(String channelKey, String sessionKey) throws MessageBusException {
        return retrieveFeedbackForSession(channelKey, sessionKey, null, null);
    }

    /**
     *
     * @param scope
     * @param startDate
     * @param endDate
     * @return Scope specific feedback for the account.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForAccount(Set<ScopeType> scope,
                                                       Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupFeedbackQueryMap(scope,
                startDate, endDate);
        return (FeedbackResponse) this.jerseyBasedRequest(
                DefaultFeedbackClient.FEEDBACK_FOR_ACCOUNT, HttpMethod.GET, queryMap, null,
                FeedbackResponse.class);
    }

    /**
     *
     * @param scope
     * @return Scope specific feedback for the account.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForAccount(Set<ScopeType> scope) throws MessageBusException {
        return retrieveFeedbackForAccount(scope, null, null);
    }

    /**
     *
     * @param scope
     * @param channelKey
     * @param startDate
     * @param endDate
     * @return Scope specific feedback for the channel.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForChannel(Set<ScopeType> scope,
                                                       String channelKey, Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupFeedbackQueryMap(scope,
                startDate, endDate);
        return (FeedbackResponse) this.jerseyBasedRequest(
                String.format(DefaultFeedbackClient.FEEDBACK_FOR_CHANNEL, channelKey), HttpMethod.GET, queryMap, null,
                FeedbackResponse.class);

    }

    /**
     *
     * @param scope
     * @param channelKey
     * @return Scope specific feedback for the channel.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForChannel(Set<ScopeType> scope, String channelKey) throws MessageBusException {
        return retrieveFeedbackForChannel(scope, channelKey, null, null);
    }

    /**
     *
     * @param scope
     * @param channelKey
     * @param sessionKey
     * @param startDate
     * @param endDate
     * @return Scope specific feedback for the session.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForSession(Set<ScopeType> scope,
                                                       String channelKey, String sessionKey, Date startDate, Date endDate) throws MessageBusException {
        final MultivaluedMap<String, String> queryMap = this.setupFeedbackQueryMap(scope,
                startDate, endDate);
        return (FeedbackResponse) this.jerseyBasedRequest(
                String.format(DefaultFeedbackClient.FEEDBACK_FOR_SESSION, channelKey, sessionKey), HttpMethod.GET, queryMap, null,
                FeedbackResponse.class);
    }

    /**
     *
     * @param scope
     * @param channelKey
     * @param sessionKey
     * @return Scope specific feedback for the session.
     * @throws MessageBusException
     */
    @Override
    public FeedbackResponse retrieveFeedbackForSession(Set<ScopeType> scope, String channelKey, String sessionKey) throws MessageBusException {
        return retrieveFeedbackForSession(scope, channelKey, sessionKey, null, null);
    }

}
