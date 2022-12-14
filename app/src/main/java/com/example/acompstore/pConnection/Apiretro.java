package com.example.acompstore.pConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiretro {
    public static final String public_idPembeli = "1";
    //        public static String base_url = "http://172.16.109.148/naise/acompstore/";
//        public static String base_url = "http://192.168.100.49/naise/acompstore/";
        public static String base_url = "http://172.16.109.29/naise/acompstore/";
//    public static String base_url = "http://192.168.1.2/naise/acompstore/";



    private static Retrofit retro;

    public static Retrofit getService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retro == null) {
            retro = new Retrofit.Builder().baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retro;
    }

    public static Retrofit retrofit = null;

    public static Retrofit getRajaOngkirUrl() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.addInterceptor(chain -> {
            //apikey 1 = aa0d50f66fb530481d1b27ffde35bcca
            //apikey 2 = 58a126fcb4e7161ffa4a259232550c42
            Request request = chain.request().newBuilder()
                    .addHeader("key", "aa0d50f66fb530481d1b27ffde35bcca")
                    .build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.rajaongkir.com/starter/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
