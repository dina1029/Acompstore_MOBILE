package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelAlamatToko;

import java.util.List;

public class ResponseAlamatToko {
    private byte kode;
    private String message;
    private List<ModelAlamatToko> data;

    public ResponseAlamatToko(byte kode, String message, List<ModelAlamatToko> data) {
        this.kode = kode;
        this.message = message;
        this.data = data;
    }

    public ResponseAlamatToko() {
    }

    public byte getKode() {
        return kode;
    }

    public String getMessage() {
        return message;
    }

    public List<ModelAlamatToko> getData() {
        return data;
    }
}
