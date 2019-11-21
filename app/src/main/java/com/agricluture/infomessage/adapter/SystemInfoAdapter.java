package com.agricluture.infomessage.adapter;

import com.agricluture.infomessage.bean.SystemMesBean;
import com.agricluture.utils.Utils;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author zem
 * @date 2019/10/29.
 * description：
 */
public class SystemInfoAdapter extends BaseQuickAdapter<SystemMesBean, BaseViewHolder> {


    public SystemInfoAdapter(List<SystemMesBean> listCode) {
        super(R.layout.info_message_item, listCode);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SystemMesBean item) {


        helper.setText(R.id.im_item_tv_name, item.getStrnoticetitle())
                .setText(R.id.im_item_tv_time, Utils.dealDateFormatT(item.getCreatetime()))
                .addOnClickListener(R.id.im_item_lly);

            helper.setVisible(R.id.im_iv_icon, true);
            if ("1".equals(item.getBitRead())) {
                helper.setBackgroundRes(R.id.im_iv_icon, R.mipmap.message_yes);
            } else {
                helper.setBackgroundRes(R.id.im_iv_icon, R.mipmap.message_no);
            }


    }


}