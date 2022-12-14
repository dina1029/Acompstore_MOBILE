package com.example.acompstore.pResponse;

public class ResponsePosMessage {
    private byte kode;
    private String pesan;

    public ResponsePosMessage() {
    }

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

    public ResponsePosMessage(byte kode, String pesan) {
        this.kode = kode;
        this.pesan = pesan;
    }
}
