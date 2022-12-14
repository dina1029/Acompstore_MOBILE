package com.example.acompstore.pModel;

public class ModelAlamatToko {
    private String  id_alamat;
    private String id_provinsi;
    private String id_kota;
    private String kecamatan;
    private String kelurahan;
    private String detail_alamat;

    public String getId_alamat() {
        return id_alamat;
    }

    public String getId_provinsi() {
        return id_provinsi;
    }

    public String getId_kota() {
        return id_kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public String getDetail_alamat() {
        return detail_alamat;
    }

    public ModelAlamatToko() {
    }

    public ModelAlamatToko(String id_alamat, String id_provinsi, String id_kota, String kecamatan, String kelurahan, String detail_alamat) {
        this.id_alamat = id_alamat;
        this.id_provinsi = id_provinsi;
        this.id_kota = id_kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.detail_alamat = detail_alamat;
    }


}
