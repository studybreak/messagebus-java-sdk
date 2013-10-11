/*
 * Copyright (c) 2013 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package com.messagebus.client.v5.client;

import com.messagebus.client.spi.*;
import com.messagebus.client.v5.model.MessageBusException;

/**
 * Defines the general methods for sending messages and managing channels and sessions.
 */
public interface MessageBusApiClient extends MessageBusClient {


    public BatchEmailResponse sendMessages(BatchEmailSendRequest batchEmailSendRequest) throws MessageBusException;

    public ChannelsResponse getChannels() throws MessageBusException;

    public ConfigResponse getChannelConfig(String channelKey) throws MessageBusException;

    public SessionsCreateResponse createSession(String channelKey, String name) throws MessageBusException;

    public SessionsResponse getSessions(String channelKey) throws MessageBusException;

    public SessionsRenameResponse renameSession(String channelKey, String sessionKey, String newName) throws MessageBusException;


}