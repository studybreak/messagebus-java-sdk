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

import com.messagebus.client.Info;
import com.messagebus.client.spi.DefaultResponse;
import com.messagebus.client.spi.JsonFormatHelper;
import com.messagebus.client.spi.VersionResponse;
import com.messagebus.client.v5.client.MessageBusClient;
import com.messagebus.client.v5.model.MessageBusException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.core.MultivaluedMap;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Implements methods for actual communication with the server
 */
public class SimpleHttpClient implements MessageBusClient {

    private static final String API_DOMAIN = "https://api.messagebus.com";

    private String path = "v5";

    protected enum HttpMethod {
        DELETE, GET, POST, PUT
    }

    private static final Integer TIMEOUT_SETTING = 5000;
    private static final Integer READ_TIMEOUT_SETTING = 1000;
    private static final Integer STREAMING_TIMEOUT_SETTING = 1200000;
    private static final String VERSION = "version";
    private String apiKey = null;

    private String domain = null;

    public SimpleHttpClient(final String apiKey) {
        this.domain = API_DOMAIN;
        this.apiKey = apiKey;
    }

    public SimpleHttpClient(final String apiKey, final String domain) {
        this.domain = domain;
        this.apiKey = apiKey;
    }

    public String getUserAgent() {
        return Info.getUserAgent();
    }

    private String formatRESTUri(final String partialUrl) {
        if (partialUrl.equals("version")){
            return String.format("%s/%s", this.domain, partialUrl);
        }else
            return String.format("%s/%s/%s", this.domain, this.path, partialUrl);
    }

    private String formatRESTUri(final String partialUrl, String path) {
        return String.format("%s/%s/%s", this.domain, path, partialUrl);
    }

    public String getApiKey() {
        return this.apiKey;
    }


    protected Object jerseyBasedRequest(final String partialUri,
                                        final HttpMethod method,
                                        final MultivaluedMap<String, String> queryParams,
                                        final Object requestEntity, final Class returnClass)
            throws MessageBusException {
        try {
            Client client;
            try {
                final ClientConfig config = new DefaultClientConfig();
                config.getClasses().add(JacksonJsonProvider.class);
                client = Client.create(config);

                client.setConnectTimeout(TIMEOUT_SETTING);
                client.setReadTimeout(READ_TIMEOUT_SETTING);

            } catch (final Exception e) {
                throw new MessageBusException(500, "problem with SSL setup: "
                        + e.getMessage());
            }

            String uri = this.formatRESTUri(partialUri);

            WebResource webResource = client.resource(uri);

            if (queryParams != null) {
                webResource = webResource.queryParams(queryParams);
            }
            WebResource.Builder builder = webResource.header(
                    "X-MessageBus-Key", this.apiKey);
            builder = builder.header("User-Agent", Info.getUserAgent());

            if (requestEntity != null) {

                builder = builder.entity(requestEntity,
                        "application/json; charset=utf-8");
            }

            switch (method) {
                case GET:
                    return jerseyGet(webResource, builder, returnClass);
                case POST:
                    return jerseyPost(webResource, builder, returnClass);
                case PUT:
                    return jerseyPut(webResource, builder, returnClass);
                case DELETE:
                    return jerseyDelete(webResource, builder, returnClass);
                default:
                    throw new RuntimeException("Unsupported HttpMethod type: "
                            + method.toString());
            }
        } catch (final UniformInterfaceException ue) {
            throw messageBusExceptionFromUniformInterfaceException(ue, returnClass);
        } catch (final IllegalArgumentException e) {
            throw new MessageBusException(400, e.getCause().getMessage());
        } catch (final Exception e) {
            throw new MessageBusException(500, e.getMessage());
        }
    }


