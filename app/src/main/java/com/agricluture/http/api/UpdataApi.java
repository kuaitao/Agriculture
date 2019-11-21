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
public class UpdataApi  extends API  {

    private void shareValue(JsonObject jsonObject){
        jsonObject.addProperty("device_type", "1");
        jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("userId", "1aa76488-5a1e-42c8-bd2d-8df047cbb7a2");
    }
    /**
     * 取上报情况统计
     * @param callBack
     * @return
     */
    public Disposable posUpdataAll(int pageindex, int pagesize, SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/statistics/reporting";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("pageindex", pageindex);
        jsonObject.addProperty("pagesize", pagesize);

        return post(url, jsonObject.toString(), callBack);
    }

}
