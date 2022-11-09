package com.example.zaznoo.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.zaznoo.listiners.UpdatesResponseListiner;
import com.example.zaznoo.listiners.ZaznooResponseListiner;
import com.example.zaznoo.database.AppDatabase;
import com.example.zaznoo.listiners.ZaznooStatisticsResponseListiner;
import com.example.zaznoo.listiners.ZaznooTableResponseListiner;
import com.example.zaznoo.listiners.ZaznooUserResponseListiner;
import com.example.zaznoo.models.ActivityStatistics;
import com.example.zaznoo.models.UpdatesResponse;
import com.example.zaznoo.models.User;
import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooResponse;
import com.example.zaznoo.models.ZaznooStatisticsResponse;
import com.example.zaznoo.models.ZaznooTable;
import com.example.zaznoo.models.ZaznooTableResponse;
import com.example.zaznoo.models.ZaznooUpdate;
import com.example.zaznoo.models.ZaznooUserResponse;
import com.example.zaznoo.retrofit.ZaznooService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZaznooRepository {

    private static ZaznooRepository instance;
    private AppDatabase db;
    private SharedPreferences sp;
    private ZaznooService retrofit;

    private ZaznooRepository(Context context) {
        retrofit = ZaznooService.create();
        db = AppDatabase.create(context);
        sp = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
    }

    public synchronized static ZaznooRepository getInstance(Context context){
        if(instance == null) {
            instance = new ZaznooRepository(context);
        }
        return instance;
    }

    public void getZaznooActivity (ZaznooResponseListiner listiner){
//        Call<ZaznooResponse> call = retrofit.getZaznooActivitys("app_run","CactusBavarius");
        Call<ZaznooResponse> call = retrofit.getZaznooActivitys("app_run",sp.getString("user",""));

        call.enqueue(new Callback<ZaznooResponse>() {

            @Override
            public void onResponse(Call<ZaznooResponse> call, Response<ZaznooResponse> response) {
                Log.d("-----------", "onResponse: "+ response);
                if(!response.isSuccessful()){

                    return;
                }

               Log.d("TAG", "onResponse: " + response.body().getActivities());
                listiner.onZaznnoActivitysArrived(response.body().getActivities());
            }
            @Override
            public void onFailure(Call<ZaznooResponse> call, Throwable t) {
                Log.d("-----------", "error: "+ t.toString());

            }
        });
    }
/////////////////////////////////////////////////////////////////////
    public void getZaznooTable (ZaznooTableResponseListiner listiner){
    Log.d("TAG", "getZaznooTable (CactusBavarius)");

//    Call<ZaznooTableResponse> call = retrofit.getZaznooTable("Run","CactusBavarius","sum");
    Call<ZaznooTableResponse> call = retrofit.getZaznooTable("Run",sp.getString("user",""),"sum");
    call.enqueue(new Callback<ZaznooTableResponse>() {

        @Override
        public void onResponse(Call<ZaznooTableResponse> call, Response<ZaznooTableResponse> response) {
            Log.d("-----------", "onResponse: "+ response);
            if(!response.isSuccessful()){
                Log.d("TAG", "onResponse error");
                return;
            }

            Log.d("TAG", "onResponse: " + response.body().getZaznooTableList());
            listiner.onZaznnoTableArrived(response.body().getZaznooTableList());
        }
        @Override
        public void onFailure(Call<ZaznooTableResponse> call, Throwable t) {
            Log.d("-----------", "error: "+ t.toString());

        }
    });
}
    ///////////////////////////////////////////////////////////////////
    public void getZaznooStatistics (ZaznooStatisticsResponseListiner listiner){
//        Call<ZaznooStatisticsResponse> call = retrofit.getZaznooStatistics ("CactusBavarius", "progress");
        Call<ZaznooStatisticsResponse> call = retrofit.getZaznooStatistics (sp.getString("user",""), "progress");

        call.enqueue(new Callback<ZaznooStatisticsResponse>() {

            @Override
            public void onResponse(Call<ZaznooStatisticsResponse> call, Response<ZaznooStatisticsResponse> response) {
                Log.d("-----------", "onResponse: "+ response);
                if(!response.isSuccessful()){
                    Log.d("-----------", "onResponse: "+ response);
                    return;
                }

                Log.d("GETRUN", "onResponse: " + response.body().getRuns());
                listiner.onZaznnoStatisticsArrived( response.body());
            }
            @Override
            public void onFailure(Call<ZaznooStatisticsResponse> call, Throwable t) {
                Log.d("-----------", "error: "+ t.toString());

            }
        });
    }
//
    public void getZaznooUpdates (UpdatesResponseListiner listiner){
    Log.d("TAG", "getZaznooUpdates: ");
    Call<UpdatesResponse> call = retrofit.getZaznooUpdats("getFeed");
    Log.d("TAG", "getZaznooUpdates: "+call.toString());
    call.enqueue(new Callback<UpdatesResponse>() {

        @Override
        public void onResponse(Call<UpdatesResponse> call, Response<UpdatesResponse> response) {
            Log.d("getZaznooUpdatesOmri", "onResponse: "+ response.body().getFeed().get(0).getUpdate().toString());
            if(!response.isSuccessful()){

                return;
            }

            Log.d("TAG", "onResponse: " + response.body().getFeed());
            listiner.onUpdatesArrived(response.body().getFeed());
        }
        @Override
        public void onFailure(Call<UpdatesResponse> call, Throwable t) {
            Log.d("getZaznooUpdates", "error: "+ t.toString());

        }
    });
}
    ///////////////////////////////////////////////////////////////////

    public void getZaznooUser (String userName, ZaznooUserResponseListiner listiner){
        Log.d("UserLog", "getZaznooUser: "+userName);
//        Call<ZaznooUserResponse> call = retrofit.getZaznooUser ("getUserPic","xomri");
        Call<ZaznooUserResponse> call = retrofit.getZaznooUser ("getUserPic",userName);
        call.enqueue(new Callback<ZaznooUserResponse>() {

            @Override
            public void onResponse(Call<ZaznooUserResponse> call, Response<ZaznooUserResponse> response) {
                Log.d("getZaznooUser", "onResponse: "+ response);
                if(!response.isSuccessful()){
                    Log.d("getZaznooUser", "onResponse: "+ response);
                    return;
                }

                Log.d("onResponsegetZaznooUser", "onResponse: " + response.body());
                listiner.onZaznnoUserArrived( response.body().getUser());
            }
            @Override
            public void onFailure(Call<ZaznooUserResponse> call, Throwable t) {
                Log.d("getZaznooUser", "error: "+ t.toString());

            }
        });
    }
    ///////////////////////////////////////////

    public LiveData<List<ZaznooActivity>> getZaznooActivityFromLocalDB(){

        return db.zaznooActivityDao().getZaznooActivitys();
    }
    public void upsert(ZaznooActivity[] zaznooActivities) {
        db.zaznooActivityDao().upsertZaznooActivity(zaznooActivities);
    }
    public void deleteAllZaznooActivitys() {
        db.zaznooActivityDao().deleteAllZaznooActivitys();
    }

    public LiveData<ZaznooActivity> getZaznooActivityById(int id) {
        return  db.zaznooActivityDao().getZaznooActivityById(id);
    }

    ///////////////////////////////////////////

    public LiveData<List<ActivityStatistics>> getZaznooStatisticsFromLocalDB(){

        return db.activityStatisticsDao().getActivitysStatistics();
    }
    public void upsert(ActivityStatistics[] activityStatistics) {
        db.activityStatisticsDao().upsertActivityStatistics(activityStatistics);
    }

    public LiveData<ActivityStatistics> getActivityStatisticsById(int id) {
        return  db.activityStatisticsDao().getActivityStatisticsById(id);
    }

    ///////////////////////////////////////////

    public LiveData<List<ZaznooTable>> getZaznooTableFromLocalDB(){

        return db.zaznooTableDao().getZaznooTable();
    }
    public void upsert(ZaznooTable[] zaznooTable) {
        db.zaznooTableDao().upsertZaznooTable(zaznooTable);
    }

    public LiveData<ZaznooTable> getZaznooTableById(int id) {

        return  db.zaznooTableDao().getZaznooTableById(id);
    }

    public void deleteTable() {
        db.zaznooTableDao().deletezaznooTable();
    }

    public void upsertUpdates(ZaznooUpdate[] zaznooUpdate) {
        db.zaznooUpdateDao().upsertUpdates(zaznooUpdate);
    }

    public LiveData<List<ZaznooUpdate>> getZaznooUpdatesFromLocalDB() {
        return db.zaznooUpdateDao().getZaznooUpdates();
    }

    public LiveData<List<User>> getZaznooUserFromLocalDB() {
        return db.zaznooUserDao().getZaznooUsers();
    }

    public void upsertZaznooUser(User[] users) {
        db.zaznooUserDao().upsertUser(users);
    }

    public void deleteUsers() {
        db.zaznooUserDao().getZaznooUsers();
    }
}
