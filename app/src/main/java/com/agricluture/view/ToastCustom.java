package com.agricluture.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agriculture.R;


/**
 * Created by zem
 * on 2018/12/6 .
 */

public class ToastCustom {


    public static void showVerifyToast(Context context, String message,int img) {
        if(context!=null) {
            //加载Toast布局
            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_custom_root, null);
            //初始化布局控件
            TextView mTextView = toastRoot.findViewById(R.id.textContent);
            ImageView imageView = toastRoot.findViewById(R.id.iv_toast);
            //为控件设置属性
            mTextView.setText(message);
            imageView.setBackgroundResource(img);
            //Toast的初始化
            Toast toastStart = new Toast(context);
            //获取屏幕高度
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            //toastRoot  宽高只能依靠padding却无法用autolayout
            toastStart.setGravity(Gravity.CENTER, 0, 0);
            toastStart.setDuration(Toast.LENGTH_SHORT);
            toastStart.setView(toastRoot);
            toastStart.show();
        }
    }

    /**
     *
     * @param context
     * @param message
     * @param position  "top"顶部  "botton"底部
     */
    public static void showToast(Context context, String message, String position) {
        if(context!=null) {
            //加载Toast布局
            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_custom_root, null);
            //初始化布局控件
            TextView mTextView = toastRoot.findViewById(R.id.textContent);
            //为控件设置属性
            mTextView.setText(message);
            //Toast的初始化
            Toast toastStart = new Toast(context);
            //获取屏幕高度
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            //toastRoot  宽高只能依靠padding却无法用autolayout
            if (position.equals("top")) {
                toastStart.setGravity(Gravity.TOP, 0, 0);
            } else if (position.equals("botton")) {
                toastStart.setGravity(Gravity.BOTTOM, 0, 0);
            } else {
                toastStart.setGravity(Gravity.CENTER, 0, 0);
            }
            toastStart.setDuration(Toast.LENGTH_SHORT);
            toastStart.setView(toastRoot);
            toastStart.show();
        }
    }

//    public static void showToastTransparent(Context context, String message) {
//        if(context!=null) {
//            //加载Toast布局
//            View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_custom_transparent, null);
//            //初始化布局控件
//            TextView mTextView = toastRoot.findViewById(R.id.ContentText);
//            //为控件设置属性
//            mTextView.setText(message);
//            //Toast的初始化
//            Toast toastStart = new Toast(context);
//            //获取屏幕高度
////        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            //toastRoot  宽高只能依靠padding却无法用autolayout
//            toastStart.setGravity(Gravity.CENTER, 0, 0);
//            toastStart.setDuration(Toast.LENGTH_SHORT);
//            toastStart.setView(toastRoot);
//            toastStart.show();
//        }
//    }
}
