package com.agricluture.http.api;

import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.SimpleCallBack;
import com.blankj.utilcode.util.AppUtils;
import com.google.gson.JsonObject;

import io.reactivex.disposables.Disposable;

/**
 * @author zem
 * @date 2019/11/1.
 * description：
 */
public class MineApi extends API{


    private void shareValue(JsonObject jsonObject){
        jsonObject.addProperty("device_type", "1");
        jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("userId", "1aa76488-5a1e-42c8-bd2d-8df047cbb7a2");
    }

    private void shareValue2(JsonObject jsonObject){
        jsonObject.addProperty("device_type", "1");
        jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("userId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
    }

    /**
     * 个人详细信息
     *
     * @param callBack
     * @return
     */
    public Disposable postUserDetail(SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/user/userDetail";
        JsonObject jsonObject = new JsonObject();
        shareValue2(jsonObject);
        return post(url, jsonObject.toString(), callBack);
    }

    /**
     * 投诉建议
     *
     * @param callBack
     * @return
     */
    public Disposable postSuggestedComplaint(String type,String content ,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/user/complaintSuggestion";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
        jsonObject.addProperty("type", type);
        jsonObject.addProperty("content", content);
        jsonObject.addProperty("title", "dddd");
        return post(url, jsonObject.toString(), callBack);
    }

    /**
     * 关于我们
     *
     * @param callBack
     * @return
     */
    public Disposable postAboutUs(SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/common/aboutUs";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
        return post(url, jsonObject.toString(), callBack);
    }
}
