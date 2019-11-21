package com.agricluture.infomessage.fragment;

import android.os.Bundle;
import android.view.View;

import com.agricluture.base.BaseFragment;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.infomessage.SystemInfoDetailActivity;
import com.agricluture.infomessage.SystemInfoEdDetailActivity;
import com.agricluture.infomessage.adapter.Infodapter;
import com.agricluture.infomessage.adapter.MyInfosAdapter;
import com.agricluture.infomessage.adapter.SendMesAdapter;
import com.agricluture.infomessage.adapter.SystemInfoAdapter;
import com.agricluture.infomessage.bean.MyMesBean;
import com.agricluture.infomessage.bean.SendMesBean;
import com.agricluture.infomessage.bean.SystemMesBean;
import com.agricluture.main.bean.HomeRwBean;
import com.agricluture.main.bean.TestBean;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.JumperUtils;
import com.agricluture.utils.Utils;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
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

/**
 * @author zem
 * @date 2019/9/24.
 * description：
 */
public class SysRecInfoFragment extends BaseFragment {
    private int pageNum = 1;
    private int pageSize = 20;
    @BindView(R.id.system_info_rlv_list)
    RecyclerView systemInfoRlvList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<SystemMesBean> systemMesBeanList = new ArrayList<>();
    private List<MyMesBean> myMesBeans = new ArrayList<>();
    private List<SendMesBean> sendMesBeanlist = new ArrayList<>();
    private SystemInfoAdapter systemInfoAdapter;
    private MyInfosAdapter myInfosAdapter;
    private SendMesAdapter sendMesAdapter;
    private int flag;

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_sysrecinfo_fragment;
    }

    @Override
    protected void initViews() {
        Bundle bundle = this.getArguments();
        flag = bundle.getInt("flag");

        initAdapter(systemMesBeanList, myMesBeans, sendMesBeanlist);
    }

    private void initAdapter(List<SystemMesBean> systemMesBeanList, List<MyMesBean> myMesBeans, List<SendMesBean> sendMesBeanlist) {

        if (flag == 1) {
            systemInfoRlvList.setLayoutManager(new LinearLayoutManager(activity));
            systemInfoAdapter = new SystemInfoAdapter(systemMesBeanList);
            systemInfoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                SystemMesBean status = (SystemMesBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.im_item_lly:
                        Bundle bundles = new Bundle();
                        bundles.putInt("flag", flag);
                        bundles.putString("noticeId", status.getId());
                        JumperUtils.JumpTo(activity, SystemInfoDetailActivity.class, bundles);

                        break;
                    default:
                }
            });
            systemInfoRlvList.setAdapter(systemInfoAdapter);
        } else if (flag == 2) {
            systemInfoRlvList.setLayoutManager(new LinearLayoutManager(activity));
            myInfosAdapter = new MyInfosAdapter(myMesBeans);
            myInfosAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                MyMesBean status = (MyMesBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.im_item_lly:
                        Bundle bundles = new Bundle();
                        bundles.putInt("flag", flag);
                        bundles.putString("noticeId", status.getId());
                        JumperUtils.JumpTo(activity, SystemInfoDetailActivity.class, bundles);

                        break;
                    default:
                }
            });
            systemInfoRlvList.setAdapter(myInfosAdapter);
        } else {
            systemInfoRlvList.setLayoutManager(new LinearLayoutManager(activity));
            sendMesAdapter = new SendMesAdapter(sendMesBeanlist);
            sendMesAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                SendMesBean status = (SendMesBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.im_item_lly:
                        Bundle bundles = new Bundle();
                        bundles.putString("noticeId", status.getId());
                        JumperUtils.JumpTo(activity, SystemInfoEdDetailActivity.class,bundles);

                        break;
                    default:
                }
            });
            systemInfoRlvList.setAdapter(sendMesAdapter);
        }

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

        if (flag == 1) {
            HttpUtils.getInstance().messageApi.postSystemInfo(pageNum, pageSize, new SimpleCallBack<HttpResultBean>() {
                @Override
                public void onSuccess(HttpResultBean s) {

                    if (!ObjectUtils.isEmpty(s.getData())) {
                        refreshLayout.finishLoadMore();
                        List<SystemMesBean> listCode = GsonUtil.getRealListFromT(s.getData(), SystemMesBean[].class);
                        if (listCode != null) {
                            if (pageNum == 1) {
                                systemMesBeanList.clear();
                                systemInfoRlvList.scrollToPosition(0);
                            }
                            systemMesBeanList.addAll(listCode);

                            Utils.rvNotifyItemRangeChanged(systemInfoAdapter, systemMesBeanList, listCode);

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
        } else if (flag == 2) {
            HttpUtils.getInstance().messageApi.postMyInfo(pageNum, pageSize, new SimpleCallBack<HttpResultBean>() {
                @Override
                public void onSuccess(HttpResultBean s) {
                    if (!ObjectUtils.isEmpty(s.getData())) {
                        refreshLayout.finishLoadMore();
                        List<MyMesBean> listCode = GsonUtil.getRealListFromT(s.getData(), MyMesBean[].class);
                        if (listCode != null) {
                            if (pageNum == 1) {
                                myMesBeans.clear();
                                systemInfoRlvList.scrollToPosition(0);
                            }
                            myMesBeans.addAll(listCode);

                            Utils.rvNotifyItemRangeChanged(myInfosAdapter, myMesBeans, listCode);

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
        } else {
            HttpUtils.getInstance().messageApi.postSendInfo(pageNum, pageSize, new SimpleCallBack<HttpResultBean>() {
                @Override
                public void onSuccess(HttpResultBean s) {
                    if (!ObjectUtils.isEmpty(s.getData())) {
                        refreshLayout.finishLoadMore();
                        List<SendMesBean> listCode =  GsonUtil.getRealListFromT(s.getData(), SendMesBean[].class);
                        if (listCode != null) {
                            if (pageNum == 1) {
                                sendMesBeanlist.clear();
                                systemInfoRlvList.scrollToPosition(0);
                            }
                            sendMesBeanlist.addAll(listCode);

                            Utils.rvNotifyItemRangeChanged(sendMesAdapter, sendMesBeanlist, listCode);

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
    }
}
