package com.messagebus.client.spi;

import java.util.Date;

public class FeedbackResponseResult {
    private Integer count = null;
    private String email = null;
    private Date lastEventTime = null;


    public FeedbackResponseResult(
            final String email,
            final Integer count,
            final Date lastEventTime) {

        this.count = count;
        this.email = email;
        this.lastEventTime = lastEventTime;
    }

    public Integer getCount() {
        return count;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastEventTime() {
        return lastEventTime;
    }
}
