package com.agricluture.searchall.adapter;

import android.content.Context;

import com.agricluture.homepage.adapter.TastListChildAdapter;
import com.agricluture.searchall.bean.Level0Item;
import com.agricluture.searchall.bean.Level1Item;
import com.agricluture.searchall.bean.SearchResultBean;
import com.agriculture.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author zem
 * @date 2019/10/10.
 * description：
 */
public class SearchResultAdapter extends BaseQuickAdapter<SearchResultBean, BaseViewHolder> {

    private Context context;

    public SearchResultAdapter(Context context, List<SearchResultBean> listCode) {
        super(R.layout.search_result_item, listCode);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SearchResultBean item) {

        helper.setText(R.id.item_sr_tv_address,item.getName() ).addOnClickListener(R.id.item_sr_rly);

        RecyclerView myRecyclerView = helper.getView(R.id.recyclerview);
        ExpandableItemAdapter adapter = new ExpandableItemAdapter(generateData(item.getChildrenFirstBeanList()), context);
        final LinearLayoutManager manager = new LinearLayoutManager(context);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setLayoutManager(manager);


        if(item.isShow){
            helper.setGone(R.id.recyclerview,true);
            helper.setBackgroundRes(R.id.item_sr_iv_more,R.mipmap.more_content_down_black);

        }else{
            helper.setGone(R.id.recyclerview,false);
            helper.setBackgroundRes(R.id.item_sr_iv_more,R.mipmap.more_content_black);

        }
    }

    private ArrayList<MultiItemEntity> generateData(List<SearchResultBean.ChildrenFirstBean> searchResultBeanList) {

        ArrayList<MultiItemEntity> res = new ArrayList<>();

        for (int i = 0; i < searchResultBeanList.size(); i++) {

            Level0Item lv0 = null;
            lv0 = new Level0Item(searchResultBeanList.get(i).address + "", "");
            List<SearchResultBean.ChildrenFirstBean.ChildrenBean> allPlanListBeans = searchResultBeanList.get(i).getChildrenBeans();

            for (int j = 0; j < allPlanListBeans.size(); j++) {
                Level1Item lv1 = new Level1Item(allPlanListBeans.get(j).getData(), allPlanListBeans.get(j).getPersonPrice(), allPlanListBeans.get(j).getPersonPrice(), 0);
                lv0.addSubItem(lv1);
            }

            res.add(lv0);

        }


        return res;
    }
}

