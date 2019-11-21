package com.agricluture.homepage;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.homepage.adapter.PurchaseAdapter;
import com.agricluture.homepage.bean.TastListBean;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.utils.JumperUtils;
import com.agricluture.view.ToastCustom;
import com.agriculture.R;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.zhouyou.http.exception.ApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * @author zem
 * @date 2019/10/27.
 * description：数据采集
 */
public class PurchaseActivity extends BaseActivity {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
    @BindView(R.id.item_tast_rlv_list)
    RecyclerView itemTastRlvList;
    List<TastListBean.StreamsBean> streamsBeanList = new ArrayList<>();
    @BindView(R.id.tki_tv_time)
    TextView tkiTvTime;
    private TimePickerView startTimeView;
    private PurchaseAdapter purchaseAdapter;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_purchase;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.data_purchase);

        TastListBean.StreamsBean streamsBean = new TastListBean.StreamsBean("早灿稻","中等", "50公斤", "", "", "120.6", "145.5");
        TastListBean.StreamsBean streamsBean2 = new TastListBean.StreamsBean("中灿稻","中等", "50公斤", "", "", "123.5", "145.8");
        TastListBean.StreamsBean streamsBean3 = new TastListBean.StreamsBean("晚灿稻","中等", "50公斤", "", "", "132.5", "168");

        streamsBeanList.add(streamsBean);
        streamsBeanList.add(streamsBean2);
        streamsBeanList.add(streamsBean3);

        initAdapter();


        startTimeView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                tkiTvTime.setText(sdf.format(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).build();

    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        itemTastRlvList.setLayoutManager(layoutManager);
        purchaseAdapter = new PurchaseAdapter(streamsBeanList);
        itemTastRlvList.setAdapter(purchaseAdapter);
    }

    @Override
    protected void initData() {

//        HttpUtils.getInstance().homeApi.postCollectionData("a7f67974-46c2-4920-95a1-b066dcea0acd", new SimpleCallBack<HttpResultBean>() {
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

    @OnClick({R.id.item_tast_btn_no, R.id.item_tast_btn_yes, R.id.tki_tv_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_tast_btn_no:
                ToastCustom.showVerifyToast(activity, "上报成功", R.mipmap.verify_yes_icon);
                JumperUtils.JumpTo(activity,MoreTastActivity.class);
                break;
            case R.id.item_tast_btn_yes:
                ToastCustom.showVerifyToast(activity, "保存成功", R.mipmap.verify_yes_icon);
                break;
            case R.id.tki_tv_time:
                startTimeView.show();
                break;
            default:
        }
    }


}
