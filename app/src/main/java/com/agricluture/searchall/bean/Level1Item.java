package com.agricluture.searchall.bean;

import com.agricluture.searchall.adapter.ExpandableItemAdapter;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zem on 2019/6/21.
 */
public class Level1Item extends AbstractExpandableItem implements MultiItemEntity {
    public String title;
    public String subTitle;
    public String state;
    public long pId;

    public Level1Item(String title, String subTitle,String state, long pId) {
        this.subTitle = subTitle;
        this.title = title;
        this.state=state;
        this.pId=pId;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}