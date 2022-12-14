package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseHistory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceHistory {

    @GET("historydiproses")
    Call<ResponseHistory> getProsesHistory(@Query("idPembeli") String idPembeli);

    @GET("history")
    Call<ResponseHistory> getHistory(
            @Query("idPembeli") String idPembeli,
            @Query("kodeStatus") String kodeStatus
    );
}
