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


import com.messagebus.client.spi.DefaultResponse;
import com.sun.jersey.api.client.PartialRequestBuilderHelper;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import junit.framework.TestCase;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.mockito.Mock;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SimpleHttpClientTest extends TestCase {

    @Mock
    private SimpleHttpClient mockHttpClient;


    private class DefaultTestResponse extends DefaultResponse {

        @JsonCreator
        public DefaultTestResponse(
                @JsonProperty("testString") final String testString,
                @JsonProperty("statusCode") final int statusCode,
                @JsonProperty("statusMessage") final String statusMessage,
                @JsonProperty("statusTime") final Date statusTime
        ) {
            super(statusCode, statusMessage, statusTime);
        }
    }

    private class SimpleHttpClientMock extends SimpleHttpClient {

        private Class expectedReturnClass = null;
        private Map expectedQueryParams = null;
        private Object expectedEntityToPost = null;
        private String expectedPartialUri = null;

        public SimpleHttpClientMock(final String apiKey, final Class expectedReturnClass, final MultivaluedMap expectedQueryParams, final Object expectedEntityToPost, final String expectedPartialUri) {
            super(apiKey);
            this.expectedReturnClass = expectedReturnClass;
            this.expectedQueryParams = expectedQueryParams;
            this.expectedEntityToPost = expectedEntityToPost;
            this.expectedPartialUri = expectedPartialUri;
        }

        void validateCommonIssues(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
            PartialRequestBuilderHelper helper = new PartialRequestBuilderHelper(builder);
            final URI uri = webResource.getURI();
            StringBuilder expectedQueryString = new StringBuilder();
            if (expectedQueryParams != null && expectedQueryParams.size() > 0) {
                boolean isFirst = true;
                for (Object nextQueryParamKey : expectedQueryParams.keySet()) {
                    if (isFirst) {
                        expectedQueryString.append("?");
                        isFirst = false;
                    } else {
                        expectedQueryString.append("&");
                    }
                    expectedQueryString.append(nextQueryParamKey.toString());
                    expectedQueryString.append("=");
                    List<String> valueList = (List<String>) expectedQueryParams.get(nextQueryParamKey);
                    expectedQueryString.append(valueList.get(0));
                }
            }
            assertEquals(String.format("https://api.messagebus.com/v5/%s%s", expectedPartialUri, expectedQueryString.toString()), uri.toString());
            final Object entityToPost = helper.getEntity();
            assertEquals("TESTAPIKEY", helper.getMetaData().get("X-MessageBus-Key").get(0));
            String ua = helper.getMetaData().get("User-Agent").get(0).toString();
            boolean uaMatches = ua.matches("^MessageBusAPI:4.1.0-JAVA:\\d+.\\d+.\\d+\\_\\d+$");
            assertTrue("Retrieved user agent does match regex", uaMatches);
            if (returnClass != expectedReturnClass) {
                fail("Incorrect class used for return json conversion");
            }
            assertEquals(expectedEntityToPost, entityToPost);
        }

        @Override
        protected Object jerseyGet(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
            validateCommonIssues(webResource, builder, returnClass);
            return HttpMethod.GET;
        }

        @Override
        protected Object jerseyPost(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
            validateCommonIssues(webResource, builder, returnClass);
            return HttpMethod.POST;
        }

        @Override
        protected Object jerseyDelete(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
            validateCommonIssues(webResource, builder, returnClass);
            return HttpMethod.DELETE;
        }

        @Override
        protected Object jerseyPut(WebResource webResource, WebResource.Builder builder, final Class returnClass) {
            validateCommonIssues(webResource, builder, returnClass);
            return HttpMethod.PUT;
        }
    }

    private MultivaluedMap<String, String> generateTestQueryParams() {
        final MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        List<String> valList1 = new ArrayList<String>();
        valList1.add("value1");
        queryParams.put("key1", valList1);
        List<String> valList2 = new ArrayList<String>();
        valList2.add("value2");
        queryParams.put("key2", valList2);
        return queryParams;
    }

    public void testJerseyBasedRequestGet() throws Exception {

        String paritialUri = "partial/Uri/";
        final MultivaluedMap<String, String> queryParams = generateTestQueryParams();
        mockHttpClient = new SimpleHttpClientMock("TESTAPIKEY", DefaultTestResponse.class, queryParams, null, paritialUri);


        Object result = mockHttpClient.jerseyBasedRequest(
                paritialUri, SimpleHttpClient.HttpMethod.GET, queryParams, null,
                DefaultTestResponse.class);

        assertEquals(SimpleHttpClient.HttpMethod.GET, result);

    }

    public void testJerseyBasedRequestPostWithQueryParams() throws Exception {

        String partialUri = "partial/Uri/";
        final MultivaluedMap<String, String> queryParams = generateTestQueryParams();
        mockHttpClient = new SimpleHttpClientMock("TESTAPIKEY", DefaultTestResponse.class, queryParams, "TEST ENTITY1", partialUri);

        Object result = mockHttpClient.jerseyBasedRequest(
                partialUri, SimpleHttpClient.HttpMethod.POST, queryParams, "TEST ENTITY1",
                DefaultTestResponse.class);

        assertEquals(SimpleHttpClient.HttpMethod.POST, result);

    }

    public void testJerseyBasedRequestPostWithoutQueryParams() throws Exception {

        String partialUri = "partial/Uri/";
        mockHttpClient = new SimpleHttpClientMock("TESTAPIKEY", DefaultTestResponse.class, null, "TEST ENTITY1", partialUri);

        Object result = mockHttpClient.jerseyBasedRequest(
                partialUri, SimpleHttpClient.HttpMethod.POST, null, "TEST ENTITY1",
                DefaultTestResponse.class);

        assertEquals(SimpleHttpClient.HttpMethod.POST, result);

    }

    public void testJerseyBasedRequestDelete() throws Exception {

        String partialUri = "partial/Uri/";
        mockHttpClient = new SimpleHttpClientMock("TESTAPIKEY", DefaultTestResponse.class, null, null, partialUri);

        Object result = mockHttpClient.jerseyBasedRequest(
                partialUri, SimpleHttpClient.HttpMethod.DELETE, null, null,
                DefaultTestResponse.class);

        assertEquals(SimpleHttpClient.HttpMethod.DELETE, result);

    }

    public void testJerseyBasedRequestPut() throws Exception {

        String partialUri = "partial/Uri/";
        mockHttpClient = new SimpleHttpClientMock("TESTAPIKEY", DefaultTestResponse.class, null, null, partialUri);

        Object result = mockHttpClient.jerseyBasedRequest(
                partialUri, SimpleHttpClient.HttpMethod.PUT, null, null,
                DefaultTestResponse.class);

        assertEquals(SimpleHttpClient.HttpMethod.PUT, result);

    }

    public void testUserAgent() throws Exception {
        String userAgent = "^MessageBusAPI:4.1.0-JAVA:\\d+.\\d+.\\d+.*";
        String partialUri = "partial/Uri/";
        mockHttpClient = new SimpleHttpClientMock("TESTAPIKEY", DefaultTestResponse.class, null, null, partialUri);
        String mockUserAgent = mockHttpClient.getUserAgent();
        assertTrue("Expected " + userAgent + " got " + mockUserAgent, mockUserAgent.matches(userAgent));
    }



}
