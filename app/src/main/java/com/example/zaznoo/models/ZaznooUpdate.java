package com.example.zaznoo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ZaznooUpdate")
public class ZaznooUpdate {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String update;

    public ZaznooUpdate(String update) {
        this.update = update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

}
