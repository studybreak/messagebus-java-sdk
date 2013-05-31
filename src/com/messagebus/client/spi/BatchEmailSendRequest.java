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

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.util.List;

/**
 * Used internally to format messages in the correct JSON format for
 * transmission to the server.
 */
@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
public class BatchEmailSendRequest {

    private List<BatchEmailMessageRequestItem> messages = null;

    @JsonCreator
    public BatchEmailSendRequest(@JsonProperty("messages")
                                 final List<BatchEmailMessageRequestItem> messages) {
        this.messages = messages;
    }

    public List<BatchEmailMessageRequestItem> getMessages() {
        return this.messages;
    }
}