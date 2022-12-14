package com.example.acompstore.pPengiriman;

import com.example.acompstore.pPengiriman.pCity.ResponseKota;
import com.example.acompstore.pPengiriman.pCost.ResponseCost;
import com.example.acompstore.pPengiriman.pProvince.ResponseProvinsi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServicePengiriman {

    @GET("province")
    Call<ResponseProvinsi> getProvinsi();

    @GET("city")
    Call<ResponseKota> getKota(@Query("province") int province);

    @FormUrlEncoded
    @POST("cost")
    Call<ResponseCost> getCost(
            @Field("origin") String origin,
            @Field("destination") String destination,
            @Field("weight") String weight,
            @Field("courier") String courier
    );
}
