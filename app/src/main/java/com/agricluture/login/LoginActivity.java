package com.agricluture.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.agricluture.base.BaseActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.MainActivity;
import com.agricluture.utils.GlideUtils;
import com.agricluture.utils.GsonUtil;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.zhouyou.http.exception.ApiException;

import java.util.UUID;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_edt_user)
    EditText loginEdtUser;
    @BindView(R.id.login_edt_pass)
    EditText loginEdtPass;
    @BindView(R.id.login_edt_yzm)
    EditText loginEdtYzm;
    @BindView(R.id.login_iv_yzm)
    ImageView loginIvYzm;

    private String username;
    private String password;
    private String yzmText;



    @Override
    protected int layoutViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
//        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("username"))) {
//            loginEdtUser.setText(SPUtils.getInstance().getString("username"));
//            loginEdtPass.setText(SPUtils.getInstance().getString("password"));
//
//            loginEdtUser.setSelection(SPUtils.getInstance().getString("username").length());
//            loginEdtPass.setSelection(SPUtils.getInstance().getString("password").length());
//        }
    }

    private boolean verifyAccountPassword() {

        username = loginEdtUser.getText().toString().trim();
        password = loginEdtPass.getText().toString().trim();
        yzmText = loginEdtYzm.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort("请输入账号");
            return false;
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码");
            return false;
        } else if (TextUtils.isEmpty(yzmText)) {
            ToastUtils.showShort("请输入验证码");
            return false;
        }
        return true;
    }

    @Override
    protected void initData() {

        GlideUtils.setImageSrc(activity,loginIvYzm,"http://farm.ymforever.com:6996/api/authorize/getImage");
    }


    @OnClick({R.id.login_iv_yzm, R.id.login_btn_to})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_iv_yzm:

                GlideUtils.setImageSrc_clean(activity,loginIvYzm,"http://farm.ymforever.com:6996/api/authorize/getImage");
                break;
            case R.id.login_btn_to:

                if (verifyAccountPassword()) {

                    if (!username.equals("admin")) {
                        ToastUtils.showShort("账户错误");
                        return;
                    }
                    if (!password.equals("q1w2e3")) {
                        ToastUtils.showShort("密码错误");
                        return;
                    }
//                    if (!yzmText.equals("1598")) {
//                        ToastUtils.showShort("验证码错误");
//                        return;
//                    }


                    HttpUtils.getInstance().homeApi.postLogin(username, password, new SimpleCallBack<HttpResultBean>() {
                        @Override
                        public void onSuccess(HttpResultBean s) {

                            if(!ObjectUtils.isEmpty(s.getData())){

                                LoginBean loginBean = GsonUtil.getRealBeanFromT(s.getData(),LoginBean.class);

                                LoginBean.getInstance().setId(loginBean.getId());
                                LoginBean.getInstance().setIntconfidentiallevel(loginBean.getIntconfidentiallevel());
                                LoginBean.getInstance().setTimlogintime(loginBean.getTimlogintime());
                                LoginBean.getInstance().setStrRealName(loginBean.getStrRealName());
                                LoginBean.getInstance().setOrganizationId(loginBean.getOrganizationId());
                                LoginBean.getInstance().setOrganizationNO(loginBean.getOrganizationNO());



                                Intent intent = new Intent(activity, MainActivity.class);
                                startActivity(intent);
                                LoginActivity.this.finish();
                            }


                        }

                        @Override
                        public void onError(ApiException t) {

                        }
                    });

                }

                break;
            default:
        }
    }
}
