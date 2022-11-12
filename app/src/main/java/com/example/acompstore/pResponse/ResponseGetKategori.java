package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelKategori;

import java.util.List;

public class ResponseGetKategori {
    private byte kode;
    private List<ModelKategori> data;

    public byte getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public List<ModelKategori> getData() {
        return data;
    }

    public void setData(List<ModelKategori> data) {
        this.data = data;
    }

    public ResponseGetKategori(byte kode, List<ModelKategori> data) {
        this.kode = kode;
        this.data = data;
    }

    public ResponseGetKategori() {
    }
}
