package com.example.zaznoo.listiners;

import com.example.zaznoo.models.UpdatesResponse;
import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooUpdate;

import java.util.List;

public interface UpdatesResponseListiner {
    void onUpdatesArrived(List<ZaznooUpdate> zaznooUpdates);
}
