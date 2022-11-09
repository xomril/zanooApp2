package com.example.zaznoo.models;

import java.util.List;

public class ZaznooResponse {
    private List<ZaznooActivity> activities;

    public List<ZaznooActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<ZaznooActivity> activities) {
        this.activities = activities;
    }
}
