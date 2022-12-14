package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelKotaProvinsi;

public class ResponseKotaProvinsi {
    private int kode;
    private String pesan;
    private ModelKotaProvinsi data;

    public ResponseKotaProvinsi() {
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ModelKotaProvinsi getData() {
        return data;
    }

    public void setData(ModelKotaProvinsi data) {
        this.data = data;
    }

    public ResponseKotaProvinsi(int kode, String pesan, ModelKotaProvinsi data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }
}
