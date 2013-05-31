/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

public class FeedbackResponse extends DefaultResponse {

    private final List<BouncesResponseResult> bounces;
    private final List<ComplaintsResponseResult> complaints;
    private final List<UnsubscribeResponseResult> unsubs;
    private final List<ClickResponseResult> clicks;
    private final List<OpenResponseResult> opens;

    @JsonCreator
    public FeedbackResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("bounces") final List<BouncesResponseResult> bounces,
            @JsonProperty("complaints") final List<ComplaintsResponseResult> complaints,
            @JsonProperty("unsubscribes") final List<UnsubscribeResponseResult> unsubs,
            @JsonProperty("clicks") final List<ClickResponseResult> clicks,
            @JsonProperty("opens") final List<OpenResponseResult> opens
    ) {
        super(statusCode, statusMessage, statusTime);
        this.bounces = bounces;
        this.complaints = complaints;
        this.unsubs = unsubs;
        this.clicks = clicks;
        this.opens = opens;
    }

    public List<ComplaintsResponseResult> getComplaints() {
        return complaints;
    }

    public List<UnsubscribeResponseResult> getUnsubs() {
        return unsubs;
    }

    public List<ClickResponseResult> getClicks() {
        return clicks;
    }

    public List<OpenResponseResult> getOpens() {
        return opens;
    }

    public List<BouncesResponseResult> getBounces() {
        return this.bounces;
    }
}