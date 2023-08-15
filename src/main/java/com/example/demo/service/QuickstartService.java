package com.example.demo.service;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.BetaAnalyticsDataSettings;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuickstartService {

    public List<TimeOnPageData> getTimeOnPageData(String propertyId) throws Exception {
        try (BetaAnalyticsDataClient analyticsData = createAnalyticsDataClient()) {
            RunReportRequest request =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            .addDimensions(Dimension.newBuilder().setName("date"))
                            .addMetrics(Metric.newBuilder().setName("avgSessionDuration"))
                            .addDateRanges(DateRange.newBuilder().setStartDate("2023-03-31").setEndDate("today"))
                            .build();

            RunReportResponse response = analyticsData.runReport(request);

            List<TimeOnPageData> result = new ArrayList<>();
            for (Row row : response.getRowsList()) {
                String date = row.getDimensionValues(0).getValue();
                String avgSessionDurationStr = row.getMetricValues(0).getValue();
                double avgSessionDurationSeconds = Double.parseDouble(avgSessionDurationStr);

                result.add(new TimeOnPageData(date, avgSessionDurationSeconds));
            }

            return result;
        }
    }

    private BetaAnalyticsDataClient createAnalyticsDataClient() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(
            getClass().getResourceAsStream("/ketchby-aa2b2b177225.json")
        );

        BetaAnalyticsDataSettings analyticsDataSettings = BetaAnalyticsDataSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
            .build();

        return BetaAnalyticsDataClient.create(analyticsDataSettings);
    }
}
