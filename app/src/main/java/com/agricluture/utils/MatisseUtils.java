package com.agricluture.utils;

import android.app.Activity;


import com.agricluture.main.MyApplication;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.compress.CompressHelper;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.ui.widget.CropImageView;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by kk on 2018/3/21.
 */

public class MatisseUtils {
    //直接掉起相机
    public static void  getCamera(Activity activity, int resultCode){
        getCamera(activity,true,resultCode);
    }

    //直接掉起相机
    public static void  getCamera(Activity activity, boolean isCircleStyle, int resultCode){
        Matisse.from(activity)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(new CaptureStrategy(false, PackageUtils.getApplicationId(activity)+".fileprovider"))
                .maxSelectable(1)
                .isCrop(true)
                .isCropSaveRectangle(true)
                .onlyCapture(true)
                .cropStyle(isCircleStyle? CropImageView.Style.CIRCLE:CropImageView.Style.RECTANGLE)
                .imageEngine(new GlideEngine())
                .forResult(resultCode);

    }

    //直接掉起相机
    public static void  getCameraWithoutCrop(Activity activity, int resultCode){
        Matisse.from(activity)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(new CaptureStrategy(false, PackageUtils.getApplicationId(activity)+".fileprovider"))
                .maxSelectable(1)
                .isCrop(false)
                .onlyCapture(true)
                .imageEngine(new GlideEngine())
                .forResult(resultCode);
    }

    //相册+相机
  public static void  getSingleImage(Activity activity, int resultCode){
      getSingleImage(activity,true,resultCode);
    }

    //相册+相机
    public static void  getSingleImage(Activity activity, boolean isCircleStyle, int resultCode){
        Matisse.from(activity)
                .choose(MimeType.ofAll(), false)
                .capture(true)
                .captureStrategy(new CaptureStrategy(false, PackageUtils.getApplicationId(activity)+".fileprovider"))
                .isCrop(true)
                .isCropSaveRectangle(true)
                .cropStyle(isCircleStyle?CropImageView.Style.CIRCLE:CropImageView.Style.RECTANGLE)
                .imageEngine(new GlideEngine())
                .forResult(resultCode);

    }

    public static void  getImages(Activity activity, int num, int resultCode){
      Matisse.from(activity)
              .choose(MimeType.ofAll(), false)
              .countable(true)
              .capture(true)
              .isCrop(false)
              .captureStrategy(new CaptureStrategy(false, PackageUtils.getApplicationId(activity)+".fileprovider"))
              .maxSelectable(num)
              .imageEngine(new GlideEngine())
              .forResult(resultCode);
    }

   public static File compressToFile(Activity activity, File file){
      File compressFile= CompressHelper.getDefault(activity.getApplicationContext()).compressToFile(file);
//      ToastUtils.makeText(activity, getReadableFileSize(file.length()) + " PK " + getReadableFileSize(compressFile.length()), Toast.LENGTH_SHORT).show();
      return compressFile;
  }

  public static File compressToFile(File file){
      File compressFile= CompressHelper.getDefault(MyApplication.getInstance()).compressToFile(file);
//      Log.e("compressToFile", getReadableFileSize(file.length()) + " PK " + getReadableFileSize(compressFile.length()));
      return compressFile;
  }

    public static String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
