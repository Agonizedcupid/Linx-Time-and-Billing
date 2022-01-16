package com.aariyan.linxtimeandbilling.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aariyan.linxtimeandbilling.Model.TimingModel;
import com.aariyan.linxtimeandbilling.R;

import java.util.List;

public class TimingAdapter extends RecyclerView.Adapter<TimingAdapter.ViewHolder> {

    private Context context;
    private List<TimingModel> list;

    public TimingAdapter(Context context,List<TimingModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.single_timing_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimingModel model = list.get(position);
        holder.status.setText(model.getStatus());
        holder.startDate.setText(model.getStartDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
