package com.example.newweather.data;

public class City {

    private String CountyName;
    private String adm2;    //地区/城市的上级行政区划名称
    private String adm1;    //地区/城市所属一级行政区域
    private String WeatherId;

    public void setCountyName(String countyName) {
        CountyName = countyName;
    }

    public String getCountyName() {
        return CountyName;
    }

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }

    public String getAdm1() {
        return adm1;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }

    public String getAdm2() {
        return adm2;
    }

    public void setWeatherId(String weatherId) {
        WeatherId = weatherId;
    }

    public String getWeatherId() {
        return WeatherId;
    }

}
