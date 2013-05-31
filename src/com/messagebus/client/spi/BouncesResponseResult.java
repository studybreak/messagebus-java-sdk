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

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BouncesResponseResult extends FeedbackResponseResult {

    private Integer bounceCode = null;

    @JsonCreator
    public BouncesResponseResult(

            @JsonProperty("email") final String email,
            @JsonProperty("count") final Integer count,
            @JsonProperty("bounceCode") final Integer bounceCode,
            @JsonProperty("lastEventTime") final Date lastEventTime
    ) {
        super(email, count, lastEventTime);
        this.bounceCode = bounceCode;
    }

    public Integer getBounceCode() {
        return bounceCode;
    }

}

