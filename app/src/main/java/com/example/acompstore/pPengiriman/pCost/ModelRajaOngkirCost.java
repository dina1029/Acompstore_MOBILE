package com.example.acompstore.pPengiriman.pCost;

import com.example.acompstore.pPengiriman.StatusData;
import com.example.acompstore.pPengiriman.pCity.ModelKota;

import java.util.List;

public class ModelRajaOngkirCost {
    private ModelQueryCost query;
    private StatusData status;
    private ModelKota origin_details;
    private ModelKota destination_details;
    private List<ModelResults> results;

    public ModelQueryCost getQuery() {
        return query;
    }

    public StatusData getStatus() {
        return status;
    }

    public ModelKota getOrigin_details() {
        return origin_details;
    }

    public ModelKota getDestination_details() {
        return destination_details;
    }

    public List<ModelResults> getResults() {
        return results;
    }

    public ModelRajaOngkirCost(ModelQueryCost query, StatusData status, ModelKota origin_details, ModelKota destination_details, List<ModelResults> results) {
        this.query = query;
        this.status = status;
        this.origin_details = origin_details;
        this.destination_details = destination_details;
        this.results = results;
    }

    public ModelRajaOngkirCost() {
    }
}
