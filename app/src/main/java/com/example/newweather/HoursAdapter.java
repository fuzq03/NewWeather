package com.example.newweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newweather.data.HOURS;

import java.util.ArrayList;
import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.HoursHolder> {

    List<HOURS> DataList = new ArrayList<>();

    public HoursAdapter(List<HOURS> mDataList) {
        DataList = mDataList;
    }

    @NonNull
    @Override
    public HoursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hours_item, parent, false);
        HoursHolder holder = new HoursHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HoursHolder holder, int position) {
        HOURS hours = DataList.get(position);
        holder.tem.setText(hours.tem + "℃");
        holder.wea.setText(hours.wea);
        holder.time.setText(hours.time);
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class HoursHolder extends RecyclerView.ViewHolder{

        TextView time;
        TextView wea;
        TextView tem;

        public HoursHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.hours_time);
            wea = itemView.findViewById(R.id.hours_wea);
            tem = itemView.findViewById(R.id.hours_tem);
        }
    }
}
