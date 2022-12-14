package com.example.acompstore.pModel;

public class ModelBeli {
    private String idBeli;
    private String Tanggal;
    private String TotalBeli;
    private String Status;
    private String Beli_idPembeli;
    private String NamaBank;
    private String Rekening;
    private String NoResi;
    private String TanggalDikirim;
    private String JasaKurir;
    private String ServiceKurir;
    private String BiayaOngkir;
    private String EstimasiWaktu;
    private String Bapes_Oleh;
    private String Bapes_Alasan;

    public String getIdBeli() {
        return idBeli;
    }

    public void setIdBeli(String idBeli) {
        this.idBeli = idBeli;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getTotalBeli() {
        return TotalBeli;
    }

    public void setTotalBeli(String totalBeli) {
        TotalBeli = totalBeli;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBeli_idPembeli() {
        return Beli_idPembeli;
    }

    public void setBeli_idPembeli(String beli_idPembeli) {
        Beli_idPembeli = beli_idPembeli;
    }

    public String getNamaBank() {
        return NamaBank;
    }

    public void setNamaBank(String namaBank) {
        NamaBank = namaBank;
    }

    public String getRekening() {
        return Rekening;
    }

    public void setRekening(String rekening) {
        Rekening = rekening;
    }

    public String getNoResi() {
        return NoResi;
    }

    public void setNoResi(String noResi) {
        NoResi = noResi;
    }

    public String getTanggalDikirim() {
        return TanggalDikirim;
    }

    public void setTanggalDikirim(String tanggalDikirim) {
        TanggalDikirim = tanggalDikirim;
    }

    public String getJasaKurir() {
        return JasaKurir;
    }

    public void setJasaKurir(String jasaKurir) {
        JasaKurir = jasaKurir;
    }

    public String getServiceKurir() {
        return ServiceKurir;
    }

    public void setServiceKurir(String serviceKurir) {
        ServiceKurir = serviceKurir;
    }

    public String getBiayaOngkir() {
        return BiayaOngkir;
    }

    public void setBiayaOngkir(String biayaOngkir) {
        BiayaOngkir = biayaOngkir;
    }

    public String getEstimasiWaktu() {
        return EstimasiWaktu;
    }

    public void setEstimasiWaktu(String estimasiWaktu) {
        EstimasiWaktu = estimasiWaktu;
    }

    public String getBapes_Oleh() {
        return Bapes_Oleh;
    }

    public void setBapes_Oleh(String bapes_Oleh) {
        Bapes_Oleh = bapes_Oleh;
    }

    public String getBapes_Alasan() {
        return Bapes_Alasan;
    }

    public void setBapes_Alasan(String bapes_Alasan) {
        Bapes_Alasan = bapes_Alasan;
    }

    public ModelBeli(String idBeli, String tanggal, String totalBeli, String status, String beli_idPembeli, String namaBank, String rekening, String noResi, String tanggalDikirim, String jasaKurir, String serviceKurir, String biayaOngkir, String estimasiWaktu, String bapes_Oleh, String bapes_Alasan) {
        this.idBeli = idBeli;
        Tanggal = tanggal;
        TotalBeli = totalBeli;
        Status = status;
        Beli_idPembeli = beli_idPembeli;
        NamaBank = namaBank;
        Rekening = rekening;
        NoResi = noResi;
        TanggalDikirim = tanggalDikirim;
        JasaKurir = jasaKurir;
        ServiceKurir = serviceKurir;
        BiayaOngkir = biayaOngkir;
        EstimasiWaktu = estimasiWaktu;
        Bapes_Oleh = bapes_Oleh;
        Bapes_Alasan = bapes_Alasan;
    }

    public ModelBeli() {
    }
}
