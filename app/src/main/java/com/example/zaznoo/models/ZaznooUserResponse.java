package com.example.zaznoo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class ZaznooUserResponse {
   private User user;

    public ZaznooUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ZaznooUserResponse{" +
                "user=" + user +
                '}';
    }
}
