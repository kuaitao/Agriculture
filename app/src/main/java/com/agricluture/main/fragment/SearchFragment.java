package com.agricluture.main.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agricluture.base.BaseFragment;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.bean.ProductNameBean;
import com.agricluture.searchall.fragment.DataSearchFragment;
import com.agricluture.searchall.fragment.TableSearchFragment;
import com.agricluture.utils.GsonUtil;
import com.agriculture.R;
import com.google.gson.Gson;
import com.zhouyou.http.exception.ApiException;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zem
 * @date 2019/9/21.
 * description：
 */
public class SearchFragment extends BaseFragment {

    @BindView(R.id.top_tv_system)
    TextView topTvSystem;
    @BindView(R.id.top_tv_receive)
    TextView topTvReceive;
    @BindView(R.id.top_ll_system)
    RelativeLayout topLlSystem;
    @BindView(R.id.top_ll_receive)
    RelativeLayout topLlReceive;

    private FragmentManager fm;
    private DataSearchFragment dataSearchFragment;
    private TableSearchFragment tableSearchFragment;

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initViews() {
        fm = getFragmentManager();
        showFragment(0);
    }
    private void showFragment(int position) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        reSetBackground();
        switch (position) {
            case 0:
                if (dataSearchFragment != null) {
                    ft.show(dataSearchFragment);
                } else {
                    dataSearchFragment = new DataSearchFragment();
                    ft.add(R.id.fl_search, dataSearchFragment);
                }
                topTvSystem.setTextColor(getResources().getColor(R.color.white));
                topLlSystem.setBackgroundResource(R.drawable.btn_change_c7);
                break;
            case 1:
                if (tableSearchFragment != null) {
                    ft.show(tableSearchFragment);
                } else {
                    tableSearchFragment = new TableSearchFragment();
                    ft.add(R.id.fl_search, tableSearchFragment);
                }
                topTvReceive.setTextColor(getResources().getColor(R.color.white));
                topLlReceive.setBackgroundResource(R.drawable.btn_change_c7);
                break;
            default:
                break;
        }
        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (dataSearchFragment != null) {
            ft.hide(dataSearchFragment);
        }
        if (tableSearchFragment != null) {
            ft.hide(tableSearchFragment);
        }
    }

    private void reSetBackground() {
        topTvSystem.setTextColor(getResources().getColor(R.color.maincolor));
        topTvReceive.setTextColor(getResources().getColor(R.color.maincolor));
        topLlSystem.setBackgroundResource(R.drawable.btn_num_c7);
        topLlReceive.setBackgroundResource(R.drawable.btn_num_c7);
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.top_ll_system, R.id.top_ll_receive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_ll_system:
                showFragment(0);
                break;
            case R.id.top_ll_receive:
                showFragment(1);
                break;
            default:
        }
    }
}
