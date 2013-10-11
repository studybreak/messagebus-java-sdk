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

import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

/**
 * Used by tests and also when querying for error messages
 */
public class JsonFormatHelper {
    /*
     * Converts json to a Java object
     *
     * @param returnedJson
     * @param classToGenerate
     * @return object converted from JSON
     * @throws Exception
     */
    public static Object fromWireFormat(final String returnedJson,
                                        final Class classToGenerate) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        final StringReader reader = new StringReader(returnedJson);
        return objectMapper.readValue(reader, classToGenerate);
    }

    /**
     * Converts to Json from an annotated object
     *
     * @param object
     * @return object converted to JSON
     * @throws Exception
     */
    public static String toWireFormat(final Object object) throws Exception {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS"));
            objectMapper.writeValue(bos, object);
        } finally {
            bos.close();
        }
        return new String(bos.toByteArray(), Charset.forName("UTF-8"));
    }
}
