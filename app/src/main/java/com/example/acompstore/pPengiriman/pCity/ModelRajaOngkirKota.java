package com.example.acompstore.pPengiriman.pCity;

import com.example.acompstore.pPengiriman.StatusData;

import java.util.List;

public class ModelRajaOngkirKota {
    private ModelQueryKota query;

    public ModelQueryKota getQuery() {
        return query;
    }

    public void setQuery(ModelQueryKota query) {
        this.query = query;
    }

    public StatusData getStatus() {
        return status;
    }

    public void setStatus(StatusData status) {
        this.status = status;
    }

    public List<ModelKota> getResults() {
        return results;
    }

    public void setResults(List<ModelKota> results) {
        this.results = results;
    }

    public ModelRajaOngkirKota() {
    }

    public ModelRajaOngkirKota(ModelQueryKota query, StatusData status, List<ModelKota> results) {
        this.query = query;
        this.status = status;
        this.results = results;
    }

    private StatusData status;
    private List<ModelKota> results;
}
