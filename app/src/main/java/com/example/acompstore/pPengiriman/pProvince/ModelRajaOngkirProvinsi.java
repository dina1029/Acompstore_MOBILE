package com.example.acompstore.pPengiriman.pProvince;

import com.example.acompstore.pPengiriman.StatusData;

import java.util.List;

public class ModelRajaOngkirProvinsi {
    private List<Object> query;
    private StatusData status;
    private List<ModelProvinsi> results;

    public List<Object> getQuery() {
        return query;
    }

    public void setQuery(List<Object> query) {
        this.query = query;
    }

    public StatusData getStatus() {
        return status;
    }

    public void setStatus(StatusData status) {
        this.status = status;
    }

    public List<ModelProvinsi> getResults() {
        return results;
    }

    public void setResults(List<ModelProvinsi> results) {
        this.results = results;
    }

    public ModelRajaOngkirProvinsi() {
    }

    public ModelRajaOngkirProvinsi(List<Object> query, StatusData status, List<ModelProvinsi> results) {
        this.query = query;
        this.status = status;
        this.results = results;
    }
}
