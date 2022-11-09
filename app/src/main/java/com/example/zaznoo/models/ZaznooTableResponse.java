package com.example.zaznoo.models;

import java.util.List;

public class ZaznooTableResponse {
    private List<ZaznooTable> top3;
    private List<ZaznooTable> activities;

    public List<ZaznooTable> getZaznooTableList() {
        return activities;
    }

    public void setZaznooTableList(List<ZaznooTable> zaznooTableList) {
        this.activities = zaznooTableList;
    }

    public List<ZaznooTable> getTop3() {
        return top3;
    }

    public void setTop3(List<ZaznooTable> top3) {
        this.top3 = top3;
    }

    public List<ZaznooTable> getActivities() {
        return activities;
    }

    public void setActivities(List<ZaznooTable> activities) {
        this.activities = activities;
    }
}
