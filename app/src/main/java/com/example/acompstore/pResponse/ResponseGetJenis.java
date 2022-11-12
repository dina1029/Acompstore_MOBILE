package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelJenisBarang;

import java.util.List;

public class ResponseGetJenis {
    private byte kode;
    private List<ModelJenisBarang> data;

    public byte getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public List<ModelJenisBarang> getData() {
        return data;
    }

    public void setData(List<ModelJenisBarang> data) {
        this.data = data;
    }

    public ResponseGetJenis() {
    }

    public ResponseGetJenis(byte kode, List<ModelJenisBarang> data) {
        this.kode = kode;
        this.data = data;
    }
}
