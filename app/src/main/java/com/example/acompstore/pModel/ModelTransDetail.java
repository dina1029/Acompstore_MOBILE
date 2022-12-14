package com.example.acompstore.pModel;

public class ModelTransDetail {
    private String idDetbel;
    private String Jumlah;
    private String DetailHarga;
    private String DetailDiskon;
    private String NamaKategori;
    private String Gambar;
    private String idBarang;
    private String NamaBarang;

    public String getIdDetbel() {
        return idDetbel;
    }

    public String getJumlah() {
        return Jumlah;
    }

    public String getDetailHarga() {
        return DetailHarga;
    }

    public String getDetailDiskon() {
        return DetailDiskon;
    }

    public String getNamaKategori() {
        return NamaKategori;
    }

    public String getGambar() {
        return Gambar;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public ModelTransDetail(String idDetbel, String jumlah, String detailHarga, String detailDiskon, String namaKategori, String gambar, String idBarang, String namaBarang) {
        this.idDetbel = idDetbel;
        Jumlah = jumlah;
        DetailHarga = detailHarga;
        DetailDiskon = detailDiskon;
        NamaKategori = namaKategori;
        Gambar = gambar;
        this.idBarang = idBarang;
        NamaBarang = namaBarang;
    }

    public ModelTransDetail() {
    }
}
