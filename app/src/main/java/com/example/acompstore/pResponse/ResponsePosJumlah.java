package com.example.acompstore.pResponse;

public class ResponsePosJumlah {
    private int kode;
    private String pesan;
    private String jumlah;

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

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public ResponsePosJumlah() {
    }

    public ResponsePosJumlah(int kode, String pesan, String jumlah) {
        this.kode = kode;
        this.pesan = pesan;
        this.jumlah = jumlah;
    }
}
