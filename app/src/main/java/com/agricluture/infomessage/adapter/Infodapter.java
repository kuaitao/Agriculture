package com.agricluture.infomessage.adapter;

import android.view.View;

import com.agricluture.main.bean.TestBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author zem
 * @date 2019/9/25.
 * description：
 */
public class Infodapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    private int  classFlag;
    public Infodapter(List<TestBean> listCode,int classFlag) {
        super(R.layout.info_message_item, listCode);
        this.classFlag=classFlag;
    }

    @Override
    protected void convert(BaseViewHolder helper, final TestBean item) {

        helper.setText(R.id.im_item_tv_name, item.getName())
                .setText(R.id.im_item_tv_time, item.getTime())
                .addOnClickListener(R.id.im_item_lly);


        if(classFlag ==3){
            helper.setVisible(R.id.im_iv_icon,false);
        }else{
            helper.setVisible(R.id.im_iv_icon, true);
            if(item.getJdFlag()==1){
                helper.setBackgroundRes(R.id.im_iv_icon,R.mipmap.message_no);
            }else{
                helper.setBackgroundRes(R.id.im_iv_icon,R.mipmap.message_yes);
            }
        }

    }

}