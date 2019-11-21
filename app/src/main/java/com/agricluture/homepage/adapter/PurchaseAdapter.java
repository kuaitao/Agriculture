package com.agricluture.homepage.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.agricluture.homepage.bean.TastListBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author zem
 * @date 2019/10/15.
 * description：
 */
public class PurchaseAdapter extends BaseQuickAdapter<TastListBean.StreamsBean, BaseViewHolder> {

    public PurchaseAdapter(List<TastListBean.StreamsBean> listCode) {
        super(R.layout.purchase_item, listCode);

    }

    @Override
    protected void convert(BaseViewHolder helper, final TastListBean.StreamsBean item) {
         helper.setText(R.id.tkc_tv_class,item.getName()).setText(R.id.tkc_et_live,item.getGuige())
                 .setText(R.id.tkc_et_company,item.getGongjing()).setText(R.id.tkc_et_agomarketprice,item.getAgochushoujia())
                 .setText(R.id.tkc_et_agopersonprice,item.getAgonshichangjia());

        EditText nongminET = helper.getView(R.id.tkc_et_personprice);
        EditText marketET = helper.getView(R.id.tkc_et_marketprice);


        if (nongminET.getTag() instanceof TextWatcher) {
            nongminET.removeTextChangedListener(((TextWatcher) nongminET.getTag()));
        }
        nongminET.setText(item.getChushoujia());

        TextWatcher textWatcher3 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (TextUtils.isEmpty(s)) {
//                    onItemClickListener.onItemClick("", TastListChildAdapter.this, helper.getAdapterPosition(),fatherPositon);
//                }else{
//                    onItemClickListener.onItemClick(s.toString(), TastListChildAdapter.this, helper.getAdapterPosition(),fatherPositon);
//                }

            }
        };
        nongminET.addTextChangedListener(textWatcher3);
        nongminET.setTag(textWatcher3);


        if (marketET.getTag() instanceof TextWatcher) {
            marketET.removeTextChangedListener(((TextWatcher) marketET.getTag()));
        }
        marketET.setText(item.getShichangjia());

        TextWatcher textWatcher4 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (TextUtils.isEmpty(s)) {
//                    onItemClickListener.onItemClick("", TastListChildAdapter.this, helper.getAdapterPosition(),fatherPositon);
//                }else{
//                    onItemClickListener.onItemClick(s.toString(), TastListChildAdapter.this, helper.getAdapterPosition(),fatherPositon);
//                }

            }
        };
        marketET.addTextChangedListener(textWatcher4);
        marketET.setTag(textWatcher4);



    }

}