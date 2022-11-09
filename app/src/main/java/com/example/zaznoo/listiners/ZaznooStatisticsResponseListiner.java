package com.example.zaznoo.listiners;

import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooStatisticsResponse;
import com.example.zaznoo.models.ZaznooTable;

import java.util.List;

import retrofit2.Response;

public interface ZaznooStatisticsResponseListiner {

    void onZaznnoStatisticsArrived(ZaznooStatisticsResponse zaznooStatisticsList);
}