    protected boolean streamingRequest(final String partialUri,
                                       final HttpMethod method,
                                       final MultivaluedMap<String, String> queryParams,
                                       final Object requestEntity, final OutputStream os)
            throws MessageBusException {
        try {
            Client client;
            try {
                final ClientConfig config = new DefaultClientConfig();
                client = Client.create(config);

                client.setConnectTimeout(STREAMING_TIMEOUT_SETTING);

            } catch (final Exception e) {
                throw new MessageBusException(500, "problem with SSL setup: "
                        + e.getMessage());
            }

            String uri = this.formatRESTUri(partialUri);
            if (partialUri.matches("version")) {
                uri = this.formatRESTUri(partialUri, "api");
            }

            WebResource webResource = client.resource(uri);

            if (queryParams != null) {
                webResource = webResource.queryParams(queryParams);
            }
            WebResource.Builder builder = webResource.header(
                    "X-MessageBus-Key", this.apiKey);
            builder = builder.header("User-Agent", Info.getUserAgent());

            if (requestEntity != null) {

                builder = builder.entity(requestEntity,
                        "application/json; charset=utf-8");
            }

            ClientResponse response = builder.get(ClientResponse.class);

            if (response.getStatus() >= 400) {
                String msg = "Internal Server Error";
                switch (response.getStatus()) {
                    case 400:
                        msg = "Invalid Request";
                        break;
                    case 401:
                        msg = "Unauthorized";
                        break;
                    case 402:
                        msg = "Request Failed";
                        break;
                    case 403:
                        msg = "Forbidden";
                        break;
                    case 404:
                        msg = " Not Found";
                        break;
                }
                throw new MessageBusException(response.getStatus(), msg);
            }

            InputStream is = response.getEntityInputStream();
            byte[] buffer = new byte[256];
            int bytesRead = 0;
            int totalBytesRead=0;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
                totalBytesRead=totalBytesRead+bytesRead;
            }
            is.close();
            if (totalBytesRead > 0)
                return true;
            else
                return false;

        } catch (final IllegalArgumentException e) {
            throw new MessageBusException(400, e.getCause().getMessage());
        } catch (final Exception e) {
            throw new MessageBusException(500, e.getMessage());
        }
    }

    Object jerseyGet(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
        return builder.get(returnClass);
    }

    Object jerseyPost(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
        return builder.post(returnClass);
    }

    Object jerseyDelete(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
        return builder.delete(returnClass);
    }

    Object jerseyPut(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
        return builder.put(returnClass);
    }


    public String getVersion() throws MessageBusException {

        VersionResponse versionResponse = (VersionResponse) this.jerseyBasedRequest(
                SimpleHttpClient.VERSION, HttpMethod.GET, null, null,
                VersionResponse.class);
        return String.format("%s %s", versionResponse.getApiName(), versionResponse.getApiVersion());
    }


    MultivaluedMap<String, String> setupDateQueryMap(
            final Date startDate, final Date endDate) {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        if (startDate != null) {
            map.add("startDate", sdf.format(startDate));
        }
        if (endDate != null) {
            map.add("endDate", sdf.format(endDate));
        }
        return map;
    }


    private MessageBusException messageBusExceptionFromUniformInterfaceException(UniformInterfaceException ue, Class returnClass) {
        String statusMessage = ue.getMessage();
        try {

            //try to parse out statusMessage from JSON rersponse if it may contain useful information
            if (ue.getResponse() != null && ue.getResponse().getEntityInputStream() != null) {
                final char[] buffer = new char[0x10000];
                StringBuilder out = new StringBuilder();
                Reader in = new InputStreamReader(ue.getResponse().getEntityInputStream(), "UTF-8");
                int read;
                do {
                    read = in.read(buffer, 0, buffer.length);
                    if (read > 0) {
                        out.append(buffer, 0, read);
                    }
                } while (read >= 0);
                String tempStatusString = out.toString();
                try {
                    DefaultResponse response = (DefaultResponse) JsonFormatHelper.fromWireFormat(tempStatusString, returnClass);
                    if (response.getStatusMessage() != null && !response.getStatusMessage().equals("")) {
                        statusMessage = response.getStatusMessage();
                    }
                } catch (Exception e) {
                    statusMessage = tempStatusString;
                }
            }
        } catch (Exception e) {
            statusMessage = "Unable to parse input stream: " + e.getMessage();
        }

        return new MessageBusException(ue.getResponse().getStatus(),
                statusMessage);
    }

}

