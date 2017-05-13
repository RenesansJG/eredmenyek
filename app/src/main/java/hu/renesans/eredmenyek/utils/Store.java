package hu.renesans.eredmenyek.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class Store {
    private static final String ERROR_UNINITIALIZED = "Store must be initialized before use!";

    private static SharedPreferences preferences;
    private static Gson gson;

    public static void init(Context context) {
        preferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        gson = new Gson();
    }

    private static void checkInit() {
        if (preferences == null || gson == null) {
            Log.e("Store", ERROR_UNINITIALIZED);
            throw new RuntimeException(ERROR_UNINITIALIZED);
        }
    }

    @SuppressLint("ApplySharedPref")
    public static <T> void put(String key, T element) {
        checkInit();
        preferences.edit().putString(key, gson.toJson(element, element.getClass())).commit();
    }

    public static <T> T get(String key, Type type) {
        checkInit();
        String content = preferences.getString(key, "");
        return gson.fromJson(content, type);
    }

    @SuppressLint("ApplySharedPref")
    public static void remove(String key) {
        checkInit();
        preferences.edit().remove(key).commit();
    }
}
