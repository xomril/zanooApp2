package com.example.zaznoo.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zaznoo.MainViewModel;
import com.example.zaznoo.R;
import com.example.zaznoo.adaptors.ZaznooUpdateAdapter;
import com.example.zaznoo.models.ZaznooUpdate;
import com.example.zaznoo.repositories.ZaznooRepository;

import java.util.ArrayList;
import java.util.List;


public class UpdatesFragment extends Fragment {

    private MainViewModel mViewModel;
    private static final String TAG = "TAG";
    private List<ZaznooUpdate> zaznooActivitys = new ArrayList<>();
    private RecyclerView recyclerView;
    private ZaznooUpdateAdapter adapter;
    public UpdatesFragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_updates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getZaznooUpdatesFromLocalDB().observe(getViewLifecycleOwner(), zaznooUpdatsRoom -> {
            adapter = new ZaznooUpdateAdapter(zaznooUpdatsRoom);
            recyclerView = view.findViewById(R.id.rv_updates);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

    }
}