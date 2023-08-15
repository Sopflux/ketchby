package com.example.demo.service;

public class TimeOnPageData {
    private String date;
    private double avgSessionDurationSeconds;

    public TimeOnPageData(String date, double avgSessionDurationSeconds) {
        this.date = date;
        this.avgSessionDurationSeconds = avgSessionDurationSeconds;
    }

    // Getters and setters for date and avgSessionDurationSeconds
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAvgSessionDurationSeconds() {
        return avgSessionDurationSeconds;
    }

    public void setAvgSessionDurationSeconds(double avgSessionDurationSeconds) {
        this.avgSessionDurationSeconds = avgSessionDurationSeconds;
    }
}
