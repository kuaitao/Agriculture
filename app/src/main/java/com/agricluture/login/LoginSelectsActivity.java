package com.agricluture.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.main.MainActivity;
import com.agricluture.utils.JumperUtils;
import com.agricluture.view.DialogCustom;
import com.agricluture.view.FingerprintCore;
import com.agricluture.view.FingerprintUtil;
import com.agricluture.view.KeyguardLockScreenManager;
import com.agriculture.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSelectsActivity extends BaseActivity {

    private FingerprintCore mFingerprintCore;
    private KeyguardLockScreenManager mKeyguardLockScreenManager;

    private Toast mToast;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected int layoutViewId() {
        return R.layout.activity_login_select;
    }

    @Override
    protected void initViews() {

        initFingerprintCore();
    }

    @Override
    protected void initData() {

    }

    private void initFingerprintCore() {
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mResultListener);
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
    }



//    @Override
//    public void onClick(View v) {
//        final int viewId = v.getId();
//        switch (viewId) {
//            case R.id.fingerprint_recognition_start:
//                startFingerprintRecognition();
//                break;
//            case R.id.fingerprint_recognition_cancel:
//                cancelFingerprintRecognition();
//                break;
//            case R.id.fingerprint_recognition_sys_unlock:
//                startFingerprintRecognitionUnlockScreen();
//                break;
//            case R.id.fingerprint_recognition_sys_setting:
//                enterSysFingerprintSettingPage();
//                break;
//        }
//    }

    private void enterSysFingerprintSettingPage() {
        FingerprintUtil.openFingerPrintSettingPage(this);
    }

    private void cancelFingerprintRecognition() {
        if (mFingerprintCore.isAuthenticating()) {
            mFingerprintCore.cancelAuthenticate();

        }
    }

    private void startFingerprintRecognitionUnlockScreen() {
        if (mKeyguardLockScreenManager == null) {
            return;
        }
        if (!mKeyguardLockScreenManager.isOpenLockScreenPwd()) {
            toastTipMsg(R.string.fingerprint_not_set_unlock_screen_pws);
            FingerprintUtil.openFingerPrintSettingPage(this);
            return;
        }
        mKeyguardLockScreenManager.showAuthenticationScreen(this);
    }

    /**
     * 开始指纹识别
     */
    private void startFingerprintRecognition() {
        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHasEnrolledFingerprints()) {

                if(null!=dialog&&dialog.isShowing()){
                    dialog.dismiss();
                }
                toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                FingerprintUtil.openFingerPrintSettingPage(this);
                return;
            }
            toastTipMsg(R.string.fingerprint_recognition_tip);

            if (mFingerprintCore.isAuthenticating()) {
                toastTipMsg(R.string.fingerprint_recognition_authenticating);
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
            toastTipMsg(R.string.fingerprint_recognition_not_support);

        }
    }


    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {

            toastTipMsg(R.string.fingerprint_recognition_success);
            if(null!=dialog&&dialog.isShowing()){
                dialog.dismiss();
            }

            Intent intent = new Intent(activity, MainActivity.class);
            startActivity(intent);
            LoginSelectsActivity.this.finish();


        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            toastTipMsg(R.string.fingerprint_recognition_failed);

        }

        @Override
        public void onAuthenticateError(int errMsgId) {

            toastTipMsg(R.string.fingerprint_recognition_error);
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KeyguardLockScreenManager.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                toastTipMsg(R.string.sys_pwd_recognition_success);
                Log.v("1111","===fffff");
            } else {
                toastTipMsg(R.string.sys_pwd_recognition_failed);
                Log.v("1111","===ffffff");
            }
        }
    }

    private void toastTipMsg(int messageId) {
        if (mToast == null) {
            mToast = Toast.makeText(this, messageId, Toast.LENGTH_SHORT);
        }
        mToast.setText(messageId);
        mToast.cancel();
        mHandler.removeCallbacks(mShowToastRunnable);
        mHandler.postDelayed(mShowToastRunnable, 0);
    }

    private void toastTipMsg(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        }
        mToast.setText(message);
        mToast.cancel();
        mHandler.removeCallbacks(mShowToastRunnable);
        mHandler.postDelayed(mShowToastRunnable, 200);
    }

    private Runnable mShowToastRunnable = new Runnable() {
        @Override
        public void run() {
            mToast.show();
        }
    };

    @Override
    protected void onDestroy() {
        if (mFingerprintCore != null) {
            mFingerprintCore.onDestroy();
            mFingerprintCore = null;
        }
        if (mKeyguardLockScreenManager != null) {
            mKeyguardLockScreenManager.onDestroy();
            mKeyguardLockScreenManager = null;
        }
        mResultListener = null;
        mShowToastRunnable = null;
        mToast = null;
        super.onDestroy();
    }


    @OnClick({R.id.login_zw, R.id.login_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_zw:
                showDialog();
                startFingerprintRecognition();
                break;
            case R.id.login_pw:
                JumperUtils.JumpTo2(activity, LoginActivity.class);
                break;
        }
    }
    private DialogCustom dialog;
    private void showDialog() {
        dialog = new DialogCustom(activity, R.layout.select_verify, "center");

        dialog.findViewById(R.id.btn_cancle).setOnClickListener(v -> {
            cancelFingerprintRecognition();
            dialog.dismiss();
        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
