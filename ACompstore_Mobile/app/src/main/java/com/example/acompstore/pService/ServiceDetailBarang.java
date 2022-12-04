package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseGetKategori;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceDetailBarang {
    @GET("detailkategori.php")
    Call<ResponseGetKategori> getDetailKategori(@Query("idBarangKu") String parameter);

}
