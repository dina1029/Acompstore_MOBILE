package com.example.acompstore.pModel;

public class ModelHistory {
    private String idBeli;
    private String Tanggal;
    private String TotalBeli;
    private String Jumlah;
    private String Gambar;

    public String getIdBeli() {
        return idBeli;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public String getTotalBeli() {
        return TotalBeli;
    }

    public String getJumlah() {
        return Jumlah;
    }

    public String getGambar() {
        return Gambar;
    }

    public ModelHistory(String idBeli, String tanggal, String totalBeli, String jumlah, String gambar) {
        this.idBeli = idBeli;
        Tanggal = tanggal;
        TotalBeli = totalBeli;
        Jumlah = jumlah;
        Gambar = gambar;
    }

    public ModelHistory() {
    }
}
