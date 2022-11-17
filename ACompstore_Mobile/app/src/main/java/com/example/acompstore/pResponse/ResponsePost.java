package com.example.acompstore.pResponse;

public class ResponsePost {
    private byte kode;

    public byte getKode() {
        return kode;
    }

    public void setKode(byte kode) {
        this.kode = kode;
    }

    public ResponsePost() {
    }

    public ResponsePost(byte kode) {
        this.kode = kode;
    }
}
