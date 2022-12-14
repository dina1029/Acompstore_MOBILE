package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseKotaProvinsi;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePostAkun;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ServiceAcount {

    @FormUrlEncoded
    @POST("alert_ubahakun")
    Call<ResponsePostAkun> setUbahAkun(
            @Field("idPembeli") String idPembeli,
            @Field("NamaPembeli") String namaPembeli,
            @Field("NoHPPembeli") String noHP,
            @Field("PasswordPembeli") String pasword
    );

    @FormUrlEncoded
    @POST("alert_ubahpassword.php")
    Call<ResponsePostAkun> setUbahKeamanan(
            @Field("idPembeli") String idPembeli,
            @Field("PasswordLama") String passwordLama,
            @Field("PasswordBaru") String passwordBaru

    );


    @PUT("alert_ubahalamat")
    Call<ResponseKotaProvinsi> getKotaProv(
            @Query("idKota") String idKota
    );
//    @GET("alert_ubahalamat")
//    Callback<ResponseKotaProvinsi> getKotaProvinsi
//            (@Field("idKota") String idKota
//            );

    @FormUrlEncoded
    @POST("alert_ubahalamat")
    Call<ResponsePostAkun> setUbahAlamat(
            @Field("idPembeli") String idPembeli,
            @Field("Password") String password,
            @Field("idAlamat") String idAlamat,
            @Field("Provinsi") String provinsi,
            @Field("Kota") String kota,
            @Field("Kecamatan") String kecamatan,
            @Field("Kelurahan") String kelurahan,
            @Field("Address") String address
    );


}
