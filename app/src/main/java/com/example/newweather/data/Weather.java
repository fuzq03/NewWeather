package com.example.newweather.data;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    @SerializedName("updateTimeFormat")
    public String updateTime;

    @SerializedName("city")
    public String CityName;

    @SerializedName("day")
    public Day day;

    @SerializedName("hours")
    public List<HOURS> hoursList;

    @SerializedName("month")
    public List<MONTH> monthList;

}
