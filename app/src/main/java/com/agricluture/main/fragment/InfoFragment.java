package com.agricluture.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agricluture.base.BaseFragment;
import com.agricluture.infomessage.SendInfoActivity;
import com.agricluture.infomessage.fragment.SysRecInfoFragment;
import com.agricluture.utils.JumperUtils;
import com.agriculture.R;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zem
 * @date 2019/9/21.
 * description：
 */
public class InfoFragment extends BaseFragment {


    @BindView(R.id.top_tv_system)
    TextView topTvSystem;
    @BindView(R.id.top_tv_receive)
    TextView topTvReceive;
    @BindView(R.id.top_tv_send)
    TextView topTvSend;
    @BindView(R.id.top_ll_system)
    RelativeLayout topLlSystem;
    @BindView(R.id.top_ll_receive)
    RelativeLayout topLlReceive;
    @BindView(R.id.top_ll_send)
    RelativeLayout topLlSend;

    private FragmentManager fm;
    private SysRecInfoFragment sysInfoFragment, recInfoFragment,sendInfoFragment;
    private Bundle bundle;

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initViews() {
        bundle = new Bundle();
        fm = getFragmentManager();
        showFragment(0);
    }

    private void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        reSetBackground();
        switch (position) {
            case 0:
                if (sysInfoFragment != null) {
                    ft.show(sysInfoFragment);
                } else {
                    sysInfoFragment = new SysRecInfoFragment();
                    bundle.putInt("flag",1);
                    sysInfoFragment.setArguments(bundle);
                    ft.add(R.id.fl_info, sysInfoFragment);
                }
                topTvSystem.setTextColor(getResources().getColor(R.color.white));
                topLlSystem.setBackgroundResource(R.drawable.btn_change_c7);
                break;
            case 1:
                if (recInfoFragment != null) {
                    ft.show(recInfoFragment);
                } else {
                    recInfoFragment = new SysRecInfoFragment();
                    bundle.putInt("flag",2);
                    recInfoFragment.setArguments(bundle);
                    ft.add(R.id.fl_info, recInfoFragment);
                }
                topTvReceive.setTextColor(getResources().getColor(R.color.white));
                topLlReceive.setBackgroundResource(R.drawable.btn_change_c7);
                break;
            case 2:
                if (sendInfoFragment != null) {
                    ft.show(sendInfoFragment);
                } else {
                    sendInfoFragment = new SysRecInfoFragment();
                    bundle.putInt("flag",3);
                    sendInfoFragment.setArguments(bundle);
                    ft.add(R.id.fl_info, sendInfoFragment);
                }
                topTvSend.setTextColor(getResources().getColor(R.color.white));
                topLlSend.setBackgroundResource(R.drawable.btn_change_c7);
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (sysInfoFragment != null) {
            ft.hide(sysInfoFragment);
        }
        if (recInfoFragment != null) {
            ft.hide(recInfoFragment);
        }
        if (sendInfoFragment != null) {
            ft.hide(sendInfoFragment);
        }
    }

    private void reSetBackground() {
        topTvSystem.setTextColor(getResources().getColor(R.color.maincolor));
        topTvReceive.setTextColor(getResources().getColor(R.color.maincolor));
        topTvSend.setTextColor(getResources().getColor(R.color.maincolor));
        topLlSystem.setBackgroundResource(R.drawable.btn_num_c7);
        topLlReceive.setBackgroundResource(R.drawable.btn_num_c7);
        topLlSend.setBackgroundResource(R.drawable.btn_num_c7);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.top_ll_system, R.id.top_ll_receive, R.id.top_ll_send,R.id.search_iv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_ll_system:
                showFragment(0);
                break;
            case R.id.top_ll_receive:
                showFragment(1);
                break;
            case R.id.top_ll_send:
                showFragment(2);
                break;
            case R.id.search_iv_send:
                JumperUtils.JumpTo(activity, SendInfoActivity.class);
                break;
            default:
        }
    }
}
