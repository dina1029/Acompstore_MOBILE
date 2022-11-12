package com.example.acompstore.pModel;

public class ModelKategori {
    private String idKategori, NamaKategori, HargaKategori, Stok, Gambar, Diskon, Kategori_idBarang;

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return NamaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        NamaKategori = namaKategori;
    }

    public String getHargaKategori() {
        return HargaKategori;
    }

    public void setHargaKategori(String hargaKategori) {
        HargaKategori = hargaKategori;
    }

    public String getStok() {
        return Stok;
    }

    public void setStok(String stok) {
        Stok = stok;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public String getDiskon() {
        return Diskon;
    }

    public void setDiskon(String diskon) {
        Diskon = diskon;
    }

    public String getKategori_idBarang() {
        return Kategori_idBarang;
    }

    public void setKategori_idBarang(String kategori_idBarang) {
        Kategori_idBarang = kategori_idBarang;
    }

    public ModelKategori() {
    }

    public ModelKategori(String idKategori, String namaKategori, String hargaKategori, String stok, String gambar, String diskon, String kategori_idBarang) {
        this.idKategori = idKategori;
        NamaKategori = namaKategori;
        HargaKategori = hargaKategori;
        Stok = stok;
        Gambar = gambar;
        Diskon = diskon;
        Kategori_idBarang = kategori_idBarang;
    }
}
