package cn.dpocket.moplusand.uinew.tools;

import android.content.Context;
import android.content.SharedPreferences;

import cn.dpocket.moplusand.uinew.LemonApp;

/**
 * Created by Apple on 16/7/11.
 */
public class SettingUtils {


    private static String SETTIING_NAME = "lemon";
    private static SharedPreferences settings;

    static private void init() {
        if (settings == null)
            settings = LemonApp.getContext().getSharedPreferences(
                    SETTIING_NAME, Context.MODE_PRIVATE);
    }

    static private void putBoolean(String key, boolean value) {
        init();
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    static private boolean getBoolean(String key, boolean defValue) {
        init();
        return settings.getBoolean(key, defValue);
    }

    static private void putInt(String key, int value) {
        init();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    static private int getInt(String key, int defValue) {
        init();
        return settings.getInt(key, defValue);
    }

    static private void putString(String key, String value) {
        init();
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    static private String getString(String key, String defValue) {
        init();
        return settings.getString(key, defValue);
    }

    static private void putLong(String key, long value) {
        init();
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    static private long getLong(String key, long defValue) {
        init();
        return settings.getLong(key, defValue);
    }
}
