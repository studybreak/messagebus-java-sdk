/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package com.messagebus.client.v4.client;

import com.messagebus.client.spi.StatsResponse;
import com.messagebus.client.v4.model.MessageBusException;

import java.util.Date;

/**
 * Defines the methods for getting stats on accounts, channels and sessions
 */
public interface MessageBusStatsClient extends MessageBusClient {

    public StatsResponse retrieveStatsForAccount(
            Date startDate, Date endDate) throws MessageBusException;

    public StatsResponse retrieveStatsForAccount() throws MessageBusException;

    public StatsResponse retrieveStatsForChannel(String channelKey, Date startDate, Date endDate) throws MessageBusException;

    public StatsResponse retrieveStatsForChannel(String channelKey) throws MessageBusException;

    public StatsResponse retrieveStatsForSession(String channelKey, String sessionKey, Date startDate, Date endDate) throws MessageBusException;

    public StatsResponse retrieveStatsForSession(String channelKey, String sessionKey) throws MessageBusException;
}
