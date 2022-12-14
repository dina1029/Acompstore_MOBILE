package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelNotification;

import java.util.List;

public class ResponsePos {

    private byte kode;

    public byte getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public ResponsePos() {
    }

    public ResponsePos(byte kode) {
        this.kode = kode;
    }
//    private byte kode;
//
//    public List<ModelNotification> getData() {
//        return data;
//    }
//
//    public void setData(List<ModelNotification> data) {
//        this.data = data;
//    }
//
//    private List<ModelNotification> data;
//
//    public byte getKode() {
//        return kode;
//    }
//
//    public void setKode(byte kode) {
//        this.kode = kode;
//    }
//
//    public ResponsePos() {
//    }
//
//    public ResponsePos(byte kode) {
//        this.kode = kode;
//    }
}
