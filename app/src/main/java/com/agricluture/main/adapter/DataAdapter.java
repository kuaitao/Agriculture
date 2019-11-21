package com.agricluture.main.adapter;

import com.agricluture.main.bean.DataBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author zem
 * @date 2019/10/8.
 * description：
 */
public class DataAdapter extends BaseMultiItemQuickAdapter<DataBean, BaseViewHolder> {

    public DataAdapter(List<DataBean> dataBeanList) {

        super(dataBeanList);
        addItemType(DataBean.DOUBLE, R.layout.item_data_double);
        addItemType(DataBean.SINGLE, R.layout.item_data_single);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        switch (helper.getItemViewType()) {
            case DataBean.DOUBLE:
                helper.setText(R.id.dd_tv_title, item.getTitle()).setText(R.id.dd_tv_num, item.getYesNum()+"").setText(R.id.dd_tv_num2, item.getNoNum()+"").addOnClickListener(R.id.dd_lly_tell_yes).addOnClickListener(R.id.dd_lly_tell_no);
                break;
            case DataBean.SINGLE:
                helper.setText(R.id.dds_tv_title, item.getTitle()).addOnClickListener(R.id.dds_lly_tell_yes);

                break;
                default:
        }
    }

}
