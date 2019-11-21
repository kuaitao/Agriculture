package com.agricluture.searchall.adapter;

import com.agricluture.searchall.bean.ProductSelectBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SelectProductAdapter extends BaseQuickAdapter<ProductSelectBean, BaseViewHolder> {


    public SelectProductAdapter(List<ProductSelectBean> listCode) {
        super(R.layout.select_product_item, listCode);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ProductSelectBean item) {

        helper.setText(R.id.selpro_item_tv_pro, item.getName())
                .addOnClickListener(R.id.selpro_item_lly);

        if(item.isState()){
            helper.setBackgroundRes(R.id.selpro_item_iv_pro, R.mipmap.double_select_in);
        }else{
            helper.setBackgroundRes(R.id.selpro_item_iv_pro, R.mipmap.double_select_out);
        }

    }



}
