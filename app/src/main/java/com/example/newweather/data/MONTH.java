package com.example.newweather.data;

import com.google.gson.annotations.SerializedName;

public class MONTH {
    @SerializedName("dateOfWeek")
    public String date;

    @SerializedName("day")
    public ForecastDay forecastDay;
}
