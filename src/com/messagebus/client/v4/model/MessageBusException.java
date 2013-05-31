/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package com.messagebus.client.v4.model;

/**
 * Class to hold returned exception data
 * Used extensively whenever success depends on external conditions,
 * like network or disk i/o, ie: email sends
 */
public class MessageBusException extends Exception {

    private static final long serialVersionUID = 3419690593415816767L;

    private int statusCode = 0;

    private String statusMessage = null;

    /**
     * @param statusCode
     * @param statusMessage
     */
    public MessageBusException(final int statusCode, final String statusMessage) {
        super("Communication Failed with error code:" + statusCode + " - "
                + statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    /**
     * @return status code from server
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * @return status message from server
     */
    public String getStatusMessage() {
        return this.statusMessage;
    }

    /**
     * @return is error possibly due to a short term server issue
     */
    public boolean isRetryable() {
        return (this.statusCode > 500) && (this.statusCode < 600);
    }
}
