package com.agricluture.mine;

import android.os.Bundle;
import android.widget.TextView;

import com.agricluture.base.BaseActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.mine.bean.AboutUs;
import com.agricluture.utils.GsonUtil;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.zhouyou.http.exception.ApiException;

import androidx.constraintlayout.solver.GoalRow;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity {


    @BindView(R.id.detail)
    TextView detail;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.about_us);
    }

    @Override
    protected void initData() {
        HttpUtils.getInstance().mineApi.postAboutUs(new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if(!ObjectUtils.isEmpty(s.getData())){
                    AboutUs aboutUs = GsonUtil.getRealBeanFromT(s.getData(),AboutUs.class);
                    detail.setText(aboutUs.getStrtypename());
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
