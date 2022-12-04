package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelPembeliAlamat;

import java.util.List;

public class ResponsePostPembeli {
    private byte kode;
    private List<ModelPembeliAlamat> data;

    public byte getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public List<ModelPembeliAlamat> getData() {
        return data;
    }

    public void setData(List<ModelPembeliAlamat> data) {
        this.data = data;
    }

    public ResponsePostPembeli(byte kode, List<ModelPembeliAlamat> data) {
        this.kode = kode;
        this.data = data;
    }

    public ResponsePostPembeli() {
    }
}
