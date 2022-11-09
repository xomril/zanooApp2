package com.example.zaznoo.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaznoo.R;
import com.example.zaznoo.models.ZaznooUpdate;

import java.util.List;

public class ZaznooUpdateAdapter extends RecyclerView.Adapter<ZaznooUpdateAdapter.ZaznooUpdateViewHolder> {

    private List<ZaznooUpdate> zaznooUpdates;
    public ZaznooUpdateAdapter(List<ZaznooUpdate> zaznooUpdats) {
        this.zaznooUpdates = zaznooUpdats;
    }
    @NonNull
    @Override
    public ZaznooUpdateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_updates_row, parent, false);
        return new ZaznooUpdateAdapter.ZaznooUpdateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ZaznooUpdateViewHolder holder, int position) {
        ZaznooUpdate zaznooUpdate = zaznooUpdates.get(position);
        holder.update.setText(zaznooUpdate.getUpdate());

    }

    @Override
    public int getItemCount() {return zaznooUpdates != null ? zaznooUpdates.size(): 0;
    }

    public static class ZaznooUpdateViewHolder extends RecyclerView.ViewHolder{
        private TextView update;
        public ZaznooUpdateViewHolder(@NonNull View itemView) {
            super(itemView);
            this.update = itemView.findViewById(R.id.tv_update_text);
        }
    }
}
