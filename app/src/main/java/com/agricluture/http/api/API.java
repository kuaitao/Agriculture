package com.agricluture.http.api;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.agricluture.http.https.MyProgressDialog;
import com.agricluture.http.https.SimpleCallBack;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import androidx.fragment.app.Fragment;
import io.reactivex.disposables.Disposable;
import okhttp3.Request;

/**
 * Created by zem
 * on 2018/12/6 .
 */


public class API<onCallBackDialog> {

  /*  public Disposable getByte(String url, HttpParams params,SimpleCallBack callBack) {
        ToByteConvertFactory toByteConvertFactory = new ToByteConvertFactory();
        if (EmptyUtils.isEmpty(params))
            return EasyHttp.get(url).addConverterFactory(toByteConvertFactory).execute(onCallBack(callBack));
        else
            return EasyHttp.get(url).addConverterFactory(toByteConvertFactory).params(params).timeStamp(true).execute(onCallBack(callBack));
    }*/

    protected Disposable postShort(String url, String json, SimpleCallBack callBack) {
        if (TextUtils.isEmpty(json)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackShort(callBack));
        } else {
            return EasyHttp.post(url).upJson(json).timeStamp(true).execute(onCallBackShort(callBack));

        }

    }

//    //上传文件
//    protected Disposable upFile(String url, String json,File file, SimpleCallBack callBack) {
//        if (TextUtils.isEmpty(json))
//            return EasyHttp.post(url).timeStamp(true).execute(onCallBackShort(callBack));
//        else
//            return EasyHttp.post(url).upJson(json).params("file1",file,uplistener).execute(onCallBackShort(callBack));
//
//    }
//
//    //上传进度
//    private UIProgressResponseCallBack  uplistener=new UIProgressResponseCallBack() {
//        @Override
//        public void onUIResponseProgress(long bytesRead, long contentLength, boolean done) {
//            int progress = (int) (bytesRead * 100 / contentLength);
//            if (done) {//完成
//            }
//        }
//    };


    protected Disposable post(String url, String json, SimpleCallBack callBack) {
        if (TextUtils.isEmpty(json)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.post(url).upJson(json).timeStamp(true).execute(onCallBack(callBack));
        }

    }

    protected Disposable postParams(String url, HttpParams params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.post(url).params(params).timeStamp(true).execute(onCallBack(callBack));
        }
    }

    protected Disposable postParams(Context context, String url, HttpParams params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackDialog(context, true, false, callBack));
        } else {
            return EasyHttp.post(url).params(params).timeStamp(true).execute(onCallBackDialog(context, true, false, callBack));
        }
    }

    protected Disposable postParams(Context context, String url, String cookie, HttpParams params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackDialog(context, true, false, callBack));
        } else {
            return EasyHttp.post(url).headers("cookie", cookie).params(params).timeStamp(true).execute(onCallBackDialog(context, true, false, callBack));
        }
    }


    protected Disposable postParamsFile(String url, HttpParams params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.post(url).params(params).timeStamp(true).execute(onCallBack(callBack));
        }

    }

    protected Disposable post(String baseUrl, String url, String json, SimpleCallBack callBack) {
        if (TextUtils.isEmpty(json)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.post(url).baseUrl(baseUrl).upJson(json).timeStamp(true).execute(onCallBack(callBack));
        }

    }

    protected Disposable postJsonObjDialog(Context context, boolean isShowProgress, boolean isCancel, String url, String obj, SimpleCallBack callBack) {
        WeakReference<Context> contextWeek = new WeakReference<Context>(context);
        if (obj == null) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        } else {
            return EasyHttp.post(url).upJson(obj).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        }

    }

    protected Disposable postJsonObjDialog(Context context, boolean isShowProgress, boolean isCancel, String url, HashMap obj, SimpleCallBack callBack) {
        WeakReference<Context> contextWeek = new WeakReference<Context>(context);
        if (obj == null) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        } else {
            return EasyHttp.post(url).upObject(obj).timeStamp(true).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        }
    }

    protected Disposable postJsonObj(String url, HashMap obj, SimpleCallBack callBack) {
        if (obj == null) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.post(url).upObject(obj).timeStamp(true).execute(onCallBack(callBack));
        }

    }

    protected Disposable postForm(String url, HttpParams params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.post(url).params(params).timeStamp(true).execute(onCallBack(callBack));
        }
    }

    private boolean alreadyHasAuthorizationHeader(Request originalRequest) {
        return !originalRequest.header("token").equals("");
    }

    protected Disposable postFormDialog(Context context, boolean isShowProgress, boolean isCancel, String url, HttpParams params, SimpleCallBack callBack) {
        WeakReference<Context> contextWeek = new WeakReference<Context>(context);
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        } else {
            return EasyHttp.post(url).params(params).timeStamp(true).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        }
    }

    protected Disposable get(String url, HttpParams params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.get(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.get(url).params(params).timeStamp(true).execute(onCallBack(callBack));
        }

    }

    protected Disposable get(String url, String params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.get(url).timeStamp(true).execute(onCallBack(callBack));
        } else {
            return EasyHttp.get(url + params).timeStamp(true).execute(onCallBack(callBack));
        }

    }

    protected Disposable postDialog(Context context, boolean isShowProgress, boolean isCancel, String url, String json, SimpleCallBack callBack) {
        WeakReference<Context> contextWeek = new WeakReference<Context>(context);
        if (TextUtils.isEmpty(json)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        } else {
            return EasyHttp.post(url).upJson(json).timeStamp(true).execute(onCallBackDialog(contextWeek.get(), isShowProgress, isCancel, callBack));
        }

    }

    protected Disposable getDialog(Context context, boolean isShowProgress, boolean isCancel, String url, HttpParams params, SimpleCallBack callBack) {
        if (ObjectUtils.isEmpty(params)) {
            return EasyHttp.get(url).timeStamp(true).execute(onCallBackDialog(context, isShowProgress, isCancel, callBack));
        } else {
            return EasyHttp.get(url).params(params).timeStamp(true).execute(onCallBackDialog(context, isShowProgress, isCancel, callBack));
        }

    }

    //error不用吐司(像敏感词接口)
    protected Disposable postDialogCn(Context context, boolean isShowProgress, boolean isCancel, String url, String json, SimpleCallBack callBack) {
        WeakReference<Context> contextWeek = new WeakReference<Context>(context);
        if (TextUtils.isEmpty(json)) {
            return EasyHttp.post(url).timeStamp(true).execute(onCallBackDialogCn(contextWeek.get(), isShowProgress, isCancel, callBack));
        } else {
            return EasyHttp.post(url).upJson(json).timeStamp(true).execute(onCallBackDialogCn(contextWeek.get(), isShowProgress, isCancel, callBack));
        }

    }

    private CallBack onCallBackShort(final SimpleCallBack callBack) {
        return new com.zhouyou.http.callback.SimpleCallBack<String>() {
            @Override
            public void onError(ApiException e) {
                if (assertIsDestroyed(callBack)) {
                    return;
                }
//                if (HttpUtils.getInstance().getEnvironmental()!= HttpUtils.EnvironmentalEnum.RELEASE){
//                    ToastUtils.toast("网络连接出现问题, 请稍候再试");
//                }
                callBack.onError(e);
            }

            @Override
            public void onSuccess(String s) {
                if (assertIsDestroyed(callBack)) {
                    LogUtils.d("assertIsDestroyed_result", "true");
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int status = jsonObject.getInt("status");

                    if (status == 1) {
                        callBack.onSuccess(new Gson().fromJson(s, callBack.getType()));
                    } else {
                        callBack.onFailShort(new Gson().fromJson(s, callBack.getType()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private CallBack onCallBack(final SimpleCallBack callBack) {


        return new com.zhouyou.http.callback.SimpleCallBack<String>() {
            @Override
            public void onError(ApiException e) {
                if (assertIsDestroyed(callBack)) {
                    return;
                }
                callBack.onError(e);
            }

            @Override
            public void onSuccess(String s) {
                if (assertIsDestroyed(callBack)) {
                    LogUtils.d("assertIsDestroyed_result", "true");
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String state = jsonObject.isNull("state") ? null : jsonObject.getString("state");
                    String msg = jsonObject.isNull("msg") ? null : jsonObject.getString("msg");
                    if ("200".equals(state)) {
                        callBack.onSuccess(new Gson().fromJson(s, callBack.getType()));
                    } else if ("100".equals(state)) {
                       // EventBus.getDefault().post(new OutBean(true));
                    } else {
                        callBack.onFail(new Gson().fromJson(s, callBack.getType()), state, msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    private CallBack onCallBackDialog(Context context, boolean isShowProgress, boolean isCancel, final SimpleCallBack callBack) {
        return new ProgressDialogCallBack<String>(new MyProgressDialog(context, ""), isShowProgress, isCancel) {
            @Override
            public void onError(ApiException e) {
                super.onError(e);
                if (assertIsDestroyed(callBack)) {
                    return;
                }
//                if (HttpUtils.getInstance().getEnvironmental()!= HttpUtils.EnvironmentalEnum.RELEASE){
                ToastUtils.showShort("网络连接出现问题, 请稍候再试");
//                }
                callBack.onError(e);
            }


            @Override
            public void onSuccess(String s) {
                if (assertIsDestroyed(callBack)) {
                    LogUtils.d("assertIsDestroyed_result", "true");
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String state = jsonObject.isNull("state") ? null : jsonObject.getString("state");
                    String msg = jsonObject.isNull("msg") ? null : jsonObject.getString("msg");
                    if (state.equals("1")) {
                        callBack.onSuccess(new Gson().fromJson(s, callBack.getType()));
                    } else {
                        callBack.onFail(new Gson().fromJson(s, callBack.getType()), state, msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    //error不用吐司(像敏感词接口)
    private CallBack onCallBackDialogCn(Context context, boolean isShowProgress, boolean isCancel, final SimpleCallBack callBack) {
        return new ProgressDialogCallBack<String>(new MyProgressDialog(context, ""), isShowProgress, isCancel) {
            @Override
            public void onError(ApiException e) {
                super.onError(e);
                if (assertIsDestroyed(callBack)) {
                    return;
                }
                callBack.onErrorCn(e);
            }


            @Override
            public void onSuccess(String s) {
                if (assertIsDestroyed(callBack)) {
                    LogUtils.d("assertIsDestroyed_result", "true");
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    boolean success = jsonObject.getBoolean("success");
                    Object errorCode = jsonObject.isNull("errorCode") ? null : jsonObject.get("errorCode");
                    Object errorMsg = jsonObject.isNull("errorMsg") ? null : jsonObject.get("errorMsg");
                    if (success) {
                        if (ObjectUtils.isEmpty(errorCode)) {
                            callBack.onSuccess(new Gson().fromJson(s, callBack.getType()));
                        } else {
                            callBack.onFail(new Gson().fromJson(s, callBack.getType()), errorCode, errorMsg);
                        }
                    } else {
                        callBack.onFail(new Gson().fromJson(s, callBack.getType()), errorCode, errorMsg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    private boolean assertIsDestroyed(SimpleCallBack callBack) {
        if (callBack.isSetCallerObject()) {
            Object object = callBack.getSimpleCallBackCallerObject();
            if (object == null) {
                LogUtils.d("assertIsDestroyed_obj", "");
                return true;
            }
            if (object instanceof Activity) {
                LogUtils.d("assertIsDestroyed_Activity", "");
                Activity activity = (Activity) object;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && (activity.isDestroyed() || activity.isFinishing())) {
                    return true;
                } else {
                    return activity == null || activity.isFinishing();
                }
            } else if (object instanceof Fragment) {
                LogUtils.d("assertIsDestroyed_fragment", "");
                Fragment fragment = (Fragment) object;
                return fragment.getView() == null || fragment.isRemoving() || fragment.isDetached();
            }
        }
        return false;
    }


}
