package com.agricluture.view;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import com.agriculture.R;


/**
 * 自定义dialog
 * Created by zem on 2018/1/18.
 */

public class DialogCustom extends Dialog {
    private int mViewId;
    private String mPosition="";
    private Context context;
    private Window window;
    private int keybordHeigh;
    public DialogCustom(Context context, int viewId, String position) {
        super(context, R.style.BottomDialog);
        this.context=context;
        this.mViewId=viewId;
        this.mPosition=position;
        initView();


    }
    public DialogCustom(Context context, int viewId, String position, int keybordHeigh) {
        super(context, R.style.BottomDialog);
        this.context=context;
        this.mViewId=viewId;
        this.mPosition=position;
        this.keybordHeigh=keybordHeigh;
        initView();


    }
    public DialogCustom(Context context, int viewId) {
        super(context, R.style.BottomDialogNobgs);
        this.context=context;
        this.mViewId=viewId;
        initView();
    }

    private void initView() {
        if(mPosition.equals("bottom")){
             window = this.getWindow();
            if (window != null) { //设置dialog的布局样式 让其位于底部
                window.setGravity(Gravity.BOTTOM);
                window.getDecorView().setPadding(0, 0, 0, 0);//Util.dip2px(context, 55)
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            }
        }


        if(mPosition.equals("keybord")){
            window = this.getWindow();
            if (window != null) { //设置dialog的布局样式 让其位于底部
                window.setGravity(Gravity.BOTTOM);
                window.getDecorView().setPadding(0, 0, 0, keybordHeigh);//Util.dip2px(context, 55)
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            }
        }
        if(mPosition.equals("center")){
            window = this.getWindow();
            if (window != null) { //设置dialog的布局样式 让其位于底部
                window.setGravity(Gravity.CENTER);
                window.getDecorView().setPadding(0, 0, 0, 0);//Util.dip2px(context, 55)
                window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            }
        }
        setContentView(mViewId);
    }
    public void setWidthHeight(float widths,float heighs){
        WindowManager wm = (WindowManager) context.getSystemService (Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int  mMaxItemheight = (int) (outMetrics.heightPixels * heighs);
        int  mMaxItemWidth = (int) (outMetrics.widthPixels * widths);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.height=mMaxItemheight;
        lp.width=mMaxItemWidth;
        this.getWindow().setAttributes(lp);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /* 触摸外部弹窗 */
        if (isOutOfBounds(getContext(), event)) {
            if(null!=myoutDialogClick){
                myoutDialogClick.myDialogClick();
            }
        }
        return super.onTouchEvent(event);
    }

    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = getWindow().getDecorView();
        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop))
                || (y > (decorView.getHeight() + slop));
    }
    private MyoutDialogClick myoutDialogClick;
    public interface  MyoutDialogClick{
       void myDialogClick();
    }
    public void setOutDialogClick(MyoutDialogClick myoutDialogClick){
        this.myoutDialogClick=myoutDialogClick;
    }
}