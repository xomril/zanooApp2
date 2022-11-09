package com.example.zaznoo.ui;

import android.content.Context;
import android.icu.util.LocaleData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zaznoo.MainViewModel;
import com.example.zaznoo.R;
import com.example.zaznoo.adaptors.ZaznooActivityAdapter;
import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooActivityPojo;
import com.example.zaznoo.repositories.ZaznooRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeeklyActivity extends Fragment {

    private MainViewModel mViewModel;
    private static final String TAG = "TAG";
    private List<ZaznooActivityPojo> zaznooActivitys = new ArrayList<>();
    private RecyclerView recyclerView;
    private ZaznooActivityAdapter adapter;
    private ZaznooRepository repository;
    private ImageView iv_profile;
    private TextView tv_user_name;
    public WeeklyActivity() {
    }

    public static WeeklyActivity newInstance() {
        return new WeeklyActivity();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        repository = ZaznooRepository.getInstance(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weekly_activity, container, false);
        findViewsById(v);
        loadImage(v);
        return v;
    }


    public  void loadImage(View view){
        String url = "https://pbs.twimg.com/profile_images/1463045958074347521/88y9HHVx.jpg";
        Glide.with(requireContext())
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.first)
                .into(iv_profile);
    }
    private void findViewsById(View v) {
        iv_profile = v.findViewById(R.id.iv_profile);
        tv_user_name = v.findViewById(R.id.tv_user_name);
        iv_profile = v.findViewById(R.id.iv_profile);
        tv_user_name = v.findViewById(R.id.tv_user_name);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getZaznooActivitysFromLocalDB().observe(getViewLifecycleOwner(), zaznooActivitysRoom -> {
            Log.d(TAG, "Tomer: "+ zaznooActivitysRoom.size());
            for (int i = 0; i < zaznooActivitysRoom.size(); i++) {
                String activityType = transType(zaznooActivitysRoom.get(i).getType());
                DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;
                Instant instant = Instant.from(dtf.parse(zaznooActivitysRoom.get(i).getStrDate()));
                LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                String strDate = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear() + "\n      " + date.getHour() + ":" + date.getMinute();
                double distance = Double.parseDouble(String.format("%.2f", zaznooActivitysRoom.get(i).getDistance() / 1000));
                ZaznooActivityPojo zaznooActivityPojo = new ZaznooActivityPojo(distance, strDate, activityType);
                zaznooActivitys.add(zaznooActivityPojo);
            }
            adapter = new ZaznooActivityAdapter(zaznooActivitys);
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

    }

    private String transType(String type) {
        switch (type) {
            case "Run":
                return "ריצה";
            case "Ride":
                return "רכיבה";
            case "Walk":
                return "הליכה";
            default:
                return type;
        }
    }
}