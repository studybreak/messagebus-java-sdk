/*
 * Copyright (c) 2013 Mail Bypass, Inc.
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

/**
 * Used internally to format messages in the correct JSON format for
 * transmission to the server.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionResponse extends DefaultResponse {

    private String apiName = null;
    private String apiVersion = null;

    public String getApiName() {
        return this.apiName;
    }

    public String getApiVersion() {
        return this.apiVersion;
    }


    @JsonCreator
    public VersionResponse(
            @JsonProperty("statusCode") final int statusCode,
            @JsonProperty("statusMessage") final String statusMessage,
            @JsonProperty("statusTime") final Date statusTime,
            @JsonProperty("APIName") final String apiName,
            @JsonProperty("APIVersion") final String apiVersion) {
        super(statusCode, statusMessage, statusTime);
        this.apiName = apiName;
        this.apiVersion = apiVersion;
    }
}
