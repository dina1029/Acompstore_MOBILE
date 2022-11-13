package com.example.acompstore.pAdditional;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.acompstore.pModel.ModelHomeBarang;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartSaveProduct {
    private static final String LIST_KEY = "list_key";
    public static void writeListInProduct(Context context, List<ModelHomeBarang> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();
    }

    public static List<ModelHomeBarang> readListInProduct(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_KEY, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ModelHomeBarang>>() {}.getType();
        List<ModelHomeBarang> list = gson.fromJson(jsonString, type);
        return list;
    }
}
