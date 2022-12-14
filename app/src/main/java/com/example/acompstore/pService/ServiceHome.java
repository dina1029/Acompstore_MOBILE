package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseGetBarang;
import com.example.acompstore.pResponse.ResponseGetJenis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceHome {

    @GET("homebarang.php")
    Call<ResponseGetBarang> getHomeBarangNoDiskon(
            @Query("idJenisBarang") int idJenisBarang
    );

    @GET("jenisbarang.php")
    Call<ResponseGetJenis> getHomeJenisBarang();

    @GET("searchbarang.php")
    Call<ResponseGetBarang> getCariBarang(
            @Query("NamaBarang") String NamaBarang
    );

}
