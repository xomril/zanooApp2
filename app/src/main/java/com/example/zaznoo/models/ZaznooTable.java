package com.example.zaznoo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "zaznooTable")
public class ZaznooTable {

    private String name;
    private Double distance;
    @PrimaryKey(autoGenerate = false)
    private int place;
    private boolean selected;

    public ZaznooTable() {
    }

    public ZaznooTable(String name, Double distance, int place, boolean selected) {
        this.name = name;
        this.distance = distance;
        this.place = place;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "ZaznooTable{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", place=" + place +
                ", selected=" + selected +
                '}';
    }
}
