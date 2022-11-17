package com.example.acompstore.pModel;

public class ModelKeranjang {
    private String idKeranjang;
    private String Keranjang_idPembeli;
    private String KeranjangJumlah;
    private String NamaKategori;
    private String HargaKategori;
    private String Stok;
    private String Gambar;
    private String Diskon;
    private String idBarang;
    private String NamaBarang;
    private boolean isSelectedCheck;

    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public String getKeranjang_idPembeli() {
        return Keranjang_idPembeli;
    }

    public void setKeranjang_idPembeli(String keranjang_idPembeli) {
        Keranjang_idPembeli = keranjang_idPembeli;
    }

    public String getKeranjangJumlah() {
        return KeranjangJumlah;
    }

    public void setKeranjangJumlah(String keranjangJumlah) {
        KeranjangJumlah = keranjangJumlah;
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

    public boolean isSelectedCheck() {
        return isSelectedCheck;
    }

    public void setSelectedCheck(boolean selectedCheck) {
        isSelectedCheck = selectedCheck;
    }

    public ModelKeranjang() {
    }

    public ModelKeranjang(String idKeranjang, String keranjang_idPembeli, String keranjangJumlah, String namaKategori, String hargaKategori, String stok, String gambar, String diskon, String idBarang, String namaBarang, boolean isSelectedCheck) {
        this.idKeranjang = idKeranjang;
        Keranjang_idPembeli = keranjang_idPembeli;
        KeranjangJumlah = keranjangJumlah;
        NamaKategori = namaKategori;
        HargaKategori = hargaKategori;
        Stok = stok;
        Gambar = gambar;
        Diskon = diskon;
        this.idBarang = idBarang;
        NamaBarang = namaBarang;
        this.isSelectedCheck = isSelectedCheck;
    }
}
