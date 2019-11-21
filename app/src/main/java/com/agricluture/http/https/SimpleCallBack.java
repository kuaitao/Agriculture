package com.agricluture.http.https;


import com.blankj.utilcode.util.ToastUtils;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.Utils;

import java.lang.reflect.Type;

/**
 * Created by zem
 * on 2018/12/6 .
 * 类描述:网络回调
 */
public abstract class SimpleCallBack<T> {
    private Object simpleCallBackCallerObject;
    private boolean setCallerObject;

    public SimpleCallBack() {
    }

    /**
     * @param obj activity or fragment（viewPage中超过三个fragment且没设置缓存所有fragment时必须传fragment）   防止销毁后网络回调导致崩溃
     */
    public SimpleCallBack(Object obj) {
        this.simpleCallBackCallerObject = obj;
        setCallerObject = true;
    }

    public Object getSimpleCallBackCallerObject() {
        return simpleCallBackCallerObject;
    }

    public boolean isSetCallerObject() {
        return setCallerObject;
    }

    public Type getType() {//获取需要解析的泛型T类型
        return Utils.findNeedClass(getClass());
    }

    public abstract void onSuccess(T s);

    public void onFail(T s, Object errorCode, Object errorMsg) {
        if (errorCode instanceof String) {
            if ("100".equals(errorCode)) {
//            if (HttpUtils.getInstance().getEnvironmental()!= HttpUtils.EnvironmentalEnum.RELEASE){
                ToastUtils.showShort(errorMsg + "");
            } else if ("401".equals(errorCode)) {

            } else {
                if(null!=errorMsg){
                    ToastUtils.showLong((String) errorMsg);
                }

            }
        } else {
            ToastUtils.showShort("网络错误");
        }
    }

    public void onFailShort(T s) {
    }

    public abstract void onError(ApiException t);

    public void onErrorCn(ApiException t) {//出错后不吐斯,重写此方法即可
        ToastUtils.showShort("网络连接出现问题, 请稍候再试");
    }
}