package com.aariyan.linxtimeandbilling.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aariyan.linxtimeandbilling.R;

public class TimingAdapter extends RecyclerView.Adapter<TimingAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_timing_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView startDate, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            startDate = itemView.findViewById(R.id.startDate);
            status = itemView.findViewById(R.id.status);
        }
    }
}
