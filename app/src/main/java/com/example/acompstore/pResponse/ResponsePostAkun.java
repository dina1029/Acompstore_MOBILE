package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelPembeliAlamat;

public class ResponsePostAkun {
    private int kode;
    private String pesan;
    private ModelPembeliAlamat data;

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

    public ModelPembeliAlamat getData() {
        return data;
    }

    public void setData(ModelPembeliAlamat data) {
        this.data = data;
    }

    public ResponsePostAkun(int kode, String pesan, ModelPembeliAlamat data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }

    public ResponsePostAkun() {
    }

}
