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

import com.messagebus.client.spi.*;
import com.messagebus.client.v4.client.MessageBusApiClient;
import com.messagebus.client.v4.model.MessageBusException;

/**
 *
 * Client for managing channels, sessions and sending messages.
 *
 */
public class DefaultApiClient extends SimpleHttpClient implements MessageBusApiClient {

    private static final String SESSIONS = "channel/%s/sessions";

    private static final String SEND_EMAILS = "message/email/send";
    private static final String SESSION_RENAME = "channel/%s/session/%s/rename";
    private static final String CHANNELS = "channels";
    private static final String CHANNEL_CONFIG = "channel/%s/config";

    /**
     * Constructs a Api Client using the default domain.
     * @param apiKey
     */
    public DefaultApiClient(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs a Api Client using the specified domain.
     * @param apiKey
     * @param domain
     */
    public DefaultApiClient(final String apiKey, final String domain) {
        super(apiKey, domain);
    }

    /**
     *
     * @return All channels for the account.
     * @throws MessageBusException
     */
    public ChannelsResponse getChannels() throws MessageBusException {
        return (ChannelsResponse) this.jerseyBasedRequest(CHANNELS, HttpMethod.GET, null, null,
                ChannelsResponse.class);
    }

    /**
     *
     * @param channelKey
     * @return Config info the the channel.
     * @throws MessageBusException
     */
    public ConfigResponse getChannelConfig(String channelKey) throws MessageBusException {
        return (ConfigResponse) this.jerseyBasedRequest(String.
                format(CHANNEL_CONFIG, channelKey), HttpMethod.GET, null, null,
                ConfigResponse.class);
    }

    /**
     *
     * @param channelKey
     * @param name
     * @return The new session key and session name.
     * @throws MessageBusException
     */
    public SessionsCreateResponse createSession(final String channelKey, final String name) throws MessageBusException {
        SessionsCreateRequest request = new SessionsCreateRequest(name);
        return (SessionsCreateResponse) this.jerseyBasedRequest(String.
                format(SESSIONS, channelKey), HttpMethod.POST, null, request,
                SessionsCreateResponse.class);
    }

    /**
     *
     * @param channelKey
     * @return All sessions for the channel.
     * @throws MessageBusException
     */
    public SessionsResponse getSessions(String channelKey) throws MessageBusException {
        return (SessionsResponse) this.jerseyBasedRequest(String.
                format(SESSIONS, channelKey), HttpMethod.GET, null, null,
                SessionsResponse.class);
    }

    /**
     *
     * @param channelKey
     * @param sessionKey
     * @param newName
     * @return The new session name.
     * @throws MessageBusException
     */
    public SessionsRenameResponse renameSession(String channelKey, String sessionKey, String newName) throws MessageBusException {
        SessionsCreateRequest request = new SessionsCreateRequest(newName);
        return (SessionsRenameResponse) this.jerseyBasedRequest(String.
                format(SESSION_RENAME, channelKey, sessionKey), HttpMethod.PUT, null, request,
                SessionsRenameResponse.class);
    }

    /**
     *
     * @param messages
     * @return Success and failure count for each of the messages passed along with messageId and status for each to address.
     * @throws MessageBusException
     */
    public BatchEmailResponse sendMessages(BatchEmailSendRequest messages)
            throws MessageBusException {
        return (BatchEmailResponse) this.jerseyBasedRequest(
                SEND_EMAILS, HttpMethod.POST, null, messages,
                BatchEmailResponse.class);
    }

}