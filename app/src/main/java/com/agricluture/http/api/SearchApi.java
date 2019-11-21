package com.agricluture.http.api;

import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.SimpleCallBack;
import com.blankj.utilcode.util.AppUtils;
import com.google.gson.JsonObject;

import io.reactivex.disposables.Disposable;

/**
 * @author zem
 * @date 2019/10/29.
 * description：
 */
public class SearchApi  extends API  {

    private void shareValue(JsonObject jsonObject){
        jsonObject.addProperty("device_type", "1");
        jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("userId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
    }

    /**
     *  获取表名
     * @param callBack
     * @return
     */
    public Disposable postTableName(SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/common/tableNameList";
        return get(url, "", callBack);
    }
    /**
     *  获取产品名
     * @param callBack
     * @return
     */
    public Disposable postProductName(SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/common/productName";;
        return get(url, "", callBack);
    }
    /**
     *  获取期别
     * @param callBack
     * @return
     */
    public Disposable postDateSelect(String assignmentId,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/statistics/stage";;
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("assignmentId", assignmentId);
        return post(url, jsonObject.toString(), callBack);
    }




}
