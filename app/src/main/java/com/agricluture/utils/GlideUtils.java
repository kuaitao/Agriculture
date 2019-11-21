package com.agricluture.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.agriculture.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**Glide 封装类
 * Created by zem
 * on 2018/12/6 .
 */


public class GlideUtils {
    private static int defaultImage = R.drawable.ic_launcher_background;
    private static int defaultImageBase = R.drawable.ic_launcher_background;
    private static int defaultManImage = R.mipmap.ic_launcher;

    public static void setDefaultImage(int defaultImageiv) {
        defaultImage = defaultImageiv;
    }

    public static void setByteImageSrc(Context context, ImageView view, byte[] bytes) {

        if (view == null) {
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage);
        try {
            Glide.with(context)
                    .asBitmap()
                    .load(bytes)
                    .apply(options)
                    .into(view);
            changeToDefaultImageBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static <T> void setImageSrcNo(Context context, ImageView view, T url) {
        if (view == null) {//避免  由于在子线程中调用了 此时activity已销毁了  还调用该方法 导致glide context 为空
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).diskCacheStrategy(DiskCacheStrategy.NONE).dontAnimate().centerCrop();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
        changeToDefaultImageBase();
    }


    public static <T> void setImageSrc(Context context, ImageView view, T url) {
        if (view == null) {//避免  由于在子线程中调用了 此时activity已销毁了  还调用该方法 导致glide context 为空
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).dontAnimate().centerCrop();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
        changeToDefaultImageBase();
    }

    public static <T> void setImageSrc_clean(Context context, ImageView view, T url) {
        if (view == null) {//避免  由于在子线程中调用了 此时activity已销毁了  还调用该方法 导致glide context 为空
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).dontAnimate().centerCrop();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
        changeToDefaultImageBase();
    }
    public static <T> void setImageSrc(Context context, ImageView view, T url, int defaultImg) {
        if (view == null) {//避免  由于在子线程中调用了 此时activity已销毁了  还调用该方法 导致glide context 为空
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).dontAnimate().centerCrop();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
        changeToDefaultImageBase();
    }

    public static <T> void setImageSrcNoCenterCrop(Context context, ImageView view, T url) {
        if (view == null) {//避免  由于在子线程中调用了 此时activity已销毁了  还调用该方法 导致glide context 为空
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).dontAnimate();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
        changeToDefaultImageBase();
    }
//
//
//    public static <T> void setBgImage(Context context, final View view, T url) {
//        if (view == null) {
//            return;
//        }
//        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(context)
//                .asBitmap()
//                .load(url)
//                .apply(options)
//                .into(new SimpleTarget() {
//                          @Override
//                          public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
//                              Drawable drawable = new BitmapDrawable((Bitmap) resource);
//                              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                                  view.setBackground(drawable);    //设置背景
//                              }
//                          }
//                      }
//                );
//        changeToDefaultImageBase();
//    }

    public static <T> void setBgCircleImage(final Context context, final View view, T url) {
        if (view == null) {
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asBitmap()
                .apply(options)
                .load(url)
                .into(new SimpleTarget() {
                    @Override
                    public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), (Bitmap) resource);
                        drawable.setCircular(true); //设置圆形
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.setBackground(drawable);    //设置背景
                        }
                    }

                  }
                );
        changeToDefaultImageBase();
    }

    public static <T> void setBgRoundRectImage(final Context context, final View view, T url, final int cornerDp) {
        if (view == null) {
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(options)
                .into(new SimpleTarget() {
                    @Override
                    public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), (Bitmap) resource);
                        drawable.setCornerRadius(cornerDp); //设置圆角弧度
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.setBackground(drawable);    //设置背景
                        }
                    }

                  }
                );

        changeToDefaultImageBase();
    }

    private static void changeToDefaultImageBase() {
        defaultImage = defaultImageBase;
    }

    private static int dpToPx(int dp) {
        return (int) Resources.getSystem().getDisplayMetrics().density * dp;
    }


    public static <T> void setImageSrcOne(Context context, ImageView view, T url) {
        if (view == null) {
            return;
        }
        RequestOptions options = new RequestOptions().error(defaultImage).placeholder(defaultImage).dontAnimate().centerCrop();
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
        changeToDefaultImageBase();
    }



    public static void pauseRequests(Fragment fragment) {
        if (fragment == null||fragment.getActivity()==null) {return;}
        if(!Glide.with(fragment).isPaused()){
            Glide.with(fragment).pauseRequests();
        }
    }

    public static void resumeRequests(Fragment fragment) {
        if (fragment == null||fragment.getActivity()==null){return;}
        if(Glide.with(fragment).isPaused()){
            Glide.with(fragment).resumeRequests();
        }
    }

    public static void pauseRequests(Activity activity) {
        if (activity == null || activity.isFinishing()) {return;}
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity == null || activity.isDestroyed()) {return;}
        }
        if(!Glide.with(activity).isPaused()){
            Glide.with(activity).pauseRequests();
        }
    }

    public static void resumeRequests(Activity activity) {
        if (activity == null || activity.isFinishing()) {return;}
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity == null || activity.isDestroyed()) {return;}
        }
        if(Glide.with(activity).isPaused()){
            Glide.with(activity).resumeRequests();
        }
    }

    public static void setGlideLifeCycleWithRecyclerView(RecyclerView recyclerView, final Activity context){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                   resumeRequests(context);
                } else {
                  pauseRequests(context);
                }
            }
        });
    }

    public static void setGlideLifeCycleWithRecyclerView(RecyclerView recyclerView, final Fragment context){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                   resumeRequests(context);
                } else {
                    pauseRequests(context);
                }
            }
        });
    }
}
