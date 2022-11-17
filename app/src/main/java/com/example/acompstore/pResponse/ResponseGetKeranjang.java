package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelKategori;
import com.example.acompstore.pModel.ModelKeranjang;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetKeranjang {

    private byte kode;
    private List<ModelKeranjang> data;

    public byte getKode() {
        return kode;
    }

    public List<ModelKeranjang> getData() {
        return data;
    }

    public ResponseGetKeranjang() {
    }

    public ResponseGetKeranjang(byte kode, List<ModelKeranjang> data) {
        this.kode = kode;
        this.data = data;
    }
}
