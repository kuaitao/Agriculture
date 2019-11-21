package com.agricluture.homepage.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.agricluture.homepage.bean.TastListBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author zem
 * @date 2019/10/9.
 * description：
 */
public class TastListAdapter extends BaseQuickAdapter<TastListBean, BaseViewHolder> {

    private Context context;
    TastListChildAdapter adapter;
    private int verify_flag;
    public TastListAdapter(Context context, List<TastListBean> listCode, int verify_flag) {
        super(R.layout.tast_list_item, listCode);
        this.context = context;
        this.verify_flag =verify_flag;
    }

    @Override
    protected void convert(BaseViewHolder helper, final TastListBean item) {
        helper.addOnClickListener(R.id.item_tast_btn_no).addOnClickListener(R.id.item_tast_btn_yes);
        TextView tiemED = helper.getView(R.id.tki_tv_time);
        if(verify_flag ==2){
            helper.setVisible(R.id.item_tast_lly_button,true);
        }

        tiemED.setText(item.getCreateTime());

//        if (tiemED.getTag() instanceof TextWatcher) {
//            tiemED.removeTextChangedListener(((TextWatcher) tiemED.getTag()));
//        }


//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (TextUtils.isEmpty(s)) {
//                    onItemClickListener.onItemClick("", TastListAdapter.this, helper.getAdapterPosition());
//                }else{
//                    onItemClickListener.onItemClick(s.toString(), TastListAdapter.this, helper.getAdapterPosition());
//                }
//
//            }
//        };
//        tiemED.addTextChangedListener(textWatcher);
//        tiemED.setTag(textWatcher);

        RecyclerView myRecyclerView = helper.getView(R.id.item_tast_rlv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        myRecyclerView.setLayoutManager(layoutManager);
        adapter = new TastListChildAdapter(item.getStreams(),helper.getAdapterPosition());
        myRecyclerView.setAdapter(adapter);


    }


}