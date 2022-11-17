package com.example.acompstore.pModel;

public class ModelHomeBarang {
    private String idKategori;
    private String idBarang;
    private String NamaBarang;
    private String HargaKategori;
    private String Gambar;
    private String Stok;
    private String Diskon;
    private String Terjual;
    private String DeskripsiBarang;

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
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

    public String getStok() {
        return Stok;
    }

    public void setStok(String stok) {
        Stok = stok;
    }

    public String getDiskon() {
        return Diskon;
    }

    public void setDiskon(String diskon) {
        Diskon = diskon;
    }

    public String getTerjual() {
        return Terjual;
    }

    public void setTerjual(String terjual) {
        Terjual = terjual;
    }

    public String getDeskripsiBarang() {
        return DeskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        DeskripsiBarang = deskripsiBarang;
    }

    public ModelHomeBarang() {
    }

    public ModelHomeBarang(String idKategori, String idBarang, String namaBarang, String hargaKategori, String gambar, String stok, String diskon, String terjual, String deskripsiBarang) {
        this.idKategori = idKategori;
        this.idBarang = idBarang;
        NamaBarang = namaBarang;
        HargaKategori = hargaKategori;
        Gambar = gambar;
        Stok = stok;
        Diskon = diskon;
        Terjual = terjual;
        DeskripsiBarang = deskripsiBarang;
    }
}
