package com.agricluture.http.https;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;

import com.agricluture.view.DialogCustom;
import com.agriculture.R;
import com.zhouyou.http.subsciber.IProgressDialog;

/**
 * Created by zem on 2018/11/6.
 * 类描述:网络加载框
 */
public class MyProgressDialog implements IProgressDialog {

    public Context context;
    public String message = "加载中...";

    public MyProgressDialog(Context context, String message) {
        this.context = context;
        if (!TextUtils.isEmpty(message)) {
            this.message = message;
        }
    }

    @Override
    public Dialog getDialog() {
//        android.app.ProgressDialog dialog = new android.app.ProgressDialog(context);
//        dialog.setMessage(message);
        Dialog dialog = getDialog(context);
        return dialog;

    }

    public Dialog getDialog(Context context) {
        DialogCustom dialog = new DialogCustom(context, R.layout.dialog_loading);
        return dialog;
    }
}
