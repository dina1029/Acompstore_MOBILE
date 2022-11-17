package com.example.acompstore.pModel;

public class ModelHomeBarang {
    String idKategori;
    String idbarang;
    String NamaBarang;
    String HargaKategori;
    String Gambar;
    String Terjual;

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(String idbarang) {
        this.idbarang = idbarang;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        NamaBarang = namaBarang;
    }

    public String getHargaKategori() {
        return HargaKategori;
    }

    public void setHargaKategori(String hargaKategori) {
        HargaKategori = hargaKategori;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public String getTerjual() {
        return Terjual;
    }

    public void setTerjual(String terjual) {
        Terjual = terjual;
    }

    public ModelHomeBarang() {
    }

    public ModelHomeBarang(String idKategori, String idbarang, String namaBarang, String hargaKategori, String gambar, String terjual) {
        this.idKategori = idKategori;
        this.idbarang = idbarang;
        NamaBarang = namaBarang;
        HargaKategori = hargaKategori;
        Gambar = gambar;
        Terjual = terjual;
    }
}
