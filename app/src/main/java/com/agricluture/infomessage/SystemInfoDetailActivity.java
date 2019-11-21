package com.agricluture.infomessage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.infomessage.bean.ReceveInfoDetail;
import com.agricluture.infomessage.bean.SystemInfoDetailBean;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.InputMethodUtils;
import com.agricluture.utils.Utils;
import com.agricluture.view.ToastCustom;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhouyou.http.exception.ApiException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemInfoDetailActivity extends BaseActivity {


    @BindView(R.id.bottom_layout)
    RelativeLayout bottomLayout;
    @BindView(R.id.sys_info_tv_title)
    TextView sysInfoTvTitle;
    @BindView(R.id.sys_info_tv_send)
    TextView sysInfoTvSend;
    @BindView(R.id.sys_info_tv_time)
    TextView sysInfoTvTime;
    @BindView(R.id.sys_info_tv_contents)
    TextView sysInfoTvContents;
    @BindView(R.id.sys_info_et_jianyi)
    EditText sysInfoEtJianyi;


    private int flag;
    private String noticeId;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_system_info_detail;
    }

    @Override
    protected void initViews() {
        flag = getIntent().getIntExtra("flag", 1);
        noticeId = getIntent().getStringExtra("noticeId");
        if (flag == 1) {
            initTitleBar(R.string.system_info);
            bottomLayout.setVisibility(View.GONE);
        } else {
            initTitleBar(R.string.info_detail);
            bottomLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void initData() {

        if (flag == 1) {
            HttpUtils.getInstance().messageApi.postSystemInfoDetail(noticeId, new SimpleCallBack<HttpResultBean>() {
                @Override
                public void onSuccess(HttpResultBean s) {
                    if (!ObjectUtils.isEmpty(s.getData())) {
                        List<SystemInfoDetailBean> systemInfoDetailBeanList = GsonUtil.getRealListFromT(s.getData(), SystemInfoDetailBean[].class);
                        SystemInfoDetailBean systemInfoDetailBean = systemInfoDetailBeanList.get(0);
                        sysInfoTvTitle.setText(systemInfoDetailBean.getStrnoticetitle());
                        sysInfoTvTime.setText("发件时间：" + Utils.dealDateFormatT(systemInfoDetailBean.getCreatetime()));
                        sysInfoTvContents.setText(systemInfoDetailBean.getStrdescription());
                        sysInfoTvSend.setText("发件人：系统消息");
                    }

                }

                @Override
                public void onError(ApiException t) {

                }
            });
        } else {
            HttpUtils.getInstance().messageApi.postReciveDetail(noticeId, new SimpleCallBack<HttpResultBean>() {
                @Override
                public void onSuccess(HttpResultBean s) {

                    if (!ObjectUtils.isEmpty(s.getData())) {
                        List<ReceveInfoDetail> receveInfoDetailList = GsonUtil.getRealListFromT(s.getData(), ReceveInfoDetail[].class);
                        ReceveInfoDetail receveInfoDetail = receveInfoDetailList.get(0);
                        sysInfoTvTitle.setText(receveInfoDetail.getStrmessagetitle());
                        sysInfoTvTime.setText("发件时间：" + Utils.dealDateFormatT(receveInfoDetail.getCreatetime()));
                        sysInfoTvContents.setText(receveInfoDetail.getStrdescription());
                        sysInfoTvSend.setText("发件人：系统消息");
                    }


                }

                @Override
                public void onError(ApiException t) {

                }
            });
        }

    }


    @OnClick({R.id.sys_info_btn_clean, R.id.sys_info_btn_send, R.id.layout_lly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sys_info_btn_clean:
                sysInfoEtJianyi.setText("");

                break;
            case R.id.sys_info_btn_send:

                String contents = sysInfoEtJianyi.getText().toString();

                if(ObjectUtils.isEmpty(contents)){
                    ToastUtils.showShort("内容不能为空");
                    return;
                }

                ToastCustom.showVerifyToast(activity, "发送成功", R.mipmap.verify_yes_icon);
                SystemInfoDetailActivity.this.finish();
                break;
            case R.id.layout_lly:
                InputMethodUtils.hideSoftInput(activity);
                break;
            default:
        }
    }


}
