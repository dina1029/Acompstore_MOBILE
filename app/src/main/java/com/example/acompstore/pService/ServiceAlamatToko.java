package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseAlamatToko;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAlamatToko {

    @GET("alamattoko")
    Call<ResponseAlamatToko> getAlamatToko();
}
