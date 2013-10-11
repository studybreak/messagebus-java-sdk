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

import java.util.Date;

/**
 * Used internally to format messages in the correct JSON format for
 * transmission to the server.
 */
public class DefaultResponse {

    private int statusCode = 0;
    private String statusMessage = null;
    private Date statusTime = null;

    public DefaultResponse(final int statusCode, final String statusMessage,
                           final Date statusTime) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.statusTime = statusTime;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public Date getStatusTime() {
        return this.statusTime;
    }
}
