/*
 * Copyright (c) 2013 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package com.messagebus.client.impl;

import com.messagebus.client.spi.*;
import com.messagebus.client.v5.client.MessageBusApiClient;
import com.messagebus.client.v5.client.MessageBusReportingClient;
import com.messagebus.client.v5.model.MessageBusException;

import java.io.OutputStream;

/**
 *
 * Client for generating reports, querying for their status and streaming them.
 *
 */
public class DefaultReportingClient extends SimpleHttpClient implements MessageBusReportingClient {

    private static final String REPORTS = "reports";
    private static final String REPORT = "report/%s";
    private static final String REPORT_STATUS = "report/%s/status";

    /**
     * Constructs a Reporting Client using the default domain.
     * @param apiKey
     */
    public DefaultReportingClient(final String apiKey) {
        super(apiKey);
    }

    /**
     * Constructs a Reporting Client using the specified domain.
     * @param apiKey
     * @param domain
     */
    public DefaultReportingClient(final String apiKey, final String domain) {
        super(apiKey, domain);
    }

    /**
     * Retrieve information on the current status of a report
     *
     * @param reportKey
     * @return current status of a report
     * @throws com.messagebus.client.v5.model.MessageBusException
     */
    public ReportStatusResponse getReportStatus(String reportKey) throws MessageBusException {
        return (ReportStatusResponse) this.jerseyBasedRequest(String.
                format(REPORT_STATUS, reportKey), HttpMethod.GET, null, null,
                ReportStatusResponse.class);
    }

    /**
     * Stream the contents of a finished report to an output stream
     *
     * @param reportKey
     * @param outputStream
     * @throws com.messagebus.client.v5.model.MessageBusException
     */
    public boolean streamReport(String reportKey, OutputStream outputStream) throws MessageBusException {
                return this.streamingRequest(String.
                format(REPORT, reportKey), HttpMethod.GET, null, null,outputStream);
    }

    /**
     *  Request a report containing email addresses between date ranges that resulted in a given scope criteria
     */
    public ReportCreateResponse createFeedbackReport(FeedbackReportRequest feedbackReportRequest)
            throws MessageBusException {
        return (ReportCreateResponse) this.jerseyBasedRequest(
                REPORTS, HttpMethod.POST, null, feedbackReportRequest,
                ReportCreateResponse.class);
    }

    /**
     *  Request a report containing stats by session by hour (total emails sent in to api, total sent out that bounced etc...)
     */
    public ReportCreateResponse createStatsReport(StatsReportRequest feedbackReportRequest)
            throws MessageBusException {
        return (ReportCreateResponse) this.jerseyBasedRequest(
                REPORTS, HttpMethod.POST, null, feedbackReportRequest,
                ReportCreateResponse.class);
    }

    /**
     *  Request a report of currently blocked emails
     */
    public ReportCreateResponse createBlocklistReport(BlocklistReportRequest feedbackReportRequest)
            throws MessageBusException {
        return (ReportCreateResponse) this.jerseyBasedRequest(
                REPORTS, HttpMethod.POST, null, feedbackReportRequest,
                ReportCreateResponse.class);
    }

}