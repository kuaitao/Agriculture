package com.agricluture.main;

import android.content.Intent;
import android.widget.TextView;

import com.agricluture.base.BaseActivity;
import com.agricluture.login.LoginSelectsActivity;
import com.agriculture.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class IntoActivity extends BaseActivity {

    @BindView(R.id.txttime)
    TextView txttime;
    private int recLen = 3;
    private Timer timer = new Timer();

    @Override
    protected int layoutViewId() {
        return R.layout.activity_into;
    }

    @Override
    protected void initViews() {
        timer.schedule(task, 1000, 1000);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    txttime.setText(recLen+"");
                    recLen--;
                    if (recLen == 0) {
                        timer.cancel();
                        Intent intent = new Intent(activity, LoginSelectsActivity.class);
                        startActivity(intent);
                        IntoActivity.this.finish();
                    }
                }
            });
        }
    };

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        if (null != task) {
            task.cancel();
            task = null;
        }
        super.onDestroy();
    }


}
