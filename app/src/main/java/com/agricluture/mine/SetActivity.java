package com.agricluture.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.login.LoginActivity;
import com.agricluture.main.IntoActivity;
import com.agricluture.utils.JumperUtils;
import com.agriculture.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @BindView(R.id.set_st_info)
    Switch setStInfo;
    @Override
    protected int layoutViewId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.mine_set);

        setStInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                }else {
                }
            }
        });

    }

    @Override
    protected void initData() {

    }


    @OnClick({ R.id.set_rly_updata,R.id.set_rly_exits})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_rly_updata:
                Toast.makeText(activity, "已是最新版本！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.set_rly_exits:
//                Intent intent = new Intent(activity, LoginActivity.class);
//                activity.startActivity(intent);
               JumperUtils.JumpTo(activity,LoginActivity.class);
            //    activity.finish();
                break;


                default:
        }
    }
}
