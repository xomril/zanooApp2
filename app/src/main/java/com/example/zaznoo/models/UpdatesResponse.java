package com.example.zaznoo.models;

import java.util.List;

public class UpdatesResponse {
    private List<ZaznooUpdate> feed;

    public List<ZaznooUpdate> getFeed() {
        return feed;
    }

    public void setFeed(List<ZaznooUpdate> feeds) {
        this.feed = feeds;
    }
}
