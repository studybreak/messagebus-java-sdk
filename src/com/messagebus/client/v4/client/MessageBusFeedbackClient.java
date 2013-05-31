package com.messagebus.client.v4.client;

import com.messagebus.client.spi.FeedbackResponse;
import com.messagebus.client.v4.model.MessageBusException;

import java.util.Date;
import java.util.Set;

public interface MessageBusFeedbackClient extends MessageBusClient {

    public enum ScopeType {
        UNSUBS, BOUNCES, COMPLAINTS, CLICKS, OPENS, ALL;
    }

    public FeedbackResponse retrieveFeedbackForAccount() throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForAccount(Date startDate, Date endDate) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForChannel(String channelKey) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForChannel(String channelKey, Date startDate, Date endDate) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForSession(String channelKey, String sessionKey) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForSession(String channelKey, String sessionKey, Date startDate, Date endDate) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForAccount(Set<ScopeType> scopeTypes) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForAccount(Set<ScopeType> scopeTypes, Date startDate, Date endDate) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForChannel(Set<ScopeType> scopeTypes, String channelKey) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForChannel(Set<ScopeType> scopeTypes, String channelKey, Date startDate, Date endDate) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForSession(Set<ScopeType> scopeTypes, String channelKey, String sessionKey) throws MessageBusException;

    public FeedbackResponse retrieveFeedbackForSession(Set<ScopeType> scopeTypes, String channelKey, String sessionKey, Date startDate, Date endDate) throws MessageBusException;

}
