package com.example.acompstore.pPengiriman;

public class StatusData {
    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusData() {
    }

    public StatusData(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
