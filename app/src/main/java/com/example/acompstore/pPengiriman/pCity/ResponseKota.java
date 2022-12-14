package com.example.acompstore.pPengiriman.pCity;

public class ResponseKota {
    private ModelRajaOngkirKota rajaongkir;

    public ModelRajaOngkirKota getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(ModelRajaOngkirKota rajaongkir) {
        this.rajaongkir = rajaongkir;
    }

    public ResponseKota() {
    }

    public ResponseKota(ModelRajaOngkirKota rajaongkir) {
        this.rajaongkir = rajaongkir;
    }
}
