package com.agricluture.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.agricluture.base.BaseActivity;
import com.agriculture.R;

public class AccountSafeActivity extends BaseActivity {


    @Override
    protected int layoutViewId() {
        return R.layout.activity_account_safe;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.account_sale);
    }

    @Override
    protected void initData() {

    }
}
