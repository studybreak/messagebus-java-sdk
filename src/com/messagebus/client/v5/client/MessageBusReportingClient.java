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

import com.messagebus.client.impl.SimpleHttpClient;
import com.messagebus.client.spi.*;
import com.messagebus.client.v5.model.MessageBusException;

import java.io.OutputStream;

/**
 * Defines the general methods for sending messages and managing channels and sessions.
 */
public interface MessageBusReportingClient extends MessageBusClient {


    public enum FeedbackReportScopeType {
        UNSUBSCRIBES, BOUNCES, COMPLAINTS, CLICKS, OPENS, BLOCKS;
    }

    public enum ReportFormat {
        CSV, JSON;
    }


    public ReportStatusResponse getReportStatus(String reportKey) throws MessageBusException;

    public boolean streamReport(String reportKey, OutputStream outputStream) throws MessageBusException;

    public ReportCreateResponse createFeedbackReport(FeedbackReportRequest feedbackReportRequest)  throws MessageBusException;

    public ReportCreateResponse createStatsReport(StatsReportRequest feedbackReportRequest)
            throws MessageBusException;

    public ReportCreateResponse createBlocklistReport(BlocklistReportRequest feedbackReportRequest)
            throws MessageBusException;

}