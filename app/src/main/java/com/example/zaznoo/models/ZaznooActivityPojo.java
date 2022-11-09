package com.example.zaznoo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@Entity(tableName = "zaznooActivity")
public class ZaznooActivityPojo {
//    @PrimaryKey(autoGenerate = true)
//    private int id;
    private Double distance;
    private String strDate;
    private String type;

    /**
     * No args constructor for use in serialization
     *
     */
    public ZaznooActivityPojo() {
    }

    public ZaznooActivityPojo(Double distance, String strDate, String type) {
        this.distance = distance;
        this.strDate = strDate;
        this.type = type;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
