package com.example.zaznoo.ui;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zaznoo.MainViewModel;
import com.example.zaznoo.R;

public class LoginFragment extends Fragment {

    private MainViewModel mViewModel;
    private Button loginButton;
    private Button registerButton;
    private ImageView logo;
    private ImageView zaznoo_text;
    private ImageView left_btn;
    private ImageView right_btn;
    private Handler h = new Handler();
    private SharedPreferences sp;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        findViews(view);
//        loginButton = view.findViewById(R.id.login_btn);
//        registerButton = view.findViewById(R.id.registration_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateViews();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sp = requireContext().getSharedPreferences("SharedPref", Context.MODE_PRIVATE);
                        String user = sp.getString("user",""); //TODO get user from twitter
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("user","xomri");
                        editor.commit();
                        Log.d("USERUSER", "run: " + sp.getString("user",""));
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_splashFragment);
                    }
                },2500);
//                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_splashFragment);
//                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_twitterFragment);
//                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment);
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
            }
        });

        return view;
    }

    private void findViews(View view) {
        loginButton = view.findViewById(R.id.login_btn);
        registerButton = view.findViewById(R.id.registration_btn);
        logo = view.findViewById(R.id.iv_logo);
        zaznoo_text = view.findViewById(R.id.iv_zaznoo_text);
        left_btn = view.findViewById(R.id.iv_left_button);
        right_btn = view.findViewById(R.id.iv_right_button);
    }

    public void  animateViews(){

        logo.animate().translationY(-1600).setDuration(1000).setStartDelay(500);
        zaznoo_text.animate().translationY(-1600).setDuration(1000).setStartDelay(1000);
        loginButton.animate().translationX(700).setDuration(1000).setStartDelay(1500);
        registerButton.animate().translationX(-700).setDuration(1000).setStartDelay(1500);
        left_btn.animate().translationY(1400).setDuration(1000).setStartDelay(2000);

        right_btn.animate().translationY(1400).setDuration(1000).setStartDelay(2000);
    }
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mViewModel.DeleteZaznooActivitys();
//        mViewModel.DeleteTable();
//        insertActivitiesToRoom();
//        insertTableToRoom();
//        insertStatisticsToRoom();
//        insertUpdatesToRoom();
//    }
//
//    private void insertUpdatesToRoom() {
//        mViewModel.getZaznooUpdates().observe(getViewLifecycleOwner(), zaznooUpdates -> {
//            for (int i = 0; i < zaznooUpdates.size(); i++) {
//                Log.d("Update", "insertpdatesToRoom: "+zaznooUpdates.get(i).getUpdate().toString());
//                mViewModel.upsertUpdates(new ZaznooUpdate(zaznooUpdates.get(i).getUpdate()));
//            }
//        });
//    }
//
//    private void insertActivitiesToRoom(){
//        mViewModel.getZaznooActivitys().observe(getViewLifecycleOwner(), zaznooActivities -> {
//            for (int i = 0; i < zaznooActivities.size(); i++) {
//                double distance = Double.parseDouble(String.format("%.2f", zaznooActivities.get(i).getDistance()));
//                mViewModel.upsertZaznooActivity(new ZaznooActivity(distance, zaznooActivities.get(i).getStrDate(), zaznooActivities.get(i).getType()));
//            }
//        });
//    }
//
//    private void insertTableToRoom(){
//        mViewModel.getZaznooTable().observe(getViewLifecycleOwner(), zaznooTable -> {
//            for (int i = 0; i < zaznooTable.size(); i++) {
//                double distance = Double.parseDouble(String.format("%.2f", zaznooTable.get(i).getDistance()));
//                ZaznooTable zaznooTableItem = new ZaznooTable(zaznooTable.get(i).getName(),distance,zaznooTable.get(i).getPlace(),zaznooTable.get(i).isSelected());
//                mViewModel.upsertZaznooTable(new ZaznooTable(zaznooTableItem.getName(),zaznooTableItem.getDistance(),zaznooTableItem.getPlace(),zaznooTableItem.isSelected()));
//            }
//        });
//    }
//
//    private void insertStatisticsToRoom() {
//        mViewModel.getZaznooStatistics().observe(getViewLifecycleOwner(), zaznooStatistics -> {
//            double current = Double.parseDouble(String.format("%.2f", zaznooStatistics.getRuns().getCurrent()/1000));
//            double lastWeek = Double.parseDouble(String.format("%.2f", zaznooStatistics.getRuns().getLastWeek()/1000));
//            double total = Double.parseDouble(String.format("%.2f", zaznooStatistics.getRuns().getTotal()/1000));
//            mViewModel.upsertActivityStatistics(new ActivityStatistics(current,lastWeek,total));
//
//        });
//    }


}