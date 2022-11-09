package com.example.zaznoo.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaznoo.R;
import com.example.zaznoo.models.ZaznooActivityPojo;

import java.util.List;

public class ZaznooActivityAdapter extends RecyclerView.Adapter<ZaznooActivityAdapter.ZaznooActivityViewHolder> {

    private List<ZaznooActivityPojo> zaznooActivitys;
    public ZaznooActivityAdapter(List<ZaznooActivityPojo> zaznooActivitys) {
        this.zaznooActivitys = zaznooActivitys;
    }

    @NonNull
    @Override
    public ZaznooActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zaznoo_activity_row, parent, false);

        return new ZaznooActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ZaznooActivityViewHolder holder, int position) {
        ZaznooActivityPojo zaznooActivityPojo = zaznooActivitys.get(position);
        holder.distance.setText(zaznooActivityPojo.getDistance().toString()+" km");
        holder.type.setText(zaznooActivityPojo.getType().toString());
        holder.strDate.setText(zaznooActivityPojo.getStrDate().toString());
    }

    @Override
    public int getItemCount() {
        return zaznooActivitys != null ? zaznooActivitys.size(): 0;
    }

    public static class ZaznooActivityViewHolder extends RecyclerView.ViewHolder{
        private TextView type;
        private TextView distance;
        private TextView strDate;


        public ZaznooActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            this.type = itemView.findViewById(R.id.tv_place);
            this.distance = itemView.findViewById(R.id.tv_distance_table);
            this.strDate = itemView.findViewById(R.id.tv_name);

        }

    }
}
