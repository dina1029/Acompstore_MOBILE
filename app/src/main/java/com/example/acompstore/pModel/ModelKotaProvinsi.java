package com.example.acompstore.pModel;

public class ModelKotaProvinsi {
    private String province;
    private String city_name;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public ModelKotaProvinsi(String province, String city_name) {
        this.province = province;
        this.city_name = city_name;
    }

    public ModelKotaProvinsi() {
    }
}
