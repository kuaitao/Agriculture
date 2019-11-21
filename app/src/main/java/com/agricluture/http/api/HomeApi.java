package com.agricluture.http.api;

import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.SimpleCallBack;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.JsonObject;
import com.zhouyou.http.model.HttpParams;

import io.reactivex.disposables.Disposable;

/**
 * @author zem
 * @date 2019/10/23.
 * description：
 */
public class HomeApi extends API {



    /**
     * 登陆
     *
     * @param callBack
     * @return
     */
    public Disposable postLogin(String userName,String password,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/authorize/login";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", userName);
        jsonObject.addProperty("password", password);
        return post(url, jsonObject.toString(), callBack);
    }
    /**
     * 广告
     *
     * @param callBack
     * @return
     */
    public Disposable postBanner(SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/business/rotationChart";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        return post(url, jsonObject.toString(), callBack);
    }

    /**
     * 我的任务
     *
     * @param callBack
     * @return
     */
    public Disposable postHome(int pageindex, int pagesize, SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/business/assignmentList";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("pageindex", pageindex);
        jsonObject.addProperty("pagesize", pagesize);
        return post(url, jsonObject.toString(), callBack);

    }

    /**
     * 我的信息
     *
     * @param callBack
     * @return
     */
    public Disposable postMyInfo(int pageindex, int pagesize, SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/myList";
        JsonObject jsonObject = new JsonObject();
        shareValue2(jsonObject);
        jsonObject.addProperty("pageindex", pageindex);
        jsonObject.addProperty("pagesize", pagesize);
        return post(url, jsonObject.toString(), callBack);
    }


    private void shareValue(JsonObject jsonObject) {
        jsonObject.addProperty("device_type", "1");
     //   jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("version", "0.0.1");
        jsonObject.addProperty("userId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
//        jsonObject.addProperty("Token", "759a4af2e8ad364d1ce741e901f59653ecd9fbd5");
    }

    private void shareValue2(JsonObject jsonObject) {
        jsonObject.addProperty("device_type", "1");
        //   jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("version", "0.0.1");
        jsonObject.addProperty("receiveId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
//        jsonObject.addProperty("Token", "759a4af2e8ad364d1ce741e901f59653ecd9fbd5");
    }
    /**
     *
     *
     * @param callBack
     * @return
     */
    public Disposable postOrganization(SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/common/organization";
        return post(url, "", callBack);
    }

    /**
     * 我的信息
     *
     * @param callBack
     * @return
     */
    public Disposable postCollectionData(String assignmentId, SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/business/dataCollection";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("assignmentId", assignmentId);
        return post(url, jsonObject.toString(), callBack);
    }
    /**
     * 未审核列表
     *
     * @param callBack
     * @return
     */
    public Disposable postNoTast(String assignmentId, String stage, int pageindex, int pagesize,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/business/auditList";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("assignmentId", assignmentId);
        jsonObject.addProperty("stage", stage);
        jsonObject.addProperty("pageindex", pageindex);
        jsonObject.addProperty("pagesize", pagesize);
        return post(url, jsonObject.toString(), callBack);
    }
}