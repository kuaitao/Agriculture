package com.agricluture.main.adapter;

import android.widget.TextView;

import com.agricluture.main.bean.HomeRwBean;
import com.agricluture.main.bean.TestBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author zem
 * @date 2019/9/24.
 * description：
 */
public class HomeRwAdapter extends BaseQuickAdapter<HomeRwBean, BaseViewHolder> {
    /**
     * 1可打开 2不能
     */
    private int moreList;
    private List<HomeRwBean> dataBeanList;
    public HomeRwAdapter(List<HomeRwBean> dataBeanList, int moreList) {
        super(R.layout.home_rw_item, dataBeanList);
        this.moreList = moreList;
        this.dataBeanList = dataBeanList;
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeRwBean item) {
        TextView tv = helper.getView(R.id.hrw_item_tv_name);
        tv.setEms(14);
        helper.setText(R.id.hrw_item_tv_name, item.getTastName())
                .setText(R.id.hrw_item_tv_data, "期别：" + item.getStrStage())
                .setText(R.id.hrw_item_tv_time, "截止时间："+item.getState())
                .setText(R.id.hrw_item_tv_sign_ed, "已报：" + item.getReportNumber())
                .setText(R.id.hrw_item_tv_sign, "未报：" + item.getUnreportNumber())
                .addOnClickListener(R.id.hrw_item_btn_up, R.id.hrw_item_rly_show);

        //01待填报、02待审核、03处理中、05被驳回
        TextView stateTv = helper.getView(R.id.hrw_item_tv_state);
        if ("01".equals(item.getState().trim())) {
            stateTv.setText("待填报");
            helper.setBackgroundRes(R.id.hrw_item_tv_state, R.drawable.bg_red_zj);
            helper.setText(R.id.hrw_item_btn_up, "填报");

        } else if ("02".equals(item.getState().trim())) {
            stateTv.setText("待审核");
            helper.setBackgroundRes(R.id.hrw_item_tv_state, R.drawable.bg_red_zj);
            helper.setText(R.id.hrw_item_btn_up, "审核");
        } else if ("03".equals(item.getState().trim())) {
            stateTv.setText("处理中");
            helper.setBackgroundRes(R.id.hrw_item_tv_state, R.drawable.bg_maincolor_zj);
            helper.setText(R.id.hrw_item_btn_up, "填报");
        } else if ("05".equals(item.getState().trim())) {
            stateTv.setText("被驳回");
            helper.setBackgroundRes(R.id.hrw_item_tv_state, R.drawable.bg_red_zj);
            helper.setText(R.id.hrw_item_btn_up, "填报");
        }

        if("02".equals(item.getState().trim())){
            helper.setGone(R.id.hrw_item_tv_sign_ed,true);
            helper.setGone(R.id.hrw_item_tv_sign,true);
        }else{
            helper.setGone(R.id.hrw_item_tv_sign_ed,false);
            helper.setGone(R.id.hrw_item_tv_sign,false);
        }

        if ("1".equals(item.getUrgent())) {

            helper.setVisible(R.id.hrw_item_tv_nameflag, true);

        } else {

            helper.setVisible(R.id.hrw_item_tv_nameflag, false);
        }

        if (moreList == 1) {
            if (!item.isShow()) {
                helper.setBackgroundRes(R.id.hrw_item_iv_show, R.mipmap.more_jt_maincolor_up).setBackgroundRes(R.id.home_lly, R.mipmap.nine_bg).setGone(R.id.hrw_item_lly_hidde, false);
            } else {
                helper.setBackgroundRes(R.id.hrw_item_iv_show, R.mipmap.more_jt_maincolor_down).setBackgroundRes(R.id.home_lly, R.mipmap.bg_yys).setGone(R.id.hrw_item_lly_hidde, true);
            }
        } else {
            helper.setVisible(R.id.hrw_item_iv_show, false);
        }



    }

    @Override
    public int getItemCount() {
        if(moreList==1){
            return dataBeanList.size()>6?6:dataBeanList.size();
        }else{
            return dataBeanList.size();
        }

    }


}