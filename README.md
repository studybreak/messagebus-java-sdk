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

        final ExampleSendMessages exampleSend = new ExampleSendMessages(MessageBusFactory.createApiClient("<YOUR_API_KEY>", "https://api.messagebus.com"));

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
            messageBusEmail1.setReturnPath("bounces@example.com");
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
            messageBusEmail1.setReturnPath("bounces@example.com");
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

    private MessageBusReportingClient messageBusReportingClient;

    public SampleCreateStatsReport(MessageBusReportingClient messageBusReportingClient) {
        this.messageBusReportingClient = messageBusReportingClient;
    }

    public static void main(final String[] args) {

        SampleCreateStatsReport exampleGetReport = new SampleCreateStatsReport(MessageBusFactory
                .createReportingClient("<YOUR_API_KEY>", "https://api.messagebus.com"));
        exampleGetReport.examplePollAndSaveStatsToFile();

    }

    public void examplePollAndSaveStatsToFile() {
        final String fileName = "testStats.csv";

        try {
            FileOutputStream fos = new FileOutputStream(fileName);

            final Date endDate = new Date();
            final Date startDate = new Date(endDate.getTime() - 1000 * 60 * 60 * 24); //24 hour offset in millis

            final StatsReportRequest statsReportRequest = new StatsReportRequest(startDate, endDate, MessageBusReportingClient.ReportFormat.CSV);

            final ReportCreateResponse reportCreateResponse = messageBusReportingClient.createStatsReport(statsReportRequest);

            final String reportKey = reportCreateResponse.getReportKey();

            ReportStatusResponse reportStatus = messageBusReportingClient.getReportStatus(reportKey);

            while (!reportStatus.isFinished()) {
                reportStatus = messageBusReportingClient.getReportStatus(reportKey);
                try{
                    Thread.sleep(5000); // sleep 5 sec
                }catch(Exception e){}
            }

            if (reportStatus.isSuccessful()) {
                if (messageBusReportingClient.streamReport(reportKey, fos)) {
                    System.out.println(String.format("Report saved to %s", fileName));
                } else {
                    System.out.println(String.format("Unable to stream report for key %s", reportKey));
                }
            } else if (reportStatus.isEmpty()) {
                System.out.println(String.format("No Data Was Found For reportKey %s", reportKey));
            } else {
                System.out.println(String.format("Report with key %s returned status %s", reportKey, reportStatus.getReportStatus()));
            }


        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        } catch (FileNotFoundException fe) {
            System.out.println(String.format("Error Message: %s", fe.getMessage()));
        }
    }

#### Checking email feedback data

    String channelKey= "<YOUR_CHANNEL_KEY>";
    String sessionKey= "<YOUR_SESSION_KEY>";

    private MessageBusReportingClient messageBusReportingClient;

    public SampleCreateFeedbackReport(MessageBusReportingClient messageBusReportingClient) {
        this.messageBusReportingClient = messageBusReportingClient;
    }

    public static void main(final String[] args) {


        SampleCreateFeedbackReport exampleGetReport = new SampleCreateFeedbackReport(MessageBusFactory
                .createReportingClient("<YOUR_API_KEY>", "https://api.messagebus.com"));

        exampleGetReport.examplePollAndSaveFeedbackToFile();

    }

    public void examplePollAndSaveFeedbackToFile() {
        final String fileName = "testFeedback.csv";

        try {
            FileOutputStream fos = new FileOutputStream(fileName);

            final Date endDate = new Date();
            final Date startDate = new Date(endDate.getTime() - 1000 * 60 * 60 * 24); //24 hour offset in millis

            final FeedbackReportRequest feedbackReportRequest = new FeedbackReportRequest(MessageBusReportingClient.FeedbackReportScopeType.BOUNCES, startDate, endDate, channelKey,sessionKey,MessageBusReportingClient.ReportFormat.CSV);

            final ReportCreateResponse reportCreateResponse = messageBusReportingClient.createFeedbackReport(feedbackReportRequest);

            final String reportKey = reportCreateResponse.getReportKey();

            ReportStatusResponse reportStatus = messageBusReportingClient.getReportStatus(reportKey);

            while (!reportStatus.isFinished()) {
                reportStatus = messageBusReportingClient.getReportStatus(reportKey);
                try{
                    Thread.sleep(5000); // sleep 5 sec
                }catch(Exception e){}
            }

            if (reportStatus.isSuccessful()) {
                if (messageBusReportingClient.streamReport(reportKey, fos)) {
                    System.out.println(String.format("Report saved to %s", fileName));
                } else {
                    System.out.println(String.format("Unable to stream report for key %s", reportKey));
                }
            } else if (reportStatus.isEmpty()) {
                System.out.println(String.format("No Data Was Found For reportKey %s", reportKey));
            } else {
                System.out.println(String.format("Report with key %s returned status %s", reportKey, reportStatus.getReportStatus()));
            }


        } catch (MessageBusException e) {
            System.out.println(String.format("Error Message: %s \n \n Exception: %s", e.getStatusMessage(), e));
        } catch (FileNotFoundException fe) {
            System.out.println(String.format("Error Message: %s", fe.getMessage()));
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
