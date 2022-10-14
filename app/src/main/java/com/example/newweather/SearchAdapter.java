package com.example.newweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newweather.data.City;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    List<City> DataList = new ArrayList<>();

    public SearchAdapter(List<City> mDataList) {
        DataList = mDataList;
    }
    public void setList(List<City> mCityList) {
        DataList = mCityList;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        SearchHolder holder = new SearchHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        City city = DataList.get(position);
        String cityName = city.getAdm2() + " " + city.getAdm1() + " " + city.getCountyName();
        holder.textView.setText(cityName);

    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.search_image);
            textView = itemView.findViewById(R.id.searchCountyText);
        }
    }
}
