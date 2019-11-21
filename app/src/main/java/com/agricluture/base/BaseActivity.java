package com.agricluture.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agricluture.main.MyApplication;
import com.agricluture.utils.GlideUtils;
import com.agricluture.utils.InputMethodUtils;
import com.agricluture.utils.LeakFix;
import com.agricluture.view.NoDoubleClickListener;
import com.agriculture.R;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * @author zem
 * @date 2018/11/5
 * 类描述：基类activity
 */
public abstract class BaseActivity extends AppCompatActivity implements CustomAdapt {

    private Unbinder unbinder;

    protected LinearLayout back;
    protected Button titleRight;


    protected FragmentActivity activity;

    public ImmersionBar mImmersionBar;

    private boolean statusColor = true;

    protected int pageNumber = 1, pageSize = 10;

    public BaseActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        activity = this;
        if (ActivityUtils.getTopActivity() != null) {
            LogUtils.i("入栈:" + ActivityUtils.getTopActivity().getClass().getSimpleName());
        }
        setContentView(layoutViewId());
        unbinder = ButterKnife.bind(this);
        if (isRegisterEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setStatusColor();
        initViews();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Configuration configuration = MyApplication.getInstance().getResources().getConfiguration();
        if (configuration.fontScale != 1.0f) {
            configuration.fontScale = 1.0f;//设置字体的缩放比例
            MyApplication.getInstance().getResources().updateConfiguration(configuration, MyApplication.getInstance().getResources().getDisplayMetrics());
        }
        GlideUtils.resumeRequests(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GlideUtils.pauseRequests(this);
    }

    protected abstract int layoutViewId();

    protected abstract void initViews();

    protected abstract void initData();

    /**
     * 是否注册EventBus事件
     */
    protected boolean isRegisterEventBus() {
        return false;
    }


    public void setStatusColor(boolean isSet) {
        this.statusColor = isSet;
    }

    private void setStatusColor() {
        mImmersionBar = ImmersionBar.with(this);
        if (statusColor) {
            //white背景+深色文本颜色
            mImmersionBar.fitsSystemWindows(true).statusBarColor(statusBarColor()).keyboardEnable(true).statusBarDarkFont(false, 0.2f).init();
        } else {
            mImmersionBar.transparentStatusBar().init();
        }
    }

    protected int statusBarColor() {
        return R.color.color00B57F;
    }

    protected void initTitleBar() {
        initTitleBar(getString(R.string.home_page_top), null);
    }

    protected void initTitleBar(int titleResoureId) {
        initTitleBar(getString(titleResoureId), null);
    }

    protected void initTitleBar(String titleText) {
        initTitleBar(titleText, null);
    }

    protected void initTitleBar(int titleResoureId, int rightTextResourId) {
        initTitleBar(getString(titleResoureId), getString(rightTextResourId));
    }

    protected void initTitleBar(String titleText, String rightTextStr) {
        back = findViewById(R.id.title_back);
        if (back != null) {
            back.setOnClickListener(view -> onBackPressed());
        }

        TextView title = findViewById(R.id.title_title);
        if (title != null && !TextUtils.isEmpty(titleText)) {
            title.setText(titleText);
        }

        titleRight = findViewById(R.id.title_right);
        if (titleRight != null) {
            if (!TextUtils.isEmpty(rightTextStr)) {
                titleRight.setText(rightTextStr);
                titleRight.setVisibility(View.VISIBLE);
            } else {
                titleRight.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 连续点击
     */
    public boolean isFastTwiceClick(View view) {
        return NoDoubleClickListener.isFastTwiceClick(view);
    }

    /**
     * 修改系统字体大小影响APP字体大小
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 适配
     */
    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        if (null != unbinder) {
            unbinder.unbind();
        }
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        LeakFix.fixInputMethodManagerLeak(this);
        // 必须调用该方法，防止内存泄漏
        if (null != mImmersionBar) {
            mImmersionBar.destroy();
        }
        super.onDestroy();
        if (!ActivityUtils.isActivityExistsInStack(this)) {
            LogUtils.i("出栈:" + this.getClass().getSimpleName());
        }
    }

    @Override
    public void finish() {
        InputMethodUtils.hideSoftInput(this);
        super.finish();
    }


}
