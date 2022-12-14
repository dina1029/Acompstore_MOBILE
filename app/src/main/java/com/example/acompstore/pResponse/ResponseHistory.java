package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelHistory;

import java.util.List;

public class ResponseHistory {
    private short kode;
    private String pesan;
    private List<ModelHistory> data;

    public short getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelHistory> getData() {
        return data;
    }

    public ResponseHistory(short kode, String pesan, List<ModelHistory> data) {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }

    public ResponseHistory() {
    }
}
