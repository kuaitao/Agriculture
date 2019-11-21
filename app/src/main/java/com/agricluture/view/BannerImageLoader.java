package com.agricluture.view;

import android.content.Context;
import android.widget.ImageView;

import com.agricluture.utils.GlideUtils;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by a on 2018/6/27.
 */

public class BannerImageLoader extends ImageLoader {
    public static final int RECT_IMAGE = 1;
    public static final int ROUND_RECT_IMAGE = 2;
    public int flags;
    public BannerImageLoader(Context context, int flags){
        this.flags=flags;
    }
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if(flags==RECT_IMAGE){
            GlideUtils.setImageSrc(context,imageView, path);
        }else{
            GlideUtils.setImageSrc(context,imageView, path);
          //  GlideUtils.setRoundRectImage(context,imageView, path,2);
        }
    }
}
