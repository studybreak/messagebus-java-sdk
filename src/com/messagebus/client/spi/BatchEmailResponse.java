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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Used internally to format messages in the correct JSON format for
 * transmission to the server.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchEmailResponse extends DefaultResponse {

    private int failureCount = 0;

    private List<BatchEmailResult> results = null;

    private int successCount = 0;

    @JsonCreator
    public BatchEmailResponse(@JsonProperty("statusCode") final int statusCode,
                              @JsonProperty("statusMessage") final String statusMessage,
                              @JsonProperty("statusTime") final Date statusTime,
                              @JsonProperty("successCount") final int successCount,
                              @JsonProperty("failureCount") final int failureCount,
                              @JsonProperty("results") final List<BatchEmailResult> results) {
        super(statusCode, statusMessage, statusTime);
        this.successCount = successCount;
        this.failureCount = failureCount;

        this.results = results;

    }

    public int getFailureCount() {
        return this.failureCount;
    }

    public List<BatchEmailResult> getResults() {
        return this.results;
    }

    public int getSuccessCount() {
        return this.successCount;
    }

}