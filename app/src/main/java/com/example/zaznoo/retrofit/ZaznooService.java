package com.example.zaznoo.retrofit;




import com.example.zaznoo.models.UpdatesResponse;
import com.example.zaznoo.models.ZaznooResponse;
import com.example.zaznoo.models.ZaznooStatisticsResponse;
import com.example.zaznoo.models.ZaznooTableResponse;
import com.example.zaznoo.models.ZaznooUserResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZaznooService {
    static ZaznooService create(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://script.google.com/macros/s/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ZaznooService.class);
    }


    @GET("AKfycbzaeK-fzj4xSL6-Vd5D0mcnjqWgvEdZt5sOxhSGv4AitCyo7hVlI5MM4VYR2hXgL3i4/exec")
    Call<ZaznooResponse>getZaznooActivitys(@Query("table") String table, @Query("user") String user);

    @GET("AKfycbz8pCiuY6NyI7e1MqgZJtD0lauePjNf6_PW-gf0iWgD7Tjn8yP_TTtoCx-U9GhwJ8Ch/exec")
    Call<ZaznooTableResponse> getZaznooTable(@Query("table") String table, @Query("user") String user, @Query("requestType") String requestType);

    @GET("AKfycby7sJ_qhseHW3zQg-HoJoPR1YwZlO40a3UfThI5Dl8Of7_S7F0E2OXtvK914jHy1-0T/exec")
    Call<ZaznooStatisticsResponse> getZaznooStatistics(@Query("user") String user, @Query("requestType") String requestType);

    @GET("AKfycbzAdU6XtQcNWRieYEo34b1ICE2KIAVhcxqwO9hyEpunNnhm8Dr4EoujaM-mZ56xst76/exec")
    Call<UpdatesResponse> getZaznooUpdats(@Query("requestType")String getFeed);

    @GET("AKfycby4XhQAancubmX2jybpVQLsgylI59z3-cm1pgnK7N9K8esqTqFn4lsDlXVLIsTlLJbY/exec")
    Call<ZaznooUserResponse> getZaznooUser(@Query("requestType")String getUserPic, @Query("user") String user);
}
