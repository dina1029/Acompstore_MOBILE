package com.example.acompstore.pModel;

public class ModelPembeliAlamat {
    private String idPembeli;
    private String NamaPembeli;
    private String NoHPPembeli;
    private String EmailPembeli;
    private String PasswordPembeli;
    private String ActivePembeli;
    private String idAlamat;
    private String Provinsi;
    private String Kota;
    private String Kecamatan;
    private String Kelurahan;
    private String Address;
    private String Alamat_idPembeli;

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

    public String getActivePembeli() {
        return ActivePembeli;
    }

    public void setActivePembeli(String activePembeli) {
        ActivePembeli = activePembeli;
    }

    public String getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(String idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String provinsi) {
        Provinsi = provinsi;
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

    public String getKelurahan() {
        return Kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        Kelurahan = kelurahan;
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

    public ModelPembeliAlamat() {
    }

    public ModelPembeliAlamat(String idPembeli, String namaPembeli, String noHPPembeli, String emailPembeli, String passwordPembeli, String activePembeli, String idAlamat, String provinsi, String kota, String kecamatan, String kelurahan, String address, String alamat_idPembeli) {
        this.idPembeli = idPembeli;
        NamaPembeli = namaPembeli;
        NoHPPembeli = noHPPembeli;
        EmailPembeli = emailPembeli;
        PasswordPembeli = passwordPembeli;
        ActivePembeli = activePembeli;
        this.idAlamat = idAlamat;
        Provinsi = provinsi;
        Kota = kota;
        Kecamatan = kecamatan;
        Kelurahan = kelurahan;
        Address = address;
        Alamat_idPembeli = alamat_idPembeli;
    }
}
