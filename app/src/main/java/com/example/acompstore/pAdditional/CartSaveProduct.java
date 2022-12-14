package com.example.acompstore.pAdditional;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.acompstore.pModel.ModelHomeBarang;
import com.example.acompstore.pModel.ModelKeranjang;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartSaveProduct {
    private static final String LIST_KEY = "list_key";
    public static void writeListKeranjang(Context context, List<ModelKeranjang> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();
    }

    public static List<ModelKeranjang> readListKeranjang(Context context){
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = shared.getString(LIST_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelKeranjang>>() {}.getType();
        List<ModelKeranjang> list = gson.fromJson(jsonString, type);
        return list;
    }
}
