package com.example.acompstore.pModel;

public class ModelUbahAkun {

    private String NamaPembeli;
    private String NoHPPembeli;

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

    public ModelUbahAkun(String namaPembeli, String noHPPembeli) {
        this.NamaPembeli = namaPembeli;
        this.NoHPPembeli = noHPPembeli;
    }
}


