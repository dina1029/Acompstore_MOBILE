package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelPembeliAlamat;

import java.util.List;

public class ResponsePostPembeli {
    private byte kode;
    private String pesan;
    private List<ModelPembeliAlamat> data;

    public byte getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelPembeliAlamat> getData() {
        return data;
    }

    public void setData(List<ModelPembeliAlamat> data) {
        this.data = data;
    }

    public ResponsePostPembeli(byte kode, String pesan, List<ModelPembeliAlamat> data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }
    public ResponsePostPembeli() {
    }
}
