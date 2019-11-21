package com.agricluture.main;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agricluture.base.BaseActivity;
import com.agricluture.main.fragment.DataFragment;
import com.agricluture.main.fragment.HomeFragment;
import com.agricluture.main.fragment.InfoFragment;
import com.agricluture.main.fragment.MineFragment;
import com.agricluture.main.fragment.SearchFragment;
import com.agricluture.mine.bean.HeadEvent;
import com.agricluture.view.BaseDialog;
import com.agriculture.R;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    private static final int OUT_TIME = 2000;
    @BindView(R.id.main_iv_home)
    ImageView mainIvHome;
    @BindView(R.id.main_tv_home)
    TextView mainTvHome;

    @BindView(R.id.main_lv_function)
    ImageView mainLvFunction;

    @BindView(R.id.main_lv_mine)
    ImageView mainLvMine;
    @BindView(R.id.main_tv_mine)
    TextView mainTvMine;

    @BindView(R.id.main_iv_terminal)
    ImageView mainIvTerminal;
    @BindView(R.id.main_tv_terminal)
    TextView mainTvTerminal;

    @BindView(R.id.main_iv_data)
    ImageView mainIvData;
    @BindView(R.id.main_tv_data)
    TextView mainTvData;
    @BindView(R.id.fl_title)
    RelativeLayout flTitle;
    @BindView(R.id.title_back)
    LinearLayout titleBack;

    /**
     * 首页
     */
    private HomeFragment homePageFragment;


    /**
     * 查询
     */
    private SearchFragment searchFragment;
    /**
     * 数据
     */
    private DataFragment dataFragment;


    /**
     * 消息
     */
    private InfoFragment infoFragment;


    /**
     * 我的
     */
    private MineFragment mineFragment;

    private FragmentManager fm;

    private DownloadBuilder builder;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        titleBack.setVisibility(View.GONE);
        fm = getSupportFragmentManager();
        showFragment(0);
        // getUpgrade();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    public void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        reSetBackground();
        switch (position) {
            case 0:
                if (homePageFragment != null) {
                    ft.show(homePageFragment);
                } else {
                    homePageFragment = new HomeFragment();
                    ft.add(R.id.fl_main, homePageFragment);
                }
                mainTvHome.setTextColor(getResources().getColor(R.color.maincolor));
                mainIvHome.setBackgroundResource(R.mipmap.main_home_sel);
                flTitle.setVisibility(View.GONE);
                break;
            case 1:
                if (searchFragment != null) {
                    ft.show(searchFragment);
                } else {
                    searchFragment = new SearchFragment();
                    ft.add(R.id.fl_main, searchFragment);
                }
                mainTvTerminal.setTextColor(getResources().getColor(R.color.maincolor));
                mainIvTerminal.setBackgroundResource(R.mipmap.main_terminal_sel);
                flTitle.setVisibility(View.VISIBLE);
                initTitleBar(R.string.search_title);
                break;
            case 2:
                if (dataFragment != null) {
                    ft.show(dataFragment);
                } else {
                    dataFragment = new DataFragment();
                    ft.add(R.id.fl_main, dataFragment);
                }
                flTitle.setVisibility(View.VISIBLE);
                initTitleBar(R.string.data_title);
                break;
            case 3:
                if (infoFragment != null) {
                    ft.show(infoFragment);
                } else {
                    infoFragment = new InfoFragment();
                    ft.add(R.id.fl_main, infoFragment);
                }
                mainTvData.setTextColor(getResources().getColor(R.color.maincolor));
                mainIvData.setBackgroundResource(R.mipmap.main_data_sel);
                flTitle.setVisibility(View.VISIBLE);
                initTitleBar(R.string.info_title);
                break;
            case 4:
                if (mineFragment != null) {
                    ft.show(mineFragment);
                } else {
                    mineFragment = new MineFragment();
                    ft.add(R.id.fl_main, mineFragment);
                }
                mainTvMine.setTextColor(getResources().getColor(R.color.maincolor));
                mainLvMine.setBackgroundResource(R.mipmap.main_mine_sel);
                flTitle.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (homePageFragment != null) {
            ft.hide(homePageFragment);
        }
        if (searchFragment != null) {
            ft.hide(searchFragment);
        }
        if (dataFragment != null) {
            ft.hide(dataFragment);
        }
        if (infoFragment != null) {
            ft.hide(infoFragment);
        }

        if (mineFragment != null) {
            ft.hide(mineFragment);
        }
    }

    private void reSetBackground() {
        mainTvHome.setTextColor(getResources().getColor(R.color.colorababab));
        mainTvMine.setTextColor(getResources().getColor(R.color.colorababab));
        mainTvTerminal.setTextColor(getResources().getColor(R.color.colorababab));
        mainTvData.setTextColor(getResources().getColor(R.color.colorababab));
        mainIvHome.setBackgroundResource(R.mipmap.main_home);
        mainLvMine.setBackgroundResource(R.mipmap.main_mine);
        mainLvFunction.setBackgroundResource(R.mipmap.main_function);
        mainIvTerminal.setBackgroundResource(R.mipmap.main_terminal);
        mainIvData.setBackgroundResource(R.mipmap.main_data);
    }

    @OnClick({R.id.main_ll_home, R.id.main_ll_function, R.id.main_ll_mine, R.id.main_ll_terminal, R.id.main_ll_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_ll_home:
                showFragment(0);
                break;
            case R.id.main_ll_terminal:
                showFragment(1);
                break;
            case R.id.main_ll_function:
                showFragment(2);
                break;
            case R.id.main_ll_data:
                showFragment(3);
                break;
            case R.id.main_ll_mine:
                showFragment(4);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {

    }

    /**
     * 双击退出计时
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > OUT_TIME) {
                ToastUtils.showShort("再次点击将退出");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private UIData crateUIData(String title, String contents, String urls) {

        UIData uiData = UIData.create();
        uiData.setTitle(title);
        uiData.setDownloadUrl(urls);
        uiData.setContent(contents);
        return uiData;
    }

    /**
     * 强制更新操作
     * 通常关闭整个activity所有界面，这里方便测试直接关闭当前activity
     */
    private void forceUpdate() {
        finish();
    }

    private CustomVersionDialogListener createCustomDialogTwo() {
        return (context, versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_two_layout);
            TextView textView = baseDialog.findViewById(R.id.tv_msg);
            TextView textView2 = baseDialog.findViewById(R.id.tv_title);
            textView.setText(versionBundle.getContent());
            textView2.setText(versionBundle.getTitle());
            baseDialog.setCanceledOnTouchOutside(true);
            return baseDialog;
        };
    }

    /**
     * 自定义下载中对话框，下载中会连续回调此方法 updateUI
     * 务必用库传回来的context 实例化你的dialog
     *
     * @return
     */
    private CustomDownloadingDialogListener createCustomDownloadingDialog() {
        return new CustomDownloadingDialogListener() {
            @Override
            public Dialog getCustomDownloadingDialog(Context context, int progress, UIData versionBundle) {
                BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_download_layout);
                return baseDialog;
            }

            @Override
            public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
                TextView tvProgress = dialog.findViewById(R.id.tv_progress);
                ProgressBar progressBar = dialog.findViewById(R.id.pb);
                progressBar.setProgress(progress);
                tvProgress.setText(getString(R.string.versionchecklib_progress, progress));
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(HeadEvent headEvent) {
        if(headEvent.isGet()){
            if (mineFragment != null) {
                mineFragment.reReshHead(headEvent.getHeadString());
            }
        }
    }
}
