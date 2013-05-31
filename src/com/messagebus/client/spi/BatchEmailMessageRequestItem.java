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

import java.util.HashMap;
import java.util.Map;

/**
 * Used internally to format messages in the correct JSON format for
 * transmission to the server.
 */
@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
public class BatchEmailMessageRequestItem {

    private Map<String, String> customHeaders = null;
    private String fromEmail = null;
    private String fromName = null;
    private String htmlBody = null;
    private String plaintextBody = null;
    private String subject = null;
    private String toEmail = null;
    private String toName = null;
    private String sessionKey = null;

    public BatchEmailMessageRequestItem() {

    }

    @JsonCreator
    public BatchEmailMessageRequestItem(@JsonProperty("customHeaders") HashMap<String, String> customHeaders,
                                        @JsonProperty("fromEmail") String fromEmail,
                                        @JsonProperty("fromName") String fromName,
                                        @JsonProperty("htmlBody") String htmlBody,
                                        @JsonProperty("plaintextBody") String plaintextBody,
                                        @JsonProperty("subject") String subject,
                                        @JsonProperty("toEmail") String toEmail,
                                        @JsonProperty("toName") String toName,
                                        @JsonProperty("sessionKey") String sessionKey) {
        this.customHeaders = customHeaders;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.htmlBody = htmlBody;
        this.plaintextBody = plaintextBody;
        this.subject = subject;
        this.toEmail = toEmail;
        this.toName = toName;
        this.sessionKey = sessionKey;
    }

    public Map<String, String> getCustomHeaders() {
        return customHeaders;
    }

    public void setCustomHeaders(Map<String, String> customHeaders) {
        this.customHeaders = customHeaders;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String getPlaintextBody() {
        return plaintextBody;
    }

    public void setPlaintextBody(String plaintextBody) {
        this.plaintextBody = plaintextBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}