package com.example.zaznoo.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zaznoo.MainViewModel;
import com.example.zaznoo.R;
import com.example.zaznoo.models.ActivityStatistics;
import com.example.zaznoo.models.User;
import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooTable;
import com.example.zaznoo.models.ZaznooUpdate;
import com.example.zaznoo.models.ZaznooUserResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class SplashFragment extends Fragment {
    private MainViewModel mViewModel;
    private Handler h = new Handler();
    final String TAG ="splash";
    private SharedPreferences sp;
    public SplashFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        sp = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.DeleteZaznooActivitys();
        mViewModel.DeleteUsers();
        mViewModel.DeleteTable();

        insertUserToRoom(sp.getString("user",""));
        insertTableToRoom();
        insertStatisticsToRoom();
        insertUpdatesToRoom();
        insertActivitiesToRoom(view);
    }

    private void insertUpdatesToRoom() {
        Log.d(TAG, "insertUpdatesToRoom: ");
        mViewModel.getZaznooUpdates().observe(getViewLifecycleOwner(), zaznooUpdates -> {
            for (int i = 0; i < zaznooUpdates.size(); i++) {
                Log.d("Update", "insertpdatesToRoom: "+zaznooUpdates.get(i).getUpdate().toString());
                mViewModel.upsertUpdates(new ZaznooUpdate(zaznooUpdates.get(i).getUpdate()));
            }
        });
    }

    private void insertActivitiesToRoom(View view){
        Log.d(TAG, "insertActivitiesToRoom: ");
        mViewModel.getZaznooActivitys().observe(getViewLifecycleOwner(), zaznooActivities -> {
            for (int i = 0; i < zaznooActivities.size(); i++) {
                double distance = Double.parseDouble(String.format("%.2f", zaznooActivities.get(i).getDistance()));
                mViewModel.upsertZaznooActivity(new ZaznooActivity(distance, zaznooActivities.get(i).getStrDate(), zaznooActivities.get(i).getType()));
            }
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_mainFragment);
        });
    }

    private void insertTableToRoom(){
        Log.d(TAG, "insertTableToRoom: ");
        mViewModel.getZaznooTable().observe(getViewLifecycleOwner(), zaznooTable -> {
            ArrayList<String> Top3 = new ArrayList<>();
            for (int i = 0; i < zaznooTable.size(); i++) {
                double distance = Double.parseDouble(String.format("%.2f", zaznooTable.get(i).getDistance()));
                ZaznooTable zaznooTableItem = new ZaznooTable(zaznooTable.get(i).getName(),distance,zaznooTable.get(i).getPlace(),zaznooTable.get(i).isSelected());
                mViewModel.upsertZaznooTable(new ZaznooTable(zaznooTableItem.getName(),zaznooTableItem.getDistance(),zaznooTableItem.getPlace(),zaznooTableItem.isSelected()));
                if(i<3){
                    Top3.add(zaznooTableItem.getName());
                }
            }
            for (int i = 0; i < Top3.size(); i++) {
                insertUserToRoom(Top3.get(i));
            }
        });
    }

    private void insertStatisticsToRoom() {
        Log.d(TAG, "insertStatisticsToRoom: ");
        mViewModel.getZaznooStatistics().observe(getViewLifecycleOwner(), zaznooStatistics -> {
            double current = Double.parseDouble(String.format("%.2f", zaznooStatistics.getRuns().getCurrent()/1000));
            double lastWeek = Double.parseDouble(String.format("%.2f", zaznooStatistics.getRuns().getLastWeek()/1000));
            double total = Double.parseDouble(String.format("%.2f", zaznooStatistics.getRuns().getTotal()/1000));
            Log.d("TAG", "insertStatisticsToRoom: "+current+lastWeek+total);
            mViewModel.upsertActivityStatistics(new ActivityStatistics(current,lastWeek,total));

        });
    }

    private void insertUserToRoom(String user) {
        Log.d(TAG, "insertUserToRoom: "+user);
        mViewModel.getZaznooUser(user).observe(getViewLifecycleOwner(), zaznooUser -> {
            Log.d(TAG, "TomtominsertUserToRoom: "+zaznooUser.getUser()+zaznooUser.getName()+zaznooUser.getImage());
            mViewModel.upsertUser(new User(zaznooUser.getUser(),zaznooUser.getName(),zaznooUser.getImage()));
        });
    }
}