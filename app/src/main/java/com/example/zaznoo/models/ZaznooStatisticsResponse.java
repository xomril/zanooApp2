package com.example.zaznoo.models;

public class ZaznooStatisticsResponse {

    private ActivityStatistics Runs;
    private ActivityStatistics Ride;
    private ActivityStatistics Swim;
    private ActivityStatistics Walk;

    public ZaznooStatisticsResponse(ActivityStatistics runs, ActivityStatistics ride, ActivityStatistics swim, ActivityStatistics walk) {
        Runs = runs;
        Ride = ride;
        Swim = swim;
        Walk = walk;
    }

    public ActivityStatistics getRuns() {
        return Runs;
    }

    public void setRuns(ActivityStatistics runs) {
        Runs = runs;
    }

    public ActivityStatistics getRide() {
        return Ride;
    }

    public void setRide(ActivityStatistics ride) {
        Ride = ride;
    }

    public ActivityStatistics getSwim() {
        return Swim;
    }

    public void setSwim(ActivityStatistics swim) {
        Swim = swim;
    }

    public ActivityStatistics getWalk() {
        return Walk;
    }

    public void setWalk(ActivityStatistics walk) {
        Walk = walk;
    }

    @Override
    public String toString() {
        return "ZaznooStatisticsResponse{" +
                "Runs=" + Runs +
                ", Ride=" + Ride +
                ", Swim=" + Swim +
                ", Walk=" + Walk +
                '}';
    }
}
