package com.agricluture.infomessage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.agricluture.base.BaseActivity;
import com.agricluture.view.ToastCustom;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendInfoActivity extends BaseActivity {


    @BindView(R.id.send_et_title)
    EditText sendEtTitle;
    @BindView(R.id.send_et_content)
    EditText sendEtContent;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_send_info;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.sending_info);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_btn:

                String title = sendEtTitle.getText().toString();
                String contents = sendEtContent.getText().toString();
                if(ObjectUtils.isEmpty(title)){
                    ToastUtils.showShort("标题不能为空");
                    return;
                }

                if(ObjectUtils.isEmpty(contents)){
                    ToastUtils.showShort("内容不能为空");
                    return;
                }

                ToastCustom.showVerifyToast(activity, "发送成功", R.mipmap.verify_yes_icon);
                SendInfoActivity.this.finish();
                break;
            default:
        }
    }


}
