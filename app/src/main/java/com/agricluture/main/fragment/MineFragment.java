package com.agricluture.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agricluture.base.BaseFragment;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.login.LoginBean;
import com.agricluture.main.bean.UserDetailBean;
import com.agricluture.mine.AboutUsActivity;
import com.agricluture.mine.AccountSafeActivity;
import com.agricluture.mine.PersonalInfoActivity;
import com.agricluture.mine.SetActivity;
import com.agricluture.mine.SuggestedComplaintActivity;
import com.agricluture.utils.GlideUtils;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.JumperUtils;
import com.agricluture.utils.Utils;
import com.agriculture.R;
import com.gcssloop.widget.RCImageView;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zem
 * @date 2019/9/21.
 * description：
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_iv_head)
    RCImageView mineIvHead;
    @BindView(R.id.mine_tv_linkman)
    TextView mineTvLinkman;
    @BindView(R.id.mine_tv_time)
    TextView mineTvTime;
    private UserDetailBean userDetailBean;

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViews() {

    }


    @Override
    protected void initData() {

        HttpUtils.getInstance().mineApi.postUserDetail(new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {

                userDetailBean = GsonUtil.getRealBeanFromT(s.getData(),UserDetailBean.class);
                GlideUtils.setImageSrc(activity,mineIvHead,userDetailBean.getStrpicturepath());
                mineTvLinkman.setText(userDetailBean.getStrrealname());
                mineTvTime.setText("上次登录："+LoginBean.getInstance().getTimlogintime());
            }

            @Override
            public void onError(ApiException t) {

            }
        });

    }

    @OnClick({R.id.mine_iv_head, R.id.mine_rly_accout, R.id.mine_rly_aboutus, R.id.mine_rly_tells, R.id.mine_rly_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_iv_head:
                Bundle bundle =new Bundle();
                bundle.putParcelable("bean",userDetailBean);
                JumperUtils.JumpTo(activity, PersonalInfoActivity.class,bundle);
                break;
            case R.id.mine_rly_accout:
                JumperUtils.JumpTo(activity, AccountSafeActivity.class);
                break;
            case R.id.mine_rly_aboutus:
                JumperUtils.JumpTo(activity, AboutUsActivity.class);
                break;
            case R.id.mine_rly_tells:
                JumperUtils.JumpTo(activity, SuggestedComplaintActivity.class);
                break;
            case R.id.mine_rly_set:
                JumperUtils.JumpTo(activity, SetActivity.class);
                break;
                default:
        }
    }

    public void reReshHead(String headURl){
        GlideUtils.setImageSrc(activity,mineIvHead,headURl);
    }
}
