![MB icon](https://www.messagebus.com/img/logo.png)

###Message Bus Java SDK

Message Bus is a cloud-based platform for easily sending email at scale and with complete insight into your messaging traffic and how recipients are responding to it. All platform functions are available via [REST API](http://www.messagebus.com/documentation) as well as the language-specific documentation, sample code, libraries, and/or compiled binaries contained in this SDK.

Samples include how to:

* Create sessions
* Send messages
* Use templates
* Check email stats

If you have questions not answered by the samples or the online documentation, please contact [support](mailto:support@messagebus.com).


####Compiling the SDK and running the tests

    mvn clean install

####Sending emails

    private MessageBusApiClient messageBusApiClient;

    public ExampleSendMessages(MessageBusApiClient messageBusApiClient) {
        this.messageBusApiClient = messageBusApiClient;
    }

    public static void main(String[] args) {

        final ExampleSendMessages exampleSend = new ExampleSendMessages(MessageBusFactory.createApiClient("<YOUR_API_KEY>", "https://api-v4.messagebus.com"));

        exampleSend.exampleSendMessages();

    }

    public void exampleSendMessages() {
        try {

            final String sessionKey = "<YOUR_SESSION_KEY>";

            final ArrayList<BatchEmailMessageRequestItem> messageBusEmails = new ArrayList<BatchEmailMessageRequestItem>();

            final Map<String, String> customHeaders = new HashMap<String, String>();
            customHeaders.put("x-messagebus-sdk", "java-sdk");

            final BatchEmailMessageRequestItem messageBusEmail1 = new BatchEmailMessageRequestItem();

            messageBusEmail1.setToEmail("bobby@example.com");
            messageBusEmail1.setToName("Bobby Flay");
            messageBusEmail1.setFromEmail("alice@example.com");
            messageBusEmail1.setFromName("Alice Waters");
            messageBusEmail1.setSubject("Sample Message with HTML body.");
            messageBusEmail1.setCustomHeaders(customHeaders);
            messageBusEmail1.setPlaintextBody("This is the plain text body.");
            messageBusEmail1.setHtmlBody("This is the <b>HTML</b>body.");
            messageBusEmail1.setSessionKey(sessionKey);

            messageBusEmails.add(messageBusEmail1);

            final BatchEmailMessageRequestItem messageBusEmail2 = new BatchEmailMessageRequestItem();

            messageBusEmail2.setToEmail("jamie@example.com");
            messageBusEmail2.setToName("Jamie Lauren");
            messageBusEmail2.setFromEmail("alice@example.com");
            messageBusEmail2.setFromName("Alice Waters");
            messageBusEmail2.setSubject("Sample Message with HTML body.");
            messageBusEmail2.setCustomHeaders(customHeaders);
            messageBusEmail2.setPlaintextBody("This is the plain text body.");
            messageBusEmail2.setHtmlBody("This is the <b>HTML</b>body.");
            messageBusEmail2.setSessionKey(sessionKey);

            messageBusEmails.add(messageBusEmail2);

            final BatchEmailSendRequest batchEmailSendRequest = new BatchEmailSendRequest(messageBusEmails);

            final BatchEmailResponse batchEmailResponse = this.messageBusApiClient.sendMessages(batchEmailSendRequest);

            if (batchEmailResponse.getStatusCode() == 202) {

                final List<BatchEmailResult> results = batchEmailResponse.getResults();

                System.out.println("Send Message results: ");

                for (BatchEmailResult result : results) {
                    System.out.println(String.format("To %s Message Id: %s Message Status: %s", result.getToEmail(), result.getMessageId(), result.getMessageStatus()));
                }
            } else {
                System.out.println(String.format("Could not Send Message. Status message: %s", batchEmailResponse.getStatusMessage()));
            }
        } catch (final MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }

####Checking email statistics

    private MessageBusStatsClient messageBusStatsClient;

    public ExampleGetStats(MessageBusStatsClient messageBusStatsClient) {
        this.messageBusStatsClient = messageBusStatsClient;
    }

    public static void main(final String[] args) {

        ExampleGetStats exampleGetStats = new ExampleGetStats(MessageBusFactory
                .createStatsClient("<YOUR_API_KEY>", "https://api-v4.messagebus.com"));

        exampleGetStats.exampleRetrieveAccountStats();
    }

    public void exampleRetrieveAccountStats() {

        final Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        final Date startDate = c.getTime();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        final Date endDate = c.getTime();

        try {
            StatsResponse statsResponse = messageBusStatsClient.retrieveStatsForAccount(startDate,
                    endDate);

            System.out.println(String.format("Account level stats from %s to %s:", startDate, endDate));
            if (statsResponse.getStatusCode() == 200) {

                System.out.println(String.format("Accept count: %d", statsResponse.getSmtp().getAcceptCount()));
                System.out.println(String.format("Bounce count: %d", statsResponse.getSmtp().getBounceCount()));
                System.out.println(String.format("Deferral count: %d", statsResponse.getSmtp().getDeferralCount()));


            } else {
                System.out.println(String.format("Could not get Stats. Status message: %s", statsResponse.getStatusMessage()));
            }
        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }

#### Checking email feedback data

    private MessageBusFeedbackClient messageBusFeedbackClient;

    public ExampleGetFeedback(MessageBusFeedbackClient messageBusFeedbackClient) {
        this.messageBusFeedbackClient = messageBusFeedbackClient;
    }

    public static void main(String[] args) {

        final ExampleGetFeedback exampleGetFeedback = new ExampleGetFeedback(MessageBusFactory.createFeedbackClient("<YOUR_API_KEY>", "https://api-v4.messagebus.com"));

        exampleGetFeedback.exampleGetAccountFeedback();
    }

    public void exampleGetAccountFeedback() {

        try {
            final FeedbackResponse feedbackResponse = this.messageBusFeedbackClient.retrieveFeedbackForAccount();

            final List<OpenResponseResult> opens = feedbackResponse.getOpens();

            System.out.println("Opens: ");
            for (OpenResponseResult openResult : opens) {
                System.out.println(String.format("Email: %s Count: %s Last Event Time: %s ", openResult.getEmail(), openResult.getCount(), openResult.getLastEventTime()));
            }

            final List<BouncesResponseResult> bounces = feedbackResponse.getBounces();

            System.out.println("Bounces: ");
            for (BouncesResponseResult bounceResult : bounces) {
                System.out.println(String.format("Email: %s Count: %s Last Event Time: %s ", bounceResult.getEmail(), bounceResult.getCount(), bounceResult.getLastEventTime()));
            }


        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        }
    }

#### License


    Copyright (c) 2013 Mail Bypass, Inc.

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
    with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the License is
    distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and limitations under the License
