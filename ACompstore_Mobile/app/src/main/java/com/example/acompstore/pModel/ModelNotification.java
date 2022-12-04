package com.example.acompstore.pModel;

import com.google.gson.annotations.SerializedName;

public class ModelNotification {
    private String tanggalUpdateBeli;
    private String statusUpdateBeli;
    private String keteranganUpdateBeli;
    private String kodeUpdateBeli;

    public String getTanggalupdatebeli() {
        return tanggalUpdateBeli;
    }

    public void setTanggalupdatebeli(String tanggalupdatebeli) {
        tanggalUpdateBeli = tanggalupdatebeli;
    }

    public String getStatusupdatebeli() {
        return statusUpdateBeli;
    }

    public void setStatusupdatebeli(String statusupdatebeli) {
        statusUpdateBeli = statusupdatebeli;
    }

    public String getKeteranganupdatebeli() {
        return keteranganUpdateBeli;
    }

    public void setKeteranganupdatebeli(String keteranganupdatebeli) {
        keteranganUpdateBeli = keteranganupdatebeli;
    }

    public String getKodeupdatebeli() {
        return kodeUpdateBeli;
    }

    public void setKodeupdatebeli(String kodeupdatebeli) {
        kodeUpdateBeli = kodeupdatebeli;
    }
}
