package com.example.zaznoo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.zaznoo.listiners.UpdatesResponseListiner;
import com.example.zaznoo.listiners.ZaznooResponseListiner;
import com.example.zaznoo.listiners.ZaznooStatisticsResponseListiner;
import com.example.zaznoo.listiners.ZaznooTableResponseListiner;
import com.example.zaznoo.listiners.ZaznooUserResponseListiner;
import com.example.zaznoo.models.ActivityStatistics;
import com.example.zaznoo.models.User;
import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooStatisticsResponse;
import com.example.zaznoo.models.ZaznooTable;
import com.example.zaznoo.models.ZaznooUpdate;
import com.example.zaznoo.models.ZaznooUserResponse;
import com.example.zaznoo.repositories.ZaznooRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<ZaznooActivity>> zaznnoActivityLiveData;
    private MutableLiveData<List<ZaznooTable>> zaznnoTableLiveData;
    private MutableLiveData<ZaznooStatisticsResponse> zaznooStatisticsMutableLiveData;
    private MutableLiveData<List<ZaznooUpdate>> zaznooUpdatesResponseMutableLiveData;
    private MutableLiveData<User> zaznooUserMutableLiveData;


    public MutableLiveData<List<ZaznooActivity>> getZaznnoActivityLiveData() {
        return zaznnoActivityLiveData;
    }

    public MutableLiveData<List<ZaznooTable>> getZaznnoTableLiveData() {
        return zaznnoTableLiveData;
    }

    public MutableLiveData<ZaznooStatisticsResponse> getZaznooStatisticsMutableLiveData() {
        return zaznooStatisticsMutableLiveData;
    }
    public MutableLiveData<List<ZaznooUpdate>> getZaznooUpdatesResponseMutableLiveData() {
        return zaznooUpdatesResponseMutableLiveData;
    }

    public MutableLiveData<User> getZaznooUserMutableLiveData() {
        return zaznooUserMutableLiveData;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        }

    // TODO: Implement the ViewModel
    public LiveData<List<ZaznooActivity>> getZaznooActivitys() {
        Log.d("TAG1", "getZaznooActivitys");
        if (zaznnoActivityLiveData == null) {
            zaznnoActivityLiveData = new MutableLiveData<>();
        }

        ZaznooRepository.getInstance(getApplication()).getZaznooActivity(new ZaznooResponseListiner() {
             @Override
             public void onZaznnoActivitysArrived(List<ZaznooActivity> activities) {
                 Log.d("TAG", "view model: " + activities.size());
                 zaznnoActivityLiveData.postValue(activities);
             }
         });
        return zaznnoActivityLiveData;
    }
    public void DeleteZaznooActivitys() {
        if (zaznnoActivityLiveData == null) {
            zaznnoActivityLiveData = new MutableLiveData<>();
        }
        ZaznooRepository.getInstance(getApplication()).deleteAllZaznooActivitys();
    }
    public LiveData<List<ZaznooTable>> getZaznooTable() {
        Log.d("TAG", "LiveData getZaznooTable");
        if (zaznnoTableLiveData == null) {
            Log.d("TAG", " getZaznooTable is null");
            zaznnoTableLiveData = new MutableLiveData<>();
        }


        ZaznooRepository.getInstance(getApplication()).getZaznooTable(new ZaznooTableResponseListiner() {
            @Override
            public void onZaznnoTableArrived(List<ZaznooTable> zaznooTableList) {
                Log.d("TAG", "onZaznnoTableArrived: " + zaznooTableList.size());
                zaznnoTableLiveData.postValue(zaznooTableList);
                Log.d("TAG", "zaznnoTableLiveData: " + zaznnoTableLiveData.toString());
            }
        });
        return zaznnoTableLiveData;
    }

    public LiveData<ZaznooStatisticsResponse> getZaznooStatistics() {
        if (zaznooStatisticsMutableLiveData == null) {
            zaznooStatisticsMutableLiveData = new MutableLiveData<>();
        }

        ZaznooRepository.getInstance(getApplication()).getZaznooStatistics(new ZaznooStatisticsResponseListiner(){
            @Override
            public void onZaznnoStatisticsArrived(ZaznooStatisticsResponse zaznooStatisticsList) {
                Log.d("TAG", "onZaznnoStatisticsArrived: ");
                zaznooStatisticsMutableLiveData.postValue(zaznooStatisticsList);
            }
        });
        return zaznooStatisticsMutableLiveData;
    }

    public LiveData<List<ZaznooUpdate>> getZaznooUpdates() {
        if (zaznooUpdatesResponseMutableLiveData == null) {
            zaznooUpdatesResponseMutableLiveData = new MutableLiveData<>();
        }
        ZaznooRepository.getInstance(getApplication()).getZaznooUpdates(new UpdatesResponseListiner() {
            @Override
            public void onUpdatesArrived(List<ZaznooUpdate> zaznooUpdates) {
                zaznooUpdatesResponseMutableLiveData.postValue( zaznooUpdates);
            }
        });
        return zaznooUpdatesResponseMutableLiveData;
    }


    public LiveData<User> getZaznooUser(String userName) {
        if (zaznooUserMutableLiveData == null) {
            zaznooUserMutableLiveData = new MutableLiveData<>();
        }

        ZaznooRepository.getInstance(getApplication()).getZaznooUser(userName, new ZaznooUserResponseListiner() {
            @Override
            public void onZaznnoUserArrived(User zaznooUser) {
                Log.d("Tomtom", "onZaznnoUserArrived: "+zaznooUser.getUser());
                zaznooUserMutableLiveData.postValue(zaznooUser);
            }
        });
        return zaznooUserMutableLiveData;
    }
