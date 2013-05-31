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

@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
public class TemplateCreateRequest {

    public Map<String, String> customHeaders;

    public String htmlBody;
    public String name;
    public Map<String, String> options;
    public String plaintextBody;
    public String returnPath;
    public String sessionKey;

    public String subject;
    public String toEmail;
    public String toName;
    public String fromEmail;
    public String fromName;

    @JsonCreator
    public TemplateCreateRequest() {
    }

    @JsonCreator
    public TemplateCreateRequest(@JsonProperty("customHeaders") HashMap<String, String> customHeaders,
                                 @JsonProperty("fromEmail") String fromEmail,
                                 @JsonProperty("fromName") String fromName,
                                 @JsonProperty("htmlBody") String htmlBody,
                                 @JsonProperty("name") String name,
                                 @JsonProperty("options") HashMap<String, String> options,
                                 @JsonProperty("plaintextBody") String plaintextBody,
                                 @JsonProperty("returnPath") String returnPath,
                                 @JsonProperty("sessionKey") String sessionKey,
                                 @JsonProperty("subject") String subject,
                                 @JsonProperty("toEmail") String toEmail,
                                 @JsonProperty("toName") String toName) {

        this.customHeaders = customHeaders;
        this.htmlBody = htmlBody;
        this.name = name;
        this.options = options;
        this.plaintextBody = plaintextBody;
        this.returnPath = returnPath;
        this.sessionKey = sessionKey;
        this.subject = subject;
        this.toEmail = toEmail;
        this.toName = toName;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }

    public Map<String, String> getCustomHeaders() {
        return customHeaders;
    }

    public void setCustomHeaders(Map<String, String> customHeaders) {
        this.customHeaders = customHeaders;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getPlaintextBody() {
        return plaintextBody;
    }

    public void setPlaintextBody(String plaintextBody) {
        this.plaintextBody = plaintextBody;
    }

    public String getReturnPath() {
        return returnPath;
    }

    public void setReturnPath(String returnPath) {
        this.returnPath = returnPath;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
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
}
