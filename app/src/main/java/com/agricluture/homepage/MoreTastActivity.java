package com.agricluture.homepage;

import android.os.Bundle;

import com.agricluture.base.BaseActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.adapter.HomeRwAdapter;
import com.agricluture.main.bean.HomeRwBean;
import com.agricluture.main.bean.TestBean;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.JumperUtils;
import com.agricluture.utils.Utils;
import com.agricluture.view.DialogCustom;
import com.agriculture.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreTastActivity extends BaseActivity {
    private int pageNum = 1;
    private int pageSize = 20;
    @BindView(R.id.home_rlv_rwlist)
    RecyclerView homeRlvRwlist;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<HomeRwBean> rwList = new ArrayList<>();
    private HomeRwAdapter homeRwAdapter;
    private Bundle bundle;
    private int moreList = 2;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_more_tast;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.go_task);
        bundle = new Bundle();
        initAdapter(rwList);
    }
    private void initAdapter(List<HomeRwBean> rwlistCode) {

        homeRlvRwlist.setLayoutManager(new LinearLayoutManager(activity));
        homeRwAdapter = new HomeRwAdapter(rwlistCode,moreList);
        homeRwAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeRwBean status = (HomeRwBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.hrw_item_btn_up:
                    if ("02".equals(status.getState().trim())) {
                        showDialog();

                    } else {
                        JumperUtils.JumpTo(activity, PurchaseActivity.class);

                    }
                    break;

                default:
            }
        });
        homeRlvRwlist.setAdapter(homeRwAdapter);
        addRefreshListener();
    }
    //刷新加在
    private void addRefreshListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                myRefresh();

            }
        }).setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                initData();
            }
        });
    }

    public void myRefresh() {
        pageNum = 1;
        refreshLayout.setNoMoreData(false);
        initData();
        refreshLayout.finishRefresh(1500);
    }

    @Override
    protected void initData() {
        HttpUtils.getInstance().homeApi.postHome(pageNum,pageSize, new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if(null!=s.getData()){

                    refreshLayout.finishLoadMore();
                    List<HomeRwBean> listCode  = GsonUtil.getRealListFromT(s.getData(),HomeRwBean[].class);
                    if (listCode != null) {
                        if (pageNum == 1) {
                            rwList.clear();
                            homeRlvRwlist.scrollToPosition(0);
                        }
                        rwList.addAll(listCode);

                        Utils.rvNotifyItemRangeChanged(homeRwAdapter, rwList, listCode);

                        if (listCode.size() == 0) {
                            refreshLayout.finishLoadMore(true);
                        }
                    }

                }


            }

            @Override
            public void onError(ApiException t) {
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onFail(HttpResultBean s, Object errorCode, Object errorMsg) {
                super.onFail(s, errorCode, errorMsg);
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void showDialog(){
        DialogCustom dialog = new DialogCustom(activity, R.layout.select_verify_go,"bottom");

        dialog.findViewById(R.id.one).setOnClickListener(v -> {
            bundle.putInt("verify_flag",2);
            JumperUtils.JumpTo(activity, TastListActivity.class,bundle);
            dialog.dismiss();
        });
        dialog.findViewById(R.id.two).setOnClickListener(v -> {
            bundle.putInt("verify_flag",1);
            JumperUtils.JumpTo(activity, TastListActivity.class,bundle);
            dialog.dismiss();
        });
        dialog.findViewById(R.id.cancel).setOnClickListener(v -> {

            dialog.dismiss();
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
