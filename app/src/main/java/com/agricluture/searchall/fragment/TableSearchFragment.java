package com.agricluture.searchall.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.agricluture.base.BaseFragment;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.bean.TableNameBean;
import com.agricluture.searchall.SearchResultActivity;
import com.agricluture.updata.TellYesActivity;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.JumperUtils;
import com.agriculture.R;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zem
 * @date 2019/10/7.
 * description：
 */
public class TableSearchFragment extends BaseFragment {
    @BindView(R.id.fd_et_bname)
    TextView fdEtBname;
    List<String> options1Items = new ArrayList<>();
    private OptionsPickerView tableSelect;
    @Override
    protected int layoutViewId() {
        return R.layout.fragment_table_search;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        HttpUtils.getInstance().searchApi.postTableName(new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if(null!=s.getData()){
                    List<TableNameBean>  tableNameBeanList= GsonUtil.getRealListFromT(s.getData(),TableNameBean[].class);

                    for (int i = 0; i < tableNameBeanList.size(); i++) {
                        options1Items.add(tableNameBeanList.get(i).getTaskname());
                    }
                    setShowTable();
                }

            }

            @Override
            public void onError(ApiException t) {

            }
        });
    }
    private void setShowTable(){
        tableSelect = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String tx = options1Items.get(options1);
                fdEtBname.setText(tx);
            }
        }).build();
        tableSelect.setPicker(options1Items, null, null);
    }
    @OnClick({R.id.login_btn_to, R.id.fd_lly_bname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn_to:
                Bundle bundle = new Bundle();
                bundle.putInt("flag",2);
                JumperUtils.JumpTo(activity, TellYesActivity.class,bundle);
                break;
            case R.id.fd_lly_bname:
                //表名
                if(null!=tableSelect) {
                    tableSelect.show();
                }

                break;
            default:
        }
    }

}
