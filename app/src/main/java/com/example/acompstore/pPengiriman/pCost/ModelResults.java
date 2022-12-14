package com.example.acompstore.pPengiriman.pCost;

import java.util.List;

public class ModelResults {
    private String code;
    private String name;
    private List<ModelCosts> costs;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<ModelCosts> getCosts() {
        return costs;
    }

    public ModelResults() {
    }

    public ModelResults(String code, String name, List<ModelCosts> costs) {
        this.code = code;
        this.name = name;
        this.costs = costs;
    }
}