//////////////////////////////////////////////////////////


    public LiveData<List<ZaznooActivity>> getZaznooActivitysFromLocalDB(){
        return ZaznooRepository.getInstance(getApplication()).getZaznooActivityFromLocalDB();
    }

    public void upsertZaznooActivity(ZaznooActivity...zaznooActivities){
        Log.d("TAG", "upsertZaznooActivity: ");
        ZaznooRepository.getInstance(getApplication()).upsert(zaznooActivities);
    }

    public LiveData<ZaznooActivity> getZaznooActivity(int id){
        return ZaznooRepository.getInstance(getApplication()).getZaznooActivityById(id);
    }
    //////////////////////////////////////////////////////////


    public LiveData<List<ZaznooTable>> getZaznooTablesFromLocalDB(){
        return ZaznooRepository.getInstance(getApplication()).getZaznooTableFromLocalDB();
    }

    public void upsertZaznooTable(ZaznooTable...zaznooTables){
        ZaznooRepository.getInstance(getApplication()).upsert(zaznooTables);
    }

    public LiveData<List<ZaznooTable>> getZaznooTable(int id){
        return ZaznooRepository.getInstance(getApplication()).getZaznooTableFromLocalDB();
    }
    //////////////////////////////////////////////////////////


    public LiveData<List<ActivityStatistics>> getZaznooZaznooStatisticsResponseLocalDB(){
        return ZaznooRepository.getInstance(getApplication()).getZaznooStatisticsFromLocalDB();
    }

    public void upsertActivityStatistics(ActivityStatistics...zaznooStatisticsResponses){
        ZaznooRepository.getInstance(getApplication()).upsert(zaznooStatisticsResponses);
    }

    public LiveData<ActivityStatistics> getZaznooStatisticsResponse(int id){
        return ZaznooRepository.getInstance(getApplication()).getActivityStatisticsById(id);
    }

    public void DeleteTable() {
        ZaznooRepository.getInstance(getApplication()).deleteTable();
    }

    public void upsertUpdates(ZaznooUpdate...zaznooUpdate) {
        ZaznooRepository.getInstance(getApplication()).upsertUpdates(zaznooUpdate);
    }

    public LiveData<List<ZaznooUpdate>> getZaznooUpdatesFromLocalDB() {
        return ZaznooRepository.getInstance(getApplication()).getZaznooUpdatesFromLocalDB();
    }
    //
    //////////////////////////////////////////////////////////


    public LiveData<List<User>> getZaznooUserResponseLocalDB(){
        return ZaznooRepository.getInstance(getApplication()).getZaznooUserFromLocalDB();
    }

    public void upsertUser(User...user){
        ZaznooRepository.getInstance(getApplication()).upsertZaznooUser(user);
    }


    public void DeleteUsers() {
        ZaznooRepository.getInstance(getApplication()).deleteUsers();
    }
}