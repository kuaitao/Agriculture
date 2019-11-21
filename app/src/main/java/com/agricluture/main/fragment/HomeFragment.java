package com.agricluture.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.agricluture.base.BaseFragment;
import com.agricluture.homepage.MoreTastActivity;
import com.agricluture.homepage.PurchaseActivity;
import com.agricluture.homepage.TastListActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.infomessage.SystemInfoDetailActivity;
import com.agricluture.main.MainActivity;
import com.agricluture.main.adapter.HomeInfoAdapter;
import com.agricluture.main.adapter.HomeRwAdapter;
import com.agricluture.main.bean.BannerBean;
import com.agricluture.main.bean.HomeInfoBean;
import com.agricluture.main.bean.HomeRwBean;
import com.agricluture.main.bean.TestBean;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.JumperUtils;
import com.agricluture.view.BannerImageLoader;
import com.agricluture.view.DialogCustom;
import com.agricluture.view.NoDoubleClickListener;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.https.HttpsUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zem
 * @date 2019/9/21.
 * description：
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.home_rlv_rwlist)
    RecyclerView homeRlvRwlist;
    @BindView(R.id.home_rlv_infolist)
    RecyclerView homeRlvInfolist;

    private HomeRwAdapter homeRwAdapter;
    private HomeInfoAdapter homeInfoAdapter;

    private Bundle bundle;
    private int homeList = 1;

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_honmepage;
    }

    @Override
    protected void initViews() {
        bundle = new Bundle();
        homeBanner.setFocusable(true);
        homeBanner.setFocusableInTouchMode(true);

    }
    //顶部广告
    public void showBanner(List<BannerBean>  bannerBeanList) {
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i <bannerBeanList.size() ; i++) {
            imageUrls.add(HttpUtils.getInstance().getBaseUrl()+bannerBeanList.get(i).getStrurl());
        }

        homeBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (NoDoubleClickListener.isFastTwiceClick(homeBanner)) {
                    return;
                } else {

                }
            }
        });
        homeBanner.setImages(imageUrls).setIndicatorGravity(BannerConfig.CENTER).setImageLoader(new BannerImageLoader(getActivity(), BannerImageLoader.RECT_IMAGE)).start();
    }

    private void initAdapterInfo(List<HomeInfoBean> infoListCode) {

        homeRlvInfolist.setLayoutManager(new LinearLayoutManager(activity) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        homeInfoAdapter = new HomeInfoAdapter(infoListCode);
        homeInfoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.hif_item_lly:
                    bundle.putInt("flag", 2);
                    JumperUtils.JumpTo(activity, SystemInfoDetailActivity.class, bundle);
                    break;
                default:
            }
        });
        homeRlvInfolist.setAdapter(homeInfoAdapter);
    }

    private void initAdapterRW(List<HomeRwBean> dataBeanList) {

        homeRlvRwlist.setLayoutManager(new LinearLayoutManager(activity));
        homeRwAdapter = new HomeRwAdapter(dataBeanList, homeList);
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
                case R.id.hrw_item_rly_show:
                    if (status.isShow()) {
                        status.setShow(false);
                    } else {
                        status.setShow(true);
                    }
                    homeRwAdapter.notifyItemChanged(position);
                    break;
                default:
            }
        });
        homeRlvRwlist.setAdapter(homeRwAdapter);


    }

    @Override
    protected void initData() {
        HttpUtils.getInstance().homeApi.postBanner(new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if (!ObjectUtils.isEmpty(s.getData())) {
                   List<BannerBean>  bannerBeanList =GsonUtil.getRealListFromT(s.getData(),BannerBean[].class);
                    showBanner(bannerBeanList);
                }

            }

            @Override
            public void onError(ApiException t) {

            }
        });
        HttpUtils.getInstance().homeApi.postHome(1, 20, new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if (!ObjectUtils.isEmpty(s.getData())) {
                    List<HomeRwBean> homeRwBeanList = GsonUtil.getRealListFromT(s.getData(), HomeRwBean[].class);
                    initAdapterRW(homeRwBeanList);

                }

            }

            @Override
            public void onError(ApiException t) {

            }
        });
        HttpUtils.getInstance().homeApi.postMyInfo( 1, 20, new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if (!ObjectUtils.isEmpty(s.getData())){
                    List<HomeInfoBean> homeInfoBeanList = GsonUtil.getRealListFromT(s.getData(), HomeInfoBean[].class);
                    initAdapterInfo(homeInfoBeanList);
                }
            }
            @Override
            public void onError(ApiException t) {
            }
        });

//        HttpUtils.getInstance().homeApi.postOrganization(new SimpleCallBack<HttpResultBean>() {
//            @Override
//            public void onSuccess(HttpResultBean s) {
//
//            }
//
//            @Override
//            public void onError(ApiException t) {
//
//            }
//        });
    }

    @OnClick({R.id.home_lly_more, R.id.home_lly_info_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_lly_more:
                JumperUtils.JumpTo(activity, MoreTastActivity.class);
                break;
            case R.id.home_lly_info_more:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showFragment(3);
                break;
            default:
        }

    }

    private void showDialog() {
        DialogCustom dialog = new DialogCustom(activity, R.layout.select_verify_go, "bottom");

        dialog.findViewById(R.id.one).setOnClickListener(v -> {
            bundle.putInt("verify_flag", 2);
            JumperUtils.JumpTo(activity, TastListActivity.class, bundle);
            dialog.dismiss();
        });
        dialog.findViewById(R.id.two).setOnClickListener(v -> {
            bundle.putInt("verify_flag", 1);
            JumperUtils.JumpTo(activity, TastListActivity.class, bundle);
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
