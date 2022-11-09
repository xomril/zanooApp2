package com.example.zaznoo.listiners;

import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooTable;

import java.util.List;

public interface ZaznooResponseListiner {
    void onZaznnoActivitysArrived(List<ZaznooActivity> zaznooActivitys);

}
