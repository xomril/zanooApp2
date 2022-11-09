package com.example.zaznoo.ui;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.zaznoo.MainViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zaznoo.R;


public class Statistics extends Fragment {
    private MainViewModel mViewModel;
    private static final int[] COLORS = {
//
//
            rgb("#14958c"), rgb("#1c7b37")//, rgb("#FF018786"), rgb("#FF03DAC5")
    };
    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;
    public Statistics() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.idBarChart);

        barEntriesArrayList = new ArrayList<>();
        mViewModel.getZaznooZaznooStatisticsResponseLocalDB().observe(getViewLifecycleOwner(), statistics -> {
            Log.d("roomTable", "1");
            if (statistics.size()>1) {
                barEntriesArrayList.add(new BarEntry(1f, (int) (statistics.get(statistics.size() - 1).getLastWeek())));
                barEntriesArrayList.add(new BarEntry(2f, (int) (statistics.get(statistics.size() - 1).getCurrent())));
                drawChart(barEntriesArrayList);
            }
        });
    }

    private void drawChart(ArrayList barEntriesArrayList) {
        Log.d("roomTable", barEntriesArrayList.toString());

        barDataSet = new BarDataSet(barEntriesArrayList, "שבוע נוכחי לעומת שבוע קודם");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.DKGRAY);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate();
    }

}