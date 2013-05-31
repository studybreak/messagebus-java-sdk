package com.messagebus.client.spi;

import org.codehaus.jackson.annotate.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonAutoDetect
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateResponseResult {

    public String templateKey;
    public String apiKey;

    public Map<String, Object> customHeaders;
    public String htmlBody;
    public List<HashMap<String, Object>> options;
    public String plaintextBody;
    public String returnPath;
    public String sessionKey;

    public String subject;
    public String toEmail;
    public String toName;
    public String fromEmail;
    public String fromName;

    private Date creationTime;

    @JsonCreator
    public TemplateResponseResult(
            @JsonProperty("templateKey") String templateKey,
            @JsonProperty("apiKey") String apiKey,
            @JsonProperty("creationTime") Date creationTime,

            @JsonProperty("customHeaders") HashMap<String, Object> customHeaders,
            @JsonProperty("htmlBody") String htmlBody,

            @JsonProperty("options") List<HashMap<String, Object>> options,
            @JsonProperty("plaintextBody") String plaintextBody,
            @JsonProperty("returnPath") String returnPath,
            @JsonProperty("sessionKey") String sessionKey,
            @JsonProperty("subject") String subject,
            @JsonProperty("toEmail") String toEmail,
            @JsonProperty("toName") String toName,
            @JsonProperty("fromEmail") String fromEmail,
            @JsonProperty("fromName") String fromName) {

        this.templateKey = templateKey;
        this.apiKey = apiKey;
        this.creationTime = creationTime;

        this.customHeaders = customHeaders;
        this.htmlBody = htmlBody;
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

    public String getTemplateKey() {
        return templateKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Map<String, Object> getCustomHeaders() {
        return customHeaders;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public List<HashMap<String, Object>> getOptions() {
        return options;
    }

    public String getPlaintextBody() {
        return plaintextBody;
    }

    public String getReturnPath() {
        return returnPath;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public String getSubject() {
        return subject;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getToName() {
        return toName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getFromName() {
        return fromName;
    }
}
