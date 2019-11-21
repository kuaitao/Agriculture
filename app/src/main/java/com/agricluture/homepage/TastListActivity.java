package com.agricluture.homepage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.agricluture.base.BaseActivity;
import com.agricluture.homepage.adapter.TastListAdapter;
import com.agricluture.homepage.bean.EventTastChildren;
import com.agricluture.homepage.bean.TastListBean;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.bean.PopSelectBean;
import com.agricluture.searchall.adapter.SelectLvelAdapter;
import com.agricluture.utils.JumperUtils;
import com.agricluture.utils.Utils;
import com.agricluture.view.DialogCustom;
import com.agricluture.view.ToastCustom;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.exception.ApiException;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TastListActivity extends BaseActivity {
    private int pageNum = 1;
    private int pageSize = 20;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.mt_tv_product)
    TextView mtTvProduct;
    @BindView(R.id.mt_rly_product)
    LinearLayout mtRlyProduct;
    @BindView(R.id.title_title)
    TextView titleTitle;
    @BindView(R.id.btn_all_refuse)
    Button btnAllRefuse;
    private TastListAdapter tastListAdapter;
    List<TastListBean> testBeanList = new ArrayList<>();
    private List<PopSelectBean> addressList = new ArrayList<>();

    /**
     * 1已审核 2未审核
     */
    private int verify_flag;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_tast_list;
    }

    @Override
    protected void initViews() {
        titleTitle.setEms(14);
        initTitleBar(R.string.tastlist);
        verify_flag = getIntent().getIntExtra("verify_flag", 1);
        TastListBean.StreamsBean streamsBean = new TastListBean.StreamsBean("早灿稻", "中等", "50公斤", "130.6", "155.5", "120.6", "145.5");
        TastListBean.StreamsBean streamsBean2 = new TastListBean.StreamsBean("中灿稻", "中等", "50公斤", "133.5", "155.8", "123.5", "145.8");
        TastListBean.StreamsBean streamsBean3 = new TastListBean.StreamsBean("晚灿稻", "中等", "50公斤", "132.5", "178", "132.5", "168");

        List<TastListBean.StreamsBean> streamsBeanList = new ArrayList<>();
        streamsBeanList.add(streamsBean);
        streamsBeanList.add(streamsBean2);
        List<TastListBean.StreamsBean> streamsBeanList2 = new ArrayList<>();
        streamsBeanList2.add(streamsBean3);
        TastListBean tastListBean = new TastListBean("2019-10-17", "李俊", streamsBeanList);
        TastListBean tastListBean2 = new TastListBean("2019-10-17", "李俊", streamsBeanList2);


        TastListBean tastListBean3 = new TastListBean("2019-10-17", "李俊", streamsBeanList2);
        TastListBean tastListBean4 = new TastListBean("2019-10-17", "李俊", streamsBeanList2);
        TastListBean tastListBean5 = new TastListBean("2019-10-17", "李俊", streamsBeanList2);
        TastListBean tastListBean6 = new TastListBean("2019-10-17", "李俊", streamsBeanList2);
        TastListBean tastListBean7 = new TastListBean("2019-10-17", "李俊", streamsBeanList2);

        testBeanList.add(tastListBean);
        testBeanList.add(tastListBean2);
        testBeanList.add(tastListBean3);
        testBeanList.add(tastListBean4);
        testBeanList.add(tastListBean5);
        testBeanList.add(tastListBean6);
        testBeanList.add(tastListBean7);

        for (int i = 0; i < 20; i++) {
            TastListBean tastListBean10 = new TastListBean("2019-10-17" + i, "李俊", streamsBeanList2);
            testBeanList.add(tastListBean10);
        }


        recyclerview.setLayoutManager(new LinearLayoutManager(activity));
        tastListAdapter = new TastListAdapter(activity, testBeanList, verify_flag);
        recyclerview.setAdapter(tastListAdapter);

        //1
        tastListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                TastListBean status = (TastListBean) adapter.getItem(position);
                switch (view.getId()) {

                    case R.id.item_tast_btn_no:
                        goOut();
                        break;

                    case R.id.item_tast_btn_yes:

                        ToastCustom.showVerifyToast(activity, "审核通过", R.mipmap.verify_yes_icon);
                        break;
                    default:
                }
            }
        });


        addressList.clear();
        if (verify_flag == 1) {

            PopSelectBean popSelectBean = new PopSelectBean("北京市", 0);
            PopSelectBean popSelectBean2 = new PopSelectBean("江苏省", 0);
            PopSelectBean popSelectBean3 = new PopSelectBean("湖南省", 0);
            addressList.add(popSelectBean);
            addressList.add(popSelectBean2);
            addressList.add(popSelectBean3);
            addressList.add(new PopSelectBean("取消", 0));
            btnAllRefuse.setVisibility(View.GONE);
        } else {
            PopSelectBean popSelectBean = new PopSelectBean("天津市", 0);
            PopSelectBean popSelectBean2 = new PopSelectBean("河北省", 0);
            PopSelectBean popSelectBean3 = new PopSelectBean("山东省", 0);
            addressList.add(popSelectBean);
            addressList.add(popSelectBean2);
            addressList.add(popSelectBean3);
            addressList.add(new PopSelectBean("取消", 0));
            btnAllRefuse.setVisibility(View.VISIBLE);
        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(EventTastChildren eventBean) {
        testBeanList.get(eventBean.getFistPositon()).getStreams().get(eventBean.getThisPositon()).setGuige(eventBean.getContents());
        tastListAdapter.notifyDataSetChanged();

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void initData() {

        if(verify_flag ==2){
            HttpUtils.getInstance().homeApi.postNoTast("3a26b33b-8cac-4d32-a5d2-8405bc500084", "O19-2019", pageNum, pageSize, new SimpleCallBack<HttpResultBean>() {
                @Override
                public void onSuccess(HttpResultBean s) {


                }

                @Override
                public void onError(ApiException t) {

                }
            });
        }
    }


    private void goOut() {

        final DialogCustom bottomDialog = new DialogCustom(this, R.layout.dialog_goout);
        EditText editText = bottomDialog.findViewById(R.id.goout_et_content);
        bottomDialog.findViewById(R.id.goout_btn_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bottomDialog != null) {
                    bottomDialog.dismiss();
                }
                ToastCustom.showVerifyToast(activity, "审核驳回", R.mipmap.verify_no_icon);
                JumperUtils.JumpTo(activity, MoreTastActivity.class);
            }
        });
        bottomDialog.findViewById(R.id.goout_btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomDialog != null) {
                    bottomDialog.dismiss();
                }
            }
        });
        bottomDialog.show();

    }

    @OnClick({R.id.mt_rly_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mt_rly_product:
                showAddressDialog();
                break;
            default:
        }
    }


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
            PopSelectBean popSelectBean = (PopSelectBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.item_rly_popsel:
                    window.dismiss();

                    for (int i = 0; i < addressList.size(); i++) {
                        addressList.get(i).setFlag(0);
                    }
                    if (popSelectBean.getName().equals("取消")) {
                        mtTvProduct.setText("地区");
                    } else {
                        mtTvProduct.setText(popSelectBean.getName());
                        addressList.get(position).setFlag(1);
                    }
                    break;
                default:
            }
        });
        recyclerView.setAdapter(selectLvelAdapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
