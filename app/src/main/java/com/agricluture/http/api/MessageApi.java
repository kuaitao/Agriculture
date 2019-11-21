package com.agricluture.http.api;

import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.login.LoginBean;
import com.blankj.utilcode.util.AppUtils;
import com.google.gson.JsonObject;

import io.reactivex.disposables.Disposable;

/**
 * @author zem
 * @date 2019/11/1.
 * description：
 */
public class MessageApi extends API {

    private void shareValue(JsonObject jsonObject){
        jsonObject.addProperty("device_type", "1");
        jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("userId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
    }

    /**
     * 系统消息详情
     *
     * @param callBack
     * @return
     */
    public Disposable postSystemInfoDetail(String noticeId,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/noticeDetail";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("noticeId", noticeId);
        return post(url, jsonObject.toString(), callBack);
    }

    /**
     * 收件箱详情
     *
     * @param callBack
     * @return
     */
    public Disposable postReciveDetail(String messageId,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/messageDetailSender";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("device_type", "1");
        jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("receiveId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
        jsonObject.addProperty("messageId", "96d2b1d8-0e39-4f62-bd1f-29f1a01a161b");
        return post(url, jsonObject.toString(), callBack);
    }

    /**
     * 发件箱详情
     *
     * @param callBack
     * @return
     */
    public Disposable postSendDetail(String messageId,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/messageDetailReceive";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("messageId", messageId);
        jsonObject.addProperty("sendId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
        return post(url, jsonObject.toString(), callBack);
    }

    /**
     * 获取系统消息
     * @param callBack
     * @return
     */
    public Disposable postSystemInfo(int pageindex,int pagesize,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/systemList";
        JsonObject jsonObject = new JsonObject();
        shareValue(jsonObject);
        jsonObject.addProperty("pageindex", pageindex);
        jsonObject.addProperty("pagesize", pagesize);
        return post(url, jsonObject.toString(), callBack);
    }
    private void shareValue2(JsonObject jsonObject) {
        jsonObject.addProperty("device_type", "1");
        //   jsonObject.addProperty("version", AppUtils.getAppVersionCode());
        jsonObject.addProperty("version", "0.0.1");
        jsonObject.addProperty("receiveId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
//        jsonObject.addProperty("Token", "759a4af2e8ad364d1ce741e901f59653ecd9fbd5");
    }
    /**
     * 获取我的消息
     * @param callBack
     * @return
     */
    public Disposable postMyInfo(int pageindex,int pagesize,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/myList";
        JsonObject jsonObject = new JsonObject();
        shareValue2(jsonObject);
        jsonObject.addProperty("pageindex", pageindex);
        jsonObject.addProperty("pagesize", pagesize);
        return post(url, jsonObject.toString(), callBack);
    }

    /**
     * 获取已发消息
     * @param callBack
     * @return
     */
    public Disposable postSendInfo(int pageindex,int pagesize,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/sentList";
        JsonObject jsonObject = new JsonObject();
        shareValue2(jsonObject);
        jsonObject.addProperty("pageindex", pageindex);
        jsonObject.addProperty("pagesize", pagesize);
        jsonObject.addProperty("sendId", "11D08F00-AE90-47A8-BB93-47F1FA4D5123");
        return post(url, jsonObject.toString(), callBack);
    }


    /**
     * 获取已发消息
     * @param callBack
     * @return
     */
    public Disposable postSendMessge(String tiltle,String contents,SimpleCallBack<HttpResultBean> callBack) {
        String url = "api/message/send";
        JsonObject jsonObject = new JsonObject();
        shareValue2(jsonObject);
        jsonObject.addProperty("messageTitle", tiltle);
        jsonObject.addProperty("description", contents);
        jsonObject.addProperty("reply", "0");


        jsonObject.addProperty("senderName", LoginBean.getInstance().getStrRealName());
        jsonObject.addProperty("sendId", LoginBean.getInstance().getId());
        return post(url, jsonObject.toString(), callBack);
    }
}
