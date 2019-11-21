package com.agricluture.searchall.adapter;

import android.content.Context;
import android.view.View;

import com.agricluture.searchall.bean.Level0Item;
import com.agricluture.searchall.bean.Level1Item;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;


/**
 * Created by zem on 2019/6/21.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {


    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    private Context context;

    public ExpandableItemAdapter(List<MultiItemEntity> data, Context context) {
        super(data);
        this.context = context;
        addItemType(TYPE_LEVEL_0, R.layout.search_result_one_item);
        addItemType(TYPE_LEVEL_1, R.layout.search_result_two_item);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:

                final Level0Item lv0 = (Level0Item) item;

                holder.setText(R.id.item_sr_tv_address, lv0.title);

                holder.setImageResource(R.id.item_sr_iv_more, lv0.isExpanded() ? R.mipmap.more_content_black : R.mipmap.more_content_down_black);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:

                final Level1Item lv1 = (Level1Item) item;
                //  holder.getView(R.id.item_second_tv_title).setVisibility(View.GONE);
                holder.setText(R.id.srt_tv_data, lv1.title)
                        .setText(R.id.srt_tv_personprice, lv1.subTitle)
                        .setText(R.id.srt_tv_marketprice, lv1.state);


                break;
            default:

        }
    }
}
