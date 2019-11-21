package com.agricluture.searchall.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.agricluture.main.bean.PopSelectBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author zem
 * @date 2019/10/12.
 * description：
 */
public class SelectLvelAdapter extends BaseQuickAdapter<PopSelectBean, BaseViewHolder> {


    public SelectLvelAdapter(List<PopSelectBean> listCode) {
        super(R.layout.popwin_list_item, listCode);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PopSelectBean item) {
        helper.addOnClickListener(R.id.item_rly_popsel);
        ((TextView) helper.getView(R.id.item_tv_popsel)).setText(item.getName());

        if(item.getFlag()==1){
            helper.setVisible(R.id.item_iv_show,true);
        }else{
            helper.setVisible(R.id.item_iv_show,false);
        }




    }
}
