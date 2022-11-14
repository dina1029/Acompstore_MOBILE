package com.example.acompstore.pModel;

public class ModelKategori {
    private String idKategori, NamaKategori, HargaKategori, Stok, Gambar, Diskon, Kategori_idBarang, Terjual;

    public String getIdKategori() {
        return idKategori;
    }

    public String getNamaKategori() {
        return NamaKategori;
    }

    public String getHargaKategori() {
        return HargaKategori;
    }

    public String getStok() {
        return Stok;
    }

    public String getGambar() {
        return Gambar;
    }

    public String getDiskon() {
        return Diskon;
    }

    public String getKategori_idBarang() {
        return Kategori_idBarang;
    }

    public String getTerjual() {
        return Terjual;
    }

    public ModelKategori() {
    }

    public ModelKategori(String idKategori, String namaKategori, String hargaKategori, String stok, String gambar, String diskon, String kategori_idBarang, String terjual) {
        this.idKategori = idKategori;
        NamaKategori = namaKategori;
        HargaKategori = hargaKategori;
        Stok = stok;
        Gambar = gambar;
        Diskon = diskon;
        Kategori_idBarang = kategori_idBarang;
        Terjual = terjual;
    }
}
