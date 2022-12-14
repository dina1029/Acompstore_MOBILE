package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelNotification;

import java.util.List;

public class ResponseNotification {
    private int kode;
    private List<ModelNotification> data;

    public int getKode() {
        return kode;
    }

    public List<ModelNotification> getData() {
        return data;
    }

    public ResponseNotification(int kode, List<ModelNotification> data) {
        this.kode = kode;
        this.data = data;
    }

    public ResponseNotification() {
    }
}
