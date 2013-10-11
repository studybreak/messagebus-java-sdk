/*
 * Copyright (c) 2013 Mail Bypass, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License
 */
package samples.reporting;

import com.messagebus.client.MessageBusFactory;
import com.messagebus.client.spi.*;
import com.messagebus.client.v5.client.MessageBusReportingClient;
import com.messagebus.client.v5.model.MessageBusException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * This example tells Message Bus to asynchronously create a feedback report
 * <p/>
 * Update with YOUR_API_KEY, verify the DOMAIN and use Maven to compile. From the top directory :
 * <p/>
 * messagebus-java-sdk>  mvn clean install
 * <p/>
 * To run this example use Maven. From the top directory :
 * <p/>
 * messagebus-java-sdk> mvn exec:java  -Dexec.mainClass="examples.api.ExampleGetReport"
 */
public class SampleCreateFeedbackReport {

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
}

