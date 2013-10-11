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
import com.messagebus.client.v5.client.MessageBusTemplatesClient;
import com.messagebus.client.v5.model.MessageBusException;

import java.util.List;
import java.util.Map;

/**
 *
 * Client for creating, managing and using templates.
 *
 */
public class DefaultTemplatesClient extends SimpleHttpClient implements MessageBusTemplatesClient {

    private static final String TEMPLATES_DOMAIN = "https://templates.messagebus.com";

    private static final String TEMPLATE = "template/%s";
    private static final String TEMPLATES = "templates";
    private static final String TEMPLATE_SEND = "templates/send";
    private static final String TEMPLATE_VERSION = "templates/version";

    /**
     * Constructs a Template Client using the default domain.
     * @param apiKey
     */
    public DefaultTemplatesClient(final String apiKey) {
        super(apiKey, TEMPLATES_DOMAIN);
    }

    /**
     * Constructs a Template Client using the specified domain.
     * @param apiKey
     * @param domain
     */
    public DefaultTemplatesClient(final String apiKey, final String domain) {
        super(apiKey, domain);
    }

    /**
     *
     * @param templateKey
     * @param templateMergeFields
     * @return Success and failure count for each of the merge fields passed along with messageId and status for each to address.
     * @throws MessageBusException
     */
    @Override
    public BatchEmailResponse sendMessages(String templateKey, List<Map<String, String>> templateMergeFields)
            throws MessageBusException {
        return (BatchEmailResponse) this.jerseyBasedRequest(
                TEMPLATE_SEND, HttpMethod.POST, null, new TemplateSendRequest(templateKey, templateMergeFields),
                BatchEmailResponse.class);
    }

    /**
     *
     * @param template
     * @return TemplateKey of the created Template.
     * @throws MessageBusException
     */
    @Override
    public TemplateCreateResponse createTemplate(TemplateCreateRequest template) throws MessageBusException {
        return (TemplateCreateResponse) this.jerseyBasedRequest(
                TEMPLATES, HttpMethod.POST, null, template,
                TemplateCreateResponse.class);
    }

    /**
     *
     * @return All templates for the account.
     * @throws MessageBusException
     */
    @Override
    public TemplatesResponse getTemplates() throws MessageBusException {
        return (TemplatesResponse) this.jerseyBasedRequest(
                TEMPLATES, HttpMethod.GET, null, null,
                TemplatesResponse.class);
    }

    /**
     *
     * @return Current version of the template server code.
     * @throws MessageBusException
     */
    @Override
    public TemplateVersionResponse templateVersion() throws MessageBusException {
        return (TemplateVersionResponse) this.jerseyBasedRequest(
                TEMPLATE_VERSION, HttpMethod.GET, null, null,
                TemplateVersionResponse.class);
    }

    /**
     *
     * @param templateKey
     * @return A Template for the templateKey passed.
     * @throws MessageBusException
     */
    @Override
    public TemplateResponse getTemplate(String templateKey) throws MessageBusException {
        return (TemplateResponse) this.jerseyBasedRequest(
                String.format(TEMPLATE, templateKey), HttpMethod.GET, null, null,
                TemplateResponse.class);
    }
}

