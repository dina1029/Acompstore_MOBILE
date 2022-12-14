package com.example.acompstore.pPengiriman.pProvince;

public class ResponseProvinsi {
    private ModelRajaOngkirProvinsi rajaongkir;

    public ModelRajaOngkirProvinsi getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(ModelRajaOngkirProvinsi rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

    public ResponseProvinsi() {
    }

    public ResponseProvinsi(ModelRajaOngkirProvinsi rajaongkir) {
        this.rajaongkir = rajaongkir;
    }
}
