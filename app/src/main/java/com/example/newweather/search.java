package com.example.newweather;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newweather.data.City;
import com.example.newweather.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class search extends Fragment implements View.OnClickListener{

    private ImageView imageView;
    private EditText editText;

    private RecyclerView recyclerViewLayout;
    private LinearLayout hotCityLayout;
    public SearchAdapter searchAdapter;

    public Button btn_beijing;
    public Button btn_shanghai;
    public Button btn_shenzhen;
    public Button btn_guangzhou;
    public Button btn_wuhan;
    public Button btn_changsha;
    public Button btn_nanjing;
    public Button btn_suzhou;
    public Button btn_xian;
    public Button btn_location;

    List<City> dataList = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        imageView = view.findViewById(R.id.search_image);
        editText = view.findViewById(R.id.search_county_edit);
        recyclerViewLayout = view.findViewById(R.id.search_recycler);

        btn_beijing = view.findViewById(R.id.btn_beijing);
        btn_shanghai = view.findViewById(R.id.btn_shanghai);
        btn_shenzhen = view.findViewById(R.id.btn_shenzhen);
        btn_guangzhou = view.findViewById(R.id.btn_guangzhou);
        btn_wuhan = view.findViewById(R.id.btn_wuhan);
        btn_changsha = view.findViewById(R.id.btn_changsha);
        btn_nanjing = view.findViewById(R.id.btn_nanjing);
        btn_suzhou = view.findViewById(R.id.btn_suzhou);
        btn_xian = view.findViewById(R.id.btn_xian);
        btn_location = view.findViewById(R.id.btn_location);

        btn_beijing.setOnClickListener(this);
        btn_shanghai.setOnClickListener(this);
        btn_shenzhen.setOnClickListener(this);
        btn_guangzhou.setOnClickListener(this);
        btn_wuhan.setOnClickListener(this);
        btn_changsha.setOnClickListener(this);
        btn_nanjing.setOnClickListener(this);
        btn_suzhou.setOnClickListener(this);
        btn_xian.setOnClickListener(this);
        btn_location.setOnClickListener(this);

        MyApplication myApplication = new MyApplication();
        searchAdapter = new SearchAdapter(dataList, (MainActivity)getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myApplication.getContext());
        recyclerViewLayout.setAdapter(searchAdapter);
        recyclerViewLayout.setLayoutManager(linearLayoutManager);
        recyclerViewLayout.addItemDecoration(new DividerItemDecoration(recyclerViewLayout.getContext(), linearLayoutManager.getOrientation()));

        //recyclerViewLayout.setVisibility(View.GONE);
        //hotCityLayout.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //editable是当前文本框的内容，在这里把内容传到专门获取模糊搜索的方法中
                MainActivity mainActivity = new MainActivity();
                //hotCityLayout.setVisibility(View.GONE);
                //recyclerViewLayout.setVisibility(View.VISIBLE);
                mainActivity.SearchForManyCityByEditText(editable.toString(), searchAdapter);
            }
        });
        return view;
    }



    public static List<City> handleCountyResponseFromHF(String response, Context context) {

        List<City> dataList = new ArrayList<>();
        if(!TextUtils.isEmpty(response)) {
            try{
                Log.d("Here", "JSONArrayLength");
                //从这里开始就不执行了
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("location");
                Log.d("Here", "JSONArrayLength = " + jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    City city = new City();
                    city.setWeatherId(jsonObject1.getString("id"));
                    city.setCountyName(jsonObject1.getString("name"));
                    city.setAdm1(jsonObject1.getString("adm1"));
                    city.setAdm2(jsonObject1.getString("adm2"));
                    Log.d("Here", "getName : " + city.getCountyName());
                    dataList.add(city);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //一直到这里都没有执行
            Log.d("Here", "daList = " +dataList.size());
        }
        return dataList;
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.btn_beijing:
                mainActivity.getSearchCityWeatherByName("北京");
                break;
            case R.id.btn_shanghai:
                mainActivity.getSearchCityWeatherByName("上海");
                break;
            case R.id.btn_shenzhen:
                mainActivity.getSearchCityWeatherByName("深圳");
                break;
            case R.id.btn_guangzhou:
                mainActivity.getSearchCityWeatherByName("广州");
                break;
            case R.id.btn_wuhan:
                mainActivity.getSearchCityWeatherByName("武汉");
                break;
            case R.id.btn_changsha:
                mainActivity.getSearchCityWeatherByName("长沙");
                break;
            case R.id.btn_nanjing:
                mainActivity.getSearchCityWeatherByName("南京");
                break;
            case R.id.btn_suzhou:
                mainActivity.getSearchCityWeatherByName("苏州");
                break;
            case R.id.btn_xian:
                mainActivity.getSearchCityWeatherByName("西安");
                break;
            case R.id.btn_location:
                mainActivity.getNowCityWeather();
                break;
            default:
        }
    }
}