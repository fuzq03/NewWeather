package com.example.newweather.data;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {
    @SerializedName("phrase")
    public String phrase;

    @SerializedName("temperature")
    public String temperature;
}
