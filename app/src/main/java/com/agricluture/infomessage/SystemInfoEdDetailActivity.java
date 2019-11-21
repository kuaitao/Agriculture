package com.agricluture.infomessage;

import android.os.Bundle;
import android.widget.TextView;

import com.agricluture.base.BaseActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.infomessage.bean.SendInfoDetailBean;
import com.agricluture.utils.GsonUtil;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.zhouyou.http.exception.ApiException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemInfoEdDetailActivity extends BaseActivity {

    @BindView(R.id.sys_info_tv_title)
    TextView sysInfoTvTitle;
    @BindView(R.id.sys_info_tv_time)
    TextView sysInfoTvTime;
    @BindView(R.id.sys_info_tv_address)
    TextView sysInfoTvAddress;
    @BindView(R.id.sys_info_tv_contents)
    TextView sysInfoTvContents;

    private String noticeId;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_system_info_ed_detail;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.infoed_detail);
        noticeId = getIntent().getStringExtra("noticeId");
    }

    @Override
    protected void initData() {
        HttpUtils.getInstance().messageApi.postSendDetail(noticeId, new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {

                if (!ObjectUtils.isEmpty(s.getData())) {
                    List<SendInfoDetailBean> sendInfoDetailBeanList = GsonUtil.getRealListFromT(s.getData(), SendInfoDetailBean[].class);
                    SendInfoDetailBean sendInfoDetailBean = sendInfoDetailBeanList.get(0);
                    sysInfoTvTitle.setText(sendInfoDetailBean.getStrmessagetitle());
                    sysInfoTvContents.setText(sendInfoDetailBean.getStrdescription());
                    sysInfoTvTime.setText("于"+sendInfoDetailBean.getCreateTime());
                    sysInfoTvAddress.setText(sendInfoDetailBean.getSenderName());
                }
            }

            @Override
            public void onError(ApiException t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
