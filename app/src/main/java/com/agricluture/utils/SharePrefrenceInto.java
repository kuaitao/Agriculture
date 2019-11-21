package com.agricluture.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.agricluture.main.MyApplication;


public class SharePrefrenceInto {
    private static SharePrefrenceInto instance;
    private SharedPreferences sharedPreferences = null;


    private SharePrefrenceInto() {
        initSharedPreferences(MyApplication.getInstance());
    }

    public static SharePrefrenceInto getInstance() {
        if (null == instance) {
            instance = new SharePrefrenceInto();
        }
        return instance;
    }

    public void initSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("IntoSharedPreferences", Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    /**
     * 保存键值 Boolean型
     *
     * @param key
     * @param value
     */

    public void saveBooleanValue(String key, boolean value) {
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }


    /**
     * 获取boolean值
     *
     * @param key
     * @param defaultValue
     * @return
     */

    public boolean readBooleanValue(String key, boolean defaultValue) {
        if (sharedPreferences == null) {
            return defaultValue;
        }
        return sharedPreferences.getBoolean(key, defaultValue);
    }



}
