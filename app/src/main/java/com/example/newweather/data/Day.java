package com.example.newweather.data;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("temperature")
    public String temperature;

    @SerializedName("phrase")
    public String phrase;

    @SerializedName("feelsLike")
    public String feelsLike;

    @SerializedName("humidity")
    public String humidity;

    @SerializedName("visibility")
    public String visibility;

    @SerializedName("altimeter")
    public String altimeter;

    @SerializedName("uvIndex")
    public String uvIndex;

    @SerializedName("windDirCompass")
    public String windDirCompass;

    @SerializedName("aqi")
    public AQI aqi;
}
