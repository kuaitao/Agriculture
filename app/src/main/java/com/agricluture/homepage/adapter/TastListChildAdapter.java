package com.agricluture.homepage.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.agricluture.homepage.bean.EventTastChildren;
import com.agricluture.homepage.bean.TastListBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author zem
 * @date 2019/10/9.
 * description：
 */
public class TastListChildAdapter  extends BaseQuickAdapter<TastListBean.StreamsBean, BaseViewHolder> {

    private int fatherPositon ;
    public TastListChildAdapter(List<TastListBean.StreamsBean> listCode,int fatherPositon) {
        super(R.layout.tast_child_item, listCode);
        this.fatherPositon =fatherPositon;

    }

    @Override
    protected void convert(BaseViewHolder helper, final TastListBean.StreamsBean item) {

        helper.setText(R.id.tkc_tv_class,item.getName()).setText(R.id.tkc_et_live,item.getGuige())
                .setText(R.id.tkc_et_company,item.getGongjing()).setText(R.id.tkc_et_agomarketprice,item.getAgonshichangjia())
                .setText(R.id.tkc_et_agopersonprice,item.getAgochushoujia()).setText(R.id.tkc_et_personprice,item.getChushoujia())
                .setText(R.id.tkc_et_marketprice,item.getShichangjia());

    }
}