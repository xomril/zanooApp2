package com.example.zaznoo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zaznoo.MainViewModel;
import com.example.zaznoo.R;
import com.example.zaznoo.adaptors.ZaznooActivityAdapter;
import com.example.zaznoo.adaptors.ZaznooTableAdapter;
import com.example.zaznoo.models.ZaznooActivity;
import com.example.zaznoo.models.ZaznooActivityPojo;
import com.example.zaznoo.models.ZaznooTable;

import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PosInTable extends Fragment {


    private MainViewModel mViewModel;
    private static final String TAG = "TAG";
    private List<ZaznooTable> zaznooTableList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ZaznooTableAdapter adapter;
    private ImageView firstPlace;
    private ImageView SecondPlace;
    private ImageView thirdPlace;
    private TextView firstPlaceName;
    private TextView SecondPlaceName;
    private TextView thirdPlaceName;
    private TextView firstPlaceDistance;
    private TextView SecondPlaceDistance;
    private TextView thirdPlaceDistance;

    public PosInTable() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pos_in_table, container, false);
        findViewsById(v);
        return v;
    }

    private void top3SetValus() {
//        Uri imgUri=Uri.parse("http://pbs.twimg.com/profile_images/1463045958074347521/88y9HHVx_normal.jpg");
//        firstPlace.setImageURI(null);
//        firstPlace.setImageURI(imgUri);
//        firstPlace.setImageBitmap(null);
//        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://pbs.twimg.com/profile_images/1463045958074347521/88y9HHVx_normal.jpg").getContent());
//        firstPlace.setImageBitmap(bitmap);
//        Bitmap bitmap = BitmapFactory.decodeFile("http://pbs.twimg.com/profile_images/1463045958074347521/88y9HHVx_normal.jpg");
//        firstPlace.setImageBitmap(bitmap);
//        Uri imgUri=Uri.parse("http://pbs.twimg.com/profile_images/1463045958074347521/88y9HHVx_normal.jpg");
//        firstPlace.setImageURI(imgUri);
//        SecondPlace.setImageURI();
//        thirdPlace.setImageURI();

//        firstPlaceName.setText(zaznooTableList.get(0).getName());
//        SecondPlaceName.setText(zaznooTableList.get(1).getName());
//        thirdPlaceName.setText(zaznooTableList.get(2).getName());
//
//        firstPlaceDistance.setText(zaznooTableList.get(0).getDistance().toString());
//        SecondPlaceDistance.setText(zaznooTableList.get(1).getDistance().toString());
//        thirdPlaceDistance.setText(zaznooTableList.get(2).getDistance().toString());
    }



    private void findViewsById(View v) {
        firstPlace = v.findViewById(R.id.iv_first_pleace);
        SecondPlace = v.findViewById(R.id.iv_second_place);
        thirdPlace = v.findViewById(R.id.iv_third_place);

        firstPlaceName = v.findViewById(R.id.tv_first_place_name);
        SecondPlaceName = v.findViewById(R.id.tv_second_place_name);
        thirdPlaceName = v.findViewById(R.id.tv_third_place_name);

        firstPlaceDistance = v.findViewById(R.id.tv_first_place_distance);
        SecondPlaceDistance = v.findViewById(R.id.tv_second_place_distance);
        thirdPlaceDistance = v.findViewById(R.id.tv_third_place_distance);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getZaznooTablesFromLocalDB().observe(getViewLifecycleOwner(), zaznooTableRoom -> {
            for (int i = 0; i < zaznooTableRoom.size(); i++) {
                double distance = Double.parseDouble(String.format("%.2f", zaznooTableRoom.get(i).getDistance()/1000));
                ZaznooTable zaznooTableItem = new ZaznooTable(zaznooTableRoom.get(i).getName(),distance,zaznooTableRoom.get(i).getPlace(),zaznooTableRoom.get(i).isSelected());
                zaznooTableList.add(zaznooTableItem);
            }
            top3SetValus();
            adapter = new ZaznooTableAdapter(zaznooTableList);
            recyclerView = view.findViewById(R.id.tableRecyclerView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

    }
}