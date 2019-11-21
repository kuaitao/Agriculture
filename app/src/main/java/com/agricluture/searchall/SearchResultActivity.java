package com.agricluture.searchall;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.bean.PopSelectBean;
import com.agricluture.main.bean.ProductNameBean;
import com.agricluture.searchall.adapter.SearchResultAdapter;
import com.agricluture.searchall.adapter.SelectLvelAdapter;
import com.agricluture.searchall.bean.DateBean;
import com.agricluture.searchall.bean.SearchResultBean;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.Utils;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchResultActivity extends BaseActivity {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.mt_tv_product)
    TextView mtTvProduct;
    @BindView(R.id.mt_tv_address)
    TextView mtTvAddress;
    @BindView(R.id.mt_rly_product)
    RelativeLayout mtRlyProduct;

    private String selectType;

    List<SearchResultBean> searchResultBeanList = new ArrayList<>();
    private List<PopSelectBean> addressList = new ArrayList<>();
    private List<PopSelectBean> addressList2 = new ArrayList<>();
    private List<PopSelectBean> addressList3 = new ArrayList<>();
    private SearchResultAdapter searchResultAdapter;
    @Override
    protected int layoutViewId() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initViews() {

        initTitleBar(R.string.search_reault);
        selectType = getIntent().getStringExtra("selectType");
        ArrayList<ProductNameBean>  productNameBeanArrayLis = getIntent().getParcelableArrayListExtra("product");

        for (ProductNameBean popSelectBean:productNameBeanArrayLis){
            PopSelectBean popSelectBeanItem = new PopSelectBean(popSelectBean.getProductname(), 0);
            addressList.add(popSelectBeanItem);
        }
        PopSelectBean popSelectBeanEnd = new PopSelectBean("取消", 0);
        addressList.add(popSelectBeanEnd);

        if ("按地区".equals(selectType)) {
            mtTvAddress.setText("地区");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean = new SearchResultBean.ChildrenFirstBean.ChildrenBean("09-10", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean2 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("09-11", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean3 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("09-12", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean4 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("09-13", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean5 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("09-14", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean6 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("09-15", "10", "122");

            List<SearchResultBean.ChildrenFirstBean.ChildrenBean> childrenBeanList = new ArrayList<>();
            List<SearchResultBean.ChildrenFirstBean.ChildrenBean> childrenBeanList2 = new ArrayList<>();
            childrenBeanList.add(childrenBean);
            childrenBeanList.add(childrenBean2);
            childrenBeanList.add(childrenBean3);
            childrenBeanList2.add(childrenBean4);
            childrenBeanList2.add(childrenBean5);
            childrenBeanList2.add(childrenBean6);

            SearchResultBean.ChildrenFirstBean searchResultBean = new SearchResultBean.ChildrenFirstBean("地区:北京", childrenBeanList);
            SearchResultBean.ChildrenFirstBean searchResultBean2 = new SearchResultBean.ChildrenFirstBean("地区:天津", childrenBeanList2);

            List<SearchResultBean.ChildrenFirstBean> childrenFirstBeanList = new ArrayList<>();
            childrenFirstBeanList.add(searchResultBean);
            childrenFirstBeanList.add(searchResultBean2);

            SearchResultBean searchResultBean00 = new SearchResultBean("大豆", childrenFirstBeanList, false);
            SearchResultBean searchResultBean11 = new SearchResultBean("小麦", childrenFirstBeanList, false);

            searchResultBeanList.add(searchResultBean00);
            searchResultBeanList.add(searchResultBean11);
        } else {
            mtTvAddress.setText("期别");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean = new SearchResultBean.ChildrenFirstBean.ChildrenBean("河北", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean2 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("北京", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean3 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("天津", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean4 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("邯郸", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean5 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("广东", "10", "122");
            SearchResultBean.ChildrenFirstBean.ChildrenBean childrenBean6 = new SearchResultBean.ChildrenFirstBean.ChildrenBean("香港", "10", "122");

            List<SearchResultBean.ChildrenFirstBean.ChildrenBean> childrenBeanList = new ArrayList<>();
            List<SearchResultBean.ChildrenFirstBean.ChildrenBean> childrenBeanList2 = new ArrayList<>();
            childrenBeanList.add(childrenBean);
            childrenBeanList.add(childrenBean2);
            childrenBeanList.add(childrenBean3);
            childrenBeanList2.add(childrenBean4);
            childrenBeanList2.add(childrenBean5);
            childrenBeanList2.add(childrenBean6);

            SearchResultBean.ChildrenFirstBean searchResultBean = new SearchResultBean.ChildrenFirstBean("期别:010-2019", childrenBeanList);
            SearchResultBean.ChildrenFirstBean searchResultBean2 = new SearchResultBean.ChildrenFirstBean("期别:011-2019", childrenBeanList2);


            List<SearchResultBean.ChildrenFirstBean> childrenFirstBeanList = new ArrayList<>();
            childrenFirstBeanList.add(searchResultBean);
            childrenFirstBeanList.add(searchResultBean2);

            SearchResultBean searchResultBean00 = new SearchResultBean("石油", childrenFirstBeanList, false);
            SearchResultBean searchResultBean11 = new SearchResultBean("润滑", childrenFirstBeanList, false);

            searchResultBeanList.add(searchResultBean00);
            searchResultBeanList.add(searchResultBean11);
        }

        recyclerview.setLayoutManager(new LinearLayoutManager(activity));
        searchResultAdapter = new SearchResultAdapter(activity, searchResultBeanList);
        recyclerview.setAdapter(searchResultAdapter);
        searchResultAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SearchResultBean searchResultBean = (SearchResultBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.item_sr_rly:

                        if (searchResultBean.isShow) {
                            searchResultBean.setShow(false);
                        } else {
                            searchResultBean.setShow(true);
                        }
                        searchResultAdapter.notifyItemChanged(position);
                        break;
                    default:
                }
            }
        });
    }

    @Override
    protected void initData() {
        //期别
        HttpUtils.getInstance().searchApi.postDateSelect("3a26b33b-8cac-4d32-a5d2-8405bc500084", new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if(!ObjectUtils.isEmpty(s.getData())){
                    List<DateBean> dateBean = GsonUtil.getRealListFromT(s.getData(),DateBean[].class);
                    for (int i = 0; i < dateBean.size(); i++) {
                        PopSelectBean popSelectBean = new PopSelectBean("期别："+dateBean.get(i).getStrstagename(),0);
                        addressList3.add(popSelectBean);
                    }
                    PopSelectBean popSelectBean2 = new PopSelectBean("取消",0);
                    addressList3.add(popSelectBean2);
                }

            }

            @Override
            public void onError(ApiException t) {

            }
        });
    }


    @OnClick({R.id.mt_rly_product, R.id.mt_rly_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mt_rly_product:
                showAddressDialog();

                break;
            case R.id.mt_rly_address:
                if ("按地区".equals(selectType)) {
                    showAddressDialogTwo(2);
                } else {
                    showAddressDialogThree();
                }

                break;
            default:
        }
    }

    private int one = 1;

    private void showAddressDialog() {



        View popupView = activity.getLayoutInflater().inflate(R.layout.pop_list, null);
        final PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = popupView.findViewById(R.id.pmrl_rlv_list);
        window.setAnimationStyle(R.style.popup_window_anim);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(mtRlyProduct, 0, Utils.dip2px(activity, 0));


        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        SelectLvelAdapter selectLvelAdapter = new SelectLvelAdapter(addressList);
        selectLvelAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            PopSelectBean popSelectBeans = (PopSelectBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.item_rly_popsel:
                    window.dismiss();
                    for (int i = 0; i < addressList.size(); i++) {
                        addressList.get(i).setFlag(0);
                    }
                    if (popSelectBeans.getName().equals("取消")) {
                        mtTvProduct.setText("产品");
                    } else {
                        mtTvProduct.setText(popSelectBeans.getName());
                        addressList.get(position).setFlag(1);
                    }

                    break;
                default:
            }
        });
        recyclerView.setAdapter(selectLvelAdapter);

    }

    private void showAddressDialogTwo(int flag) {


        if (one == 1) {
            one = 2;
            addressList2.clear();
            PopSelectBean popSelectBean = new PopSelectBean("北京市", 0);
            PopSelectBean popSelectBean2 = new PopSelectBean("石家庄", 0);
            PopSelectBean popSelectBean3 = new PopSelectBean("保定市", 0);
            PopSelectBean popSelectBean4 = new PopSelectBean("取消", 0);
            addressList2.add(popSelectBean);
            addressList2.add(popSelectBean2);
            addressList2.add(popSelectBean3);
            addressList2.add(popSelectBean4);
        }


        View popupView = activity.getLayoutInflater().inflate(R.layout.pop_list, null);
        final PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = popupView.findViewById(R.id.pmrl_rlv_list);
        window.setAnimationStyle(R.style.popup_window_anim);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(mtRlyProduct, 0, Utils.dip2px(activity, 0));


        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        SelectLvelAdapter selectLvelAdapter = new SelectLvelAdapter(addressList2);
        selectLvelAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            PopSelectBean popSelectBean = (PopSelectBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.item_rly_popsel:
                    window.dismiss();
                    for (int i = 0; i < addressList2.size(); i++) {
                        addressList2.get(i).setFlag(0);
                    }
                    if (popSelectBean.getName().equals("取消")) {

                        if ("按地区".equals(selectType)) {
                            mtTvAddress.setText("地区");
                        } else {
                            mtTvAddress.setText("期别");
                        }

                    } else {
                        mtTvAddress.setText(popSelectBean.getName());
                        addressList2.get(position).setFlag(1);
                    }

                    break;
                default:
            }
        });
        recyclerView.setAdapter(selectLvelAdapter);

    }

    private void showAddressDialogThree() {
        View popupView = activity.getLayoutInflater().inflate(R.layout.pop_list, null);
        final PopupWindow window = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = popupView.findViewById(R.id.pmrl_rlv_list);
        window.setAnimationStyle(R.style.popup_window_anim);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(mtRlyProduct, 0, Utils.dip2px(activity, 0));


        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        SelectLvelAdapter selectLvelAdapter = new SelectLvelAdapter(addressList3);
        selectLvelAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            PopSelectBean popSelectBean = (PopSelectBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.item_rly_popsel:
                    window.dismiss();
                    for (int i = 0; i < addressList3.size(); i++) {
                        addressList3.get(i).setFlag(0);
                    }
                    if (popSelectBean.getName().equals("取消")) {

                        if ("按地区".equals(selectType)) {
                            mtTvAddress.setText("地区");
                        } else {
                            mtTvAddress.setText("期别");
                        }

                    } else {
                        mtTvAddress.setText(popSelectBean.getName());
                        addressList3.get(position).setFlag(1);
                    }

                    break;
                default:
            }
        });
        recyclerView.setAdapter(selectLvelAdapter);

    }
}
