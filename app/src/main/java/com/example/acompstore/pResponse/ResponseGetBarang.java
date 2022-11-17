package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelHomeBarang;

import java.util.List;

public class ResponseGetBarang {
    private byte kode;
    private List<ModelHomeBarang> data;

    public byte getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public List<ModelHomeBarang> getData() {
        return data;
    }

    public void setData(List<ModelHomeBarang> data) {
        this.data = data;
    }

    public ResponseGetBarang() {
    }

    public ResponseGetBarang(byte kode, List<ModelHomeBarang> data) {
        this.kode = kode;
        this.data = data;
    }
}
