package com.example.acompstore.pModel;

public class ModelNotification {
    private String idUpdateBeli;
    private String kodeUpdateBeli;
    private String tanggalUpdateBeli;
    private String statusUpdateBeli;
    private String Dibaca;
    private String Gambar;

    public String getIdUpdateBeli() {
        return idUpdateBeli;
    }

    public void setIdUpdateBeli(String idUpdateBeli) {
        this.idUpdateBeli = idUpdateBeli;
    }

    public String getKodeUpdateBeli() {
        return kodeUpdateBeli;
    }

    public void setKodeUpdateBeli(String kodeUpdateBeli) {
        this.kodeUpdateBeli = kodeUpdateBeli;
    }

    public String getTanggalUpdateBeli() {
        return tanggalUpdateBeli;
    }

    public void setTanggalUpdateBeli(String tanggalUpdateBeli) {
        this.tanggalUpdateBeli = tanggalUpdateBeli;
    }

    public String getStatusUpdateBeli() {
        return statusUpdateBeli;
    }

    public void setStatusUpdateBeli(String statusUpdateBeli) {
        this.statusUpdateBeli = statusUpdateBeli;
    }

    public String getDibaca() {
        return Dibaca;
    }

    public void setDibaca(String dibaca) {
        Dibaca = dibaca;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public ModelNotification(String idUpdateBeli, String kodeUpdateBeli, String tanggalUpdateBeli, String statusUpdateBeli, String dibaca, String gambar) {
        this.idUpdateBeli = idUpdateBeli;
        this.kodeUpdateBeli = kodeUpdateBeli;
        this.tanggalUpdateBeli = tanggalUpdateBeli;
        this.statusUpdateBeli = statusUpdateBeli;
        Dibaca = dibaca;
        Gambar = gambar;
    }

    public ModelNotification() {
    }


}
