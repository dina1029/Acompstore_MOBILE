package com.example.acompstore.pModel;

public class ModelAlamat {

    public int getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(int idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getKota() {
        return Kota;
    }

    public void setKota(String kota) {
        Kota = kota;
    }

    public String getKecamatan() {
        return Kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAlamat_idPembeli() {
        return Alamat_idPembeli;
    }

    public void setAlamat_idPembeli(int alamat_idPembeli) {
        Alamat_idPembeli = alamat_idPembeli;
    }

    int idAlamat;
    String Kota;
    String Kecamatan;
    String Address;
    int Alamat_idPembeli;

    public ModelAlamat() {
    }

    public ModelAlamat(int idAlamat, String kota, String kecamatan, String address, int alamat_idPembeli) {
        this.idAlamat = idAlamat;
        Kota = kota;
        Kecamatan = kecamatan;
        Address = address;
        Alamat_idPembeli = alamat_idPembeli;
    }
}
