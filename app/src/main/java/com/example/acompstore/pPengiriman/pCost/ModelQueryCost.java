package com.example.acompstore.pPengiriman.pCost;

public class ModelQueryCost {
    private String origin;
    private String destination;
    private int weight;
    private String courier;

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public String getCourier() {
        return courier;
    }

    public ModelQueryCost() {
    }

    public ModelQueryCost(String origin, String destination, int weight, String courier) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.courier = courier;
    }

}
