package com.example.zaznoo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "activityStatistics")
public class ActivityStatistics {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double current;
    private double lastWeek;
    private double total;

    public ActivityStatistics(double current, double lastWeek, double total) {
        this.current = current;
        this.lastWeek = lastWeek;
        this.total = total;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getLastWeek() {
        return lastWeek;
    }

    public void setLastWeek(double lastWeek) {
        this.lastWeek = lastWeek;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ActivityStatistics{" +
                "current=" + current +
                ", lastWeek=" + lastWeek +
                ", total=" + total +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
