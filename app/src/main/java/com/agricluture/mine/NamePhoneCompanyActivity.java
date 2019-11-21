package com.agricluture.mine;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.agricluture.base.BaseActivity;
import com.agricluture.mine.bean.PersonalEvent;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NamePhoneCompanyActivity extends BaseActivity {


    @BindView(R.id.et_content)
    EditText etContent;

    /**
     * 1 名字 2 电话 3单位 4 地址
     */
    private int flag;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_name_phone_company;
    }

    @Override
    protected void initViews() {

        flag = getIntent().getIntExtra("flag",0);

        if(flag==1){
            initTitleBar(R.string.modify_name);
            etContent.setHint("请输入内容");
        }else if(flag==2){
            initTitleBar(R.string.modify_phone);
            etContent.setHint("请填写您的真实手机号码");
            etContent.setInputType(InputType.TYPE_CLASS_PHONE);
            etContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        }else if(flag==3){
            initTitleBar(R.string.modify_company);
            etContent.setHint("请填写您的单位名称");
        }else if(flag==4){
            initTitleBar(R.string.modify_address);
        }

    }

    @Override
    protected void initData() {

    }


    @OnClick({ R.id.clean_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clean_content:
                etContent.setText("");
                break;
                default:
        }
    }

    @Override
    protected void onDestroy() {
        String contents = etContent.getText().toString();
        if(!ObjectUtils.isEmpty(contents)){
            EventBus.getDefault().post(new PersonalEvent(flag,contents));
        }

        super.onDestroy();
    }
}
