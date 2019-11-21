package com.agricluture.searchall;

import android.view.View;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.searchall.adapter.SelectProductAdapter;
import com.agricluture.searchall.bean.ProductSelectBean;
import com.agriculture.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class SelectProductActivity extends BaseActivity {


    @BindView(R.id.sp_rlv_list)
    RecyclerView spRlvList;
    private SelectProductAdapter selectProductAdapter;

    private List<ProductSelectBean> productSelectBeans = new ArrayList<>();
    private List<ProductSelectBean> selectProduct = new ArrayList<>();
    @Override
    protected int layoutViewId() {
        return R.layout.activity_select_product;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.select_product);

        for (int i = 0; i <12 ; i++) {
            ProductSelectBean productSelectBean =new ProductSelectBean("石油"+i,false);
            productSelectBeans.add(productSelectBean);
        }

        initAdapter(productSelectBeans);
    }

    private void initAdapter(List<ProductSelectBean> rwlistCode) {

        spRlvList.setLayoutManager(new LinearLayoutManager(activity));
        selectProductAdapter = new SelectProductAdapter(rwlistCode);
        selectProductAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ProductSelectBean status = (ProductSelectBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.selpro_item_lly:
                    if(status.isState()){
                        status.setState(false);
                        if(selectProduct.size()>0){
                            selectProduct.remove(status);
                        }

                    }else{
                        status.setState(true);
                        selectProduct.add(status);
                    }
                    selectProductAdapter.notifyItemChanged(position);

                    break;
                default:
            }
        });
        spRlvList.setAdapter(selectProductAdapter);


    }
    @Override
    protected void initData() {

    }



    @OnClick({R.id.sp_btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sp_btn_sure:

                if(selectProduct.size()>0){

                }else{
                    Toast.makeText(activity, "请选择产品！", Toast.LENGTH_SHORT).show();
                }

                break;
                default:
        }
    }
}
