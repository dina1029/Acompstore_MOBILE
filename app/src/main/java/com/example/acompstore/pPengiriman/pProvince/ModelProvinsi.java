package com.example.acompstore.pPengiriman.pProvince;

public class ModelProvinsi {
    private String province_id;
    private String province;

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public ModelProvinsi() {
    }

    public ModelProvinsi(String province_id, String province) {
        this.province_id = province_id;
        this.province = province;
    }
}
