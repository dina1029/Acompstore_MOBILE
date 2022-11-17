package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseGetBarang;
import com.example.acompstore.pResponse.ResponsePost;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceHome {

    @GET("homebarang.php")
    Call<ResponseGetBarang> getHomeBarangNoDiskon();
}
