package com.example.acompstore.pPengiriman.pCost;

import com.example.acompstore.pPengiriman.pCost.ModelCost;

import java.util.List;

public class ModelCosts {
    private String service;
    private String description;
    private List<ModelCost> cost;

    public String getService() {
        return service;
    }

    public String getDescription() {
        return description;
    }

    public List<ModelCost> getCost() {
        return cost;
    }

    public ModelCosts(String service, String description, List<ModelCost> cost) {
        this.service = service;
        this.description = description;
        this.cost = cost;
    }

    public ModelCosts() {
    }
}
