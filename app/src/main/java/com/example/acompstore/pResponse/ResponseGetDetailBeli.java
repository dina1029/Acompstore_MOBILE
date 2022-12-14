package com.example.acompstore.pResponse;

import com.example.acompstore.pModel.ModelBeli;
import com.example.acompstore.pModel.ModelTransDetail;

import java.util.List;

public class ResponseGetDetailBeli {
    private short kode;
    private String pesan;
    private ModelBeli beli;
    private List<ModelTransDetail> detail_beli;

    public ResponseGetDetailBeli() {
    }

    public short getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public ModelBeli getBeli() {
        return beli;
    }

    public List<ModelTransDetail> getDetail_beli() {
        return detail_beli;
    }

    public ResponseGetDetailBeli(short kode, String pesan, ModelBeli beli, List<ModelTransDetail> detail_beli) {
        this.kode = kode;
        this.pesan = pesan;
        this.beli = beli;
        this.detail_beli = detail_beli;
    }
}
