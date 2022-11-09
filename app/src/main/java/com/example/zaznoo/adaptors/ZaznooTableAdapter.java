package com.example.zaznoo.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaznoo.R;
import com.example.zaznoo.models.ZaznooTable;

import java.util.List;

public class ZaznooTableAdapter extends RecyclerView.Adapter<ZaznooTableAdapter.ZaznooTableViewHolder> {

    List<ZaznooTable> zaznooTableList;
    public ZaznooTableAdapter(List<ZaznooTable> zaznooTableList) {
        this.zaznooTableList = zaznooTableList;
    }


    @NonNull
    @Override
    public ZaznooTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_row, parent, false);
        return new ZaznooTableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ZaznooTableViewHolder holder, int position) {
        ZaznooTable zaznooTable = zaznooTableList.get(position);
        holder.distance.setText(zaznooTable.getDistance().toString()+" km");
        holder.name.setText(zaznooTable.getName());
        holder.place.setText(String.valueOf(zaznooTable.getPlace()));
    }

    @Override
        public int getItemCount() {
        return zaznooTableList != null ? zaznooTableList.size(): 0;
    }

        public static class ZaznooTableViewHolder extends RecyclerView.ViewHolder{
        TextView place;
        TextView name;
        TextView distance;
            public ZaznooTableViewHolder(@NonNull View itemView) {
                super(itemView);
                place = itemView.findViewById(R.id.tv_place);
                name = itemView.findViewById(R.id.tv_name);
                distance = itemView.findViewById(R.id.tv_distance_table);

            }
        }
}

