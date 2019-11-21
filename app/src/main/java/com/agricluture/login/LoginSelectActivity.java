package com.agricluture.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.utils.JumperUtils;
import com.agricluture.view.DialogCustom;
import com.agricluture.view.FingerprintCore;
import com.agricluture.view.FingerprintUtil;
import com.agricluture.view.KeyguardLockScreenManager;
import com.agriculture.R;

import butterknife.OnClick;

public class LoginSelectActivity extends BaseActivity {



    private FingerprintCore mFingerprintCore;
    private KeyguardLockScreenManager mKeyguardLockScreenManager;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Toast mToast;

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
    @OnClick({R.id.login_zw, R.id.login_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_zw:
               // showDialog();
                startFingerprintRecognition();
                break;
            case R.id.login_pw:
                JumperUtils.JumpTo2(activity, LoginActivity.class);
                break;
            default:
        }
    }
    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            Log.v("","指纹识别成功");

        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            Log.v("","指纹识别识别，请重试");

        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            Log.v("","指纹识别错误，等待几秒之后再重试");
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };
    private DialogCustom dialog;
    private void showDialog() {
         dialog = new DialogCustom(activity, R.layout.select_verify, "center");

        dialog.findViewById(R.id.btn_cancle).setOnClickListener(v -> {

            dialog.dismiss();
        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KeyguardLockScreenManager.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                Log.v("", "指纹识别成功");
            } else {
                Log.v("", "指纹识别识别");
            }
        }
    }
    private void enterSysFingerprintSettingPage() {
        FingerprintUtil.openFingerPrintSettingPage(this);
    }

    private void cancelFingerprintRecognition() {
        if (mFingerprintCore.isAuthenticating()) {
            mFingerprintCore.cancelAuthenticate();
            //resetGuideViewState();
        }
    }

    private void startFingerprintRecognitionUnlockScreen() {
        if (mKeyguardLockScreenManager == null) {
            return;
        }
        if (!mKeyguardLockScreenManager.isOpenLockScreenPwd()) {
            //toastTipMsg(R.string.fingerprint_not_set_unlock_screen_pws);
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
                toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                FingerprintUtil.openFingerPrintSettingPage(this);
                return;
            }
            toastTipMsg(R.string.fingerprint_recognition_tip);
          //  mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
           // mFingerGuideImg.setBackgroundResource(R.drawable.fingerprint_guide);
            if (mFingerprintCore.isAuthenticating()) {
            //    toastTipMsg(R.string.fingerprint_recognition_authenticating);
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
            toastTipMsg(R.string.fingerprint_recognition_not_support);
          //  mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
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
}
