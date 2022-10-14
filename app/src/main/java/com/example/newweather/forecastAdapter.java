package com.example.newweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newweather.data.ForecastDay;
import com.example.newweather.data.MONTH;

import java.util.List;

public class forecastAdapter extends RecyclerView.Adapter<forecastAdapter.MyHolder> {
    private List<MONTH> DataList;

    public forecastAdapter(List<MONTH> mDataList) {
        DataList = mDataList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MONTH month = DataList.get(position);
        holder.date.setText(month.date);
        holder.phrase.setText(month.forecastDay.phrase);
        holder.tem.setText(month.forecastDay.temperature + "℃");

    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView phrase;
        TextView tem;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.forecast_item_date);
            phrase = itemView.findViewById(R.id.forecast_item_phrase);
            tem = itemView.findViewById(R.id.forecast_item_tem);
        }
    }
}
