package com.example.acompstore.pPengiriman.pCost;

public class ModelCost {
    private String value;
    private String etd;
    private String note;

    public String getValue() {
        return value;
    }

    public String getEtd() {
        return etd;
    }

    public String getNote() {
        return note;
    }

    public ModelCost() {
    }

    public ModelCost(String value, String etd, String note) {
        this.value = value;
        this.etd = etd;
        this.note = note;
    }
}
