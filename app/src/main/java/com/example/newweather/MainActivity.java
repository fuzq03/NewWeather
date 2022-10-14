package com.example.newweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newweather.data.City;
import com.example.newweather.data.Weather;
import com.example.newweather.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView CityName;
    private TextView NowTemText;
    private TextView NowPhraseText;
    private TextView NowTimeText;
    private TextView aqiTipsText;
    private TextView feelsLikeText;
    private TextView humidityText;
    private TextView windDirCompassText;
    private TextView uvIndexText;
    private TextView visibilityText;
    private TextView altimeterText;
    private RecyclerView forecastRecycler;
    private RecyclerView hoursRecycler;
    private Button navButton;

    public DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化各控件
        CityName = (TextView) findViewById(R.id.now_city_name_text);
        NowTemText = (TextView) findViewById(R.id.now_day_tem_text);
        NowPhraseText = (TextView) findViewById(R.id.now_day_phrase_text);
        NowTimeText = (TextView) findViewById(R.id.now_dateTime_format);
        aqiTipsText = (TextView) findViewById(R.id.now_day_aqi_Tips);
        feelsLikeText = (TextView) findViewById(R.id.aqi_day_feelsLike_text);
        humidityText = (TextView) findViewById(R.id.aqi_day_humidity_text);
        windDirCompassText = (TextView) findViewById(R.id.aqi_day_windDirCompass_text);
        uvIndexText = (TextView) findViewById(R.id.aqi_day_uvIndex_text);
        visibilityText = (TextView) findViewById(R.id.aqi_day_visibility_text);
        altimeterText = (TextView) findViewById(R.id.aqi_day_altimeter_text);
        forecastRecycler = (RecyclerView) findViewById(R.id.forecast_recycler);
        hoursRecycler = (RecyclerView) findViewById(R.id.hours_recycler);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navButton = (Button) findViewById(R.id.nav_button);


        //登录就向接口发送请求，获取返回的weather，显示到界面上
        getNowCityWeather();

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

    public Weather getNowCityWeather() {
        String NowCityURL = "https://v0.yiketianqi.com/api/worldchina?appid=36979775&appsecret=Bx4ExaIj&aqi=1";
        HttpUtil.sendOkHttpRequest(NowCityURL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Log.d("Here", responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Weather weather = new Gson().fromJson(responseText,Weather.class);
                        Log.d("Here", "run: " + weather.CityName);
                        Log.d("Here", "run: " + weather.day.phrase);
                        Log.d("Here", "run: " + weather.monthList.get(0).date);
                        Log.d("Here", "run: " + weather.monthList.get(0).forecastDay.phrase);
                        Log.d("Here", "run: size = " + weather.hoursList.size());
                        //传入设置页面的方法
                        showWeatherContent(weather);
                    }
                });

            }
        });
        return null;
    }


    public void showWeatherContent(Weather weather) {
        CityName.setText(weather.CityName);
        NowTemText.setText(weather.day.temperature);
        NowPhraseText.setText(weather.day.phrase);
        NowTimeText.setText(weather.updateTime);
        aqiTipsText.setText(weather.day.aqi.air_tips);
        feelsLikeText.setText(weather.day.feelsLike);
        humidityText.setText(weather.day.humidity);
        windDirCompassText.setText(weather.day.windDirCompass);
        uvIndexText.setText(weather.day.uvIndex);
        visibilityText.setText(weather.day.visibility);
        altimeterText.setText(weather.day.altimeter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        forecastAdapter adapter = new forecastAdapter(weather.monthList);
        forecastRecycler.setAdapter(adapter);
        forecastRecycler.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        HoursAdapter hoursAdapter = new HoursAdapter(weather.hoursList);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        hoursRecycler.setLayoutManager(linearLayoutManager1);
        hoursRecycler.setAdapter(hoursAdapter);
    }

    public void SearchForManyCityByEditText(String content, SearchAdapter searchAdapter) {
        String countyIdURL = "https://geoapi.heweather.net/v2/city/lookup?location=" + content + "&key=b92646e0f4194731b50870798cfad1d0";
        HttpUtil.sendOkHttpRequest(countyIdURL, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Toast.makeText(MyApplication.getContext(), "获取搜索信息失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //有返回，就拿返回的数据传入一个专门解析的方法（handleCountyResponseFromHF），获取数据组成一个list
                final String responseText = response.body().string();
                List<City> cityList = search.handleCountyResponseFromHF(responseText, MyApplication.getContext());
                searchAdapter.setList(cityList);
                //aqiTipsText(cityList);
                //接收了更新的数据，然后提醒适配器进行更新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //主线程！！！！
                        searchAdapter.notifyDataSetChanged();
                        Log.d("Here", "finish notifyDataSetChanged");

                    }
                });

            }
        });
    }
}