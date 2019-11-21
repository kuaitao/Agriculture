package com.agricluture.infomessage.adapter;

import com.agricluture.infomessage.bean.MyMesBean;
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
public class MyInfosAdapter  extends BaseQuickAdapter<MyMesBean, BaseViewHolder> {


    public MyInfosAdapter(List<MyMesBean> listCode) {
        super(R.layout.info_message_item, listCode);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyMesBean item) {

        helper.setText(R.id.im_item_tv_name, item.getStrmessagetitle())
                .setText(R.id.im_item_tv_time, item.getCreateTime())
                .addOnClickListener(R.id.im_item_lly);

        helper.setVisible(R.id.im_iv_icon, true);
        if("1".equals(item.getBitdisable())){
            helper.setBackgroundRes(R.id.im_iv_icon, R.mipmap.message_yes);
        } else {
            helper.setBackgroundRes(R.id.im_iv_icon, R.mipmap.message_no);
        }


    }

}