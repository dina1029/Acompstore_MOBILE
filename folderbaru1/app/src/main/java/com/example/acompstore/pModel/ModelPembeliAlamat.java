package com.example.acompstore.pModel;

public class ModelPembeliAlamat {
    String idPembeli;
    String NamaPembeli;
    String NoHPPembeli;
    String EmailPembeli;
    String PasswordPembeli;
    String idAlamat;
    String Kota;
    String Kecamatan;
    String Address;
    String Alamat_idPembeli;

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getNamaPembeli() {
        return NamaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        NamaPembeli = namaPembeli;
    }

    public String getNoHPPembeli() {
        return NoHPPembeli;
    }

    public void setNoHPPembeli(String noHPPembeli) {
        NoHPPembeli = noHPPembeli;
    }

    public String getEmailPembeli() {
        return EmailPembeli;
    }

    public void setEmailPembeli(String emailPembeli) {
        EmailPembeli = emailPembeli;
    }

    public String getPasswordPembeli() {
        return PasswordPembeli;
    }

    public void setPasswordPembeli(String passwordPembeli) {
        PasswordPembeli = passwordPembeli;
    }

    public String getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(String idAlamat) {
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

    public String getAlamat_idPembeli() {
        return Alamat_idPembeli;
    }

    public void setAlamat_idPembeli(String alamat_idPembeli) {
        Alamat_idPembeli = alamat_idPembeli;
    }

    public ModelPembeliAlamat(String idPembeli, String namaPembeli, String noHPPembeli, String emailPembeli, String passwordPembeli, String idAlamat, String kota, String kecamatan, String address, String alamat_idPembeli) {
        this.idPembeli = idPembeli;
        NamaPembeli = namaPembeli;
        NoHPPembeli = noHPPembeli;
        EmailPembeli = emailPembeli;
        PasswordPembeli = passwordPembeli;
        this.idAlamat = idAlamat;
        Kota = kota;
        Kecamatan = kecamatan;
        Address = address;
        Alamat_idPembeli = alamat_idPembeli;
    }

    public ModelPembeliAlamat() {
    }
}
