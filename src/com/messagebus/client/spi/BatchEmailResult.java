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

/**
 * Used internally to format messages in the correct JSON format for
 * transmission to the server.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchEmailResult {

    private String messageId = null;
    private int messageStatus = 0;
    private String toEmail = null;

    @JsonCreator
    public BatchEmailResult(
            @JsonProperty("messageStatus") final int messageStatus,
            @JsonProperty("messageId") final String messageId,
            @JsonProperty("toEmail") final String toEmail) {

        this.messageStatus = messageStatus;
        this.messageId = messageId;
        this.toEmail = toEmail;
    }

    public String getMessageId() {
        return messageId;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public String getToEmail() {
        return toEmail;
    }
}
