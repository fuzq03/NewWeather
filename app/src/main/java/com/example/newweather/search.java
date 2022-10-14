package com.example.newweather;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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


public class search extends Fragment {

    private ImageView imageView;
    private EditText editText;
    private Button button;
    private RecyclerView recyclerView;
    public SearchAdapter searchAdapter;

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
        button = view.findViewById(R.id.btn_search);
        recyclerView = view.findViewById(R.id.search_recycler);

        MyApplication myApplication = new MyApplication();
        searchAdapter = new SearchAdapter(dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myApplication.getContext());
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
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
}