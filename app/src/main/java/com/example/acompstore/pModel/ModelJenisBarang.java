package com.example.acompstore.pModel;

public class ModelJenisBarang {
    public String getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(String idJenis) {
        this.idJenis = idJenis;
    }

    public String getNamaJenis() {
        return NamaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        NamaJenis = namaJenis;
    }

    public ModelJenisBarang() {
    }

    public ModelJenisBarang(String idJenis, String namaJenis) {
        this.idJenis = idJenis;
        NamaJenis = namaJenis;
    }

    private String idJenis;
    private String NamaJenis;
}
