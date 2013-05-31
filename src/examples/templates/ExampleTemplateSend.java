package examples.templates;

import com.messagebus.client.MessageBusFactory;
import com.messagebus.client.spi.BatchEmailResponse;
import com.messagebus.client.spi.BatchEmailResult;
import com.messagebus.client.v4.client.MessageBusTemplatesClient;
import com.messagebus.client.v4.model.MessageBusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This example uses a template to send.
 *
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 *
 * messagebus-java-sdk>  mvn clean install
 *
 * To run this example use Maven. From the top directory :
 *
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.templates.ExampleTemplateSend"
 *
 */
public class ExampleTemplateSend {

    private MessageBusTemplatesClient messageBusTemplatesClient;

    public ExampleTemplateSend(MessageBusTemplatesClient messageBusTemplatesClient) {
        this.messageBusTemplatesClient = messageBusTemplatesClient;
    }

    public static void main(String[] args) {

        final ExampleTemplateSend exampleTemplate = new ExampleTemplateSend(MessageBusFactory.createTemplatesClient("<YOUR_API_KEY>", "https://templates-v4-jy01-prod.messagebus.com"));

        exampleTemplate.exampleTemplateSend();

    }

    public void exampleTemplateSend() {
        try {

            final String templateKey = "<YOUR_TEMPLATE_KEY>";
            final String sessionKey = "<YOUR_SESSION_KEY>";

            final Map<String, String> templateMergeFields1 = new HashMap<String, String>();
            templateMergeFields1.put("rcpt_email", "bob@example.com");
            templateMergeFields1.put("rcpt_name", "Bob");
            templateMergeFields1.put("subject", "Sample Message with HTML body.");
            templateMergeFields1.put("text_value", "test #1");
            templateMergeFields1.put("sender_email", "alice@example.com");
            templateMergeFields1.put("sender_name", "Alice Waters");
            templateMergeFields1.put("sessionKey", sessionKey);

            final Map<String, String> templateMergeFields2 = new HashMap<String, String>();
            templateMergeFields2.put("rcpt_email", "jane@example.com");
            templateMergeFields2.put("rcpt_name", "Jane");
            templateMergeFields2.put("subject", "Sample Message with HTML body.");
            templateMergeFields2.put("text_value", "test #2");
            templateMergeFields2.put("sender_email", "alice@example.com");
            templateMergeFields2.put("sender_name", "Alice Waters");
            templateMergeFields2.put("sessionKey", sessionKey);

            final List<Map<String, String>> mergeFields = new ArrayList<Map<String, String>>();
            mergeFields.add(templateMergeFields1);
            mergeFields.add(templateMergeFields2);

            final BatchEmailResponse templateSendResponse = this.messageBusTemplatesClient.sendMessages(templateKey, mergeFields);

            if (templateSendResponse.getStatusCode() == 202) {

                final List<BatchEmailResult> results = templateSendResponse.getResults();

                System.out.println("Template send results: ");

                for (BatchEmailResult result : results) {
                    System.out.println(String.format("To %s Message Id: %s Message Status: %s", result.getToEmail(), result.getMessageId(), result.getMessageStatus()));
                }
            } else {
                System.out.println(String.format("Could not create Template. Status message: %s", templateSendResponse.getStatusMessage()));
            }
        } catch (final MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }
}