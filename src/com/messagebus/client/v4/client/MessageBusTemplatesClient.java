/*
 * Copyright (c) 2012 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */

package com.messagebus.client.v4.client;

import com.messagebus.client.spi.*;
import com.messagebus.client.v4.model.MessageBusException;

import java.util.List;
import java.util.Map;

/**
 * Defines the methods for managing and using templates
 */
public interface MessageBusTemplatesClient extends MessageBusClient {

    public BatchEmailResponse sendMessages(String templateKey, List<Map<String, String>> templateMergeFields) throws MessageBusException;

    public TemplateCreateResponse createTemplate(TemplateCreateRequest template) throws MessageBusException;

    public TemplateResponse getTemplate(String templateKey) throws MessageBusException;

    public TemplatesResponse getTemplates() throws MessageBusException;

    public TemplateVersionResponse templateVersion() throws MessageBusException;


}