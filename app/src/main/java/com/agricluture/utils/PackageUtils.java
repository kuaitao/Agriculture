package com.agricluture.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by kk on 2018/3/10.
 */

public class PackageUtils {

    public static String getVersionName(Context context)
    {

        String version="";
        try
        {
            PackageInfo packageInfo =context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (Exception e)
        {
           e.printStackTrace();
        }
        return version;

    }

    public static long getVersionCode(Context context)
    {

        long version=-1;
        try
        {
            PackageInfo packageInfo =context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionCode;
        } catch (Exception e)
        {
           e.printStackTrace();
        }
        return version;

    }

    public static String getApplicationId(Context appContext) throws IllegalArgumentException {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo == null) {
                throw new IllegalArgumentException(" get application info = null, has no meta data! ");
            }
            return applicationInfo.metaData.getString("APP_ID");
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException(" get application info error! ", e);
        }
    }

}
