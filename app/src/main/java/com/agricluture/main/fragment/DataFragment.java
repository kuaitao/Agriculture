package com.agricluture.main.fragment;

import android.view.View;
import android.widget.Toast;

import com.agricluture.base.BaseFragment;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.adapter.DataAdapter;
import com.agricluture.main.bean.DataBean;
import com.agricluture.updata.TellNoActivity;
import com.agricluture.updata.TellYesActivity;
import com.agricluture.utils.JumperUtils;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author zem
 * @date 2019/9/21.
 * description：
 */
public class DataFragment extends BaseFragment {

    @BindView(R.id.fdata_rlv_list)
    RecyclerView fdataRlvList;

    List<DataBean> dataBeanList = new ArrayList<>();

    @Override
    protected int layoutViewId() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initViews() {

    }


    @Override
    protected void initData() {

        HttpUtils.getInstance().updataApi.posUpdataAll(1, 100, new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {

            }

            @Override
            public void onError(ApiException t) {

            }
        });


        DataBean data = new DataBean("全国原粮价格统计", 714, 4, "0", 2);
        DataBean data2 = new DataBean("全国成品粮价格统计", 0, 0, "0", 2);
        DataBean data3 = new DataBean("全国主要经济作物和养殖产品价格统计", 0, 0, "1", 1);
        DataBean data4 = new DataBean("全国主要省会城市超市价格统计", 0, 0, "1", 1);

        dataBeanList.add(data);
        dataBeanList.add(data2);
        dataBeanList.add(data3);
        dataBeanList.add(data4);

        final DataAdapter multipleItemAdapter = new DataAdapter(dataBeanList);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        fdataRlvList.setLayoutManager(manager);
        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                DataBean status = (DataBean) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.dd_lly_tell_yes:
                        JumperUtils.JumpTo(activity, TellYesActivity.class);

                        break;
                    case R.id.dd_lly_tell_no:
                        JumperUtils.JumpTo(activity, TellNoActivity.class);
                        break;
                    case R.id.dds_lly_tell_yes:
                        JumperUtils.JumpTo(activity, TellYesActivity.class);
                        break;
                    default:
                }
            }
        });
        fdataRlvList.setAdapter(multipleItemAdapter);
    }
}
