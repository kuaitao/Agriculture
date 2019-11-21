package com.agricluture.main.adapter;

import android.widget.ImageView;

import com.agricluture.main.bean.HomeInfoBean;
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
public class HomeInfoAdapter  extends BaseQuickAdapter<HomeInfoBean, BaseViewHolder> {


    public HomeInfoAdapter(List<HomeInfoBean> listCode) {
        super(R.layout.home_info_item, listCode);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeInfoBean item) {

        helper.setText(R.id.hif_item_tv_name, item.getStrmessagetitle()  )
                .addOnClickListener(R.id.hif_item_lly);
        ImageView imageView =helper.getView(R.id.hif_item_iv_img);
        if("1".equals(item.getBitdisable())){
            imageView.setBackgroundResource(R.mipmap.message_yes);
        }else{
            imageView.setBackgroundResource(R.mipmap.message_no);
        }


    }
    @Override
    public int getItemCount() {
        return 6;
    }
}