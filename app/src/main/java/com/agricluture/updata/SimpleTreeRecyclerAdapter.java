package com.agricluture.updata;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.agriculture.R;
import com.multilevel.treelist.Node;
import com.multilevel.treelist.TreeRecyclerAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhangke on 2017-1-14.
 */
public class SimpleTreeRecyclerAdapter extends TreeRecyclerAdapter {

    private  int flagclass;
    private Context context;
    public SimpleTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand,int flagclass) {
        super(mTree, context, datas, defaultExpandLevel, iconExpand, iconNoExpand);
        this.flagclass = flagclass;
        this.context =context;
    }

    public SimpleTreeRecyclerAdapter(RecyclerView mTree, Context context, List<Node> datas, int defaultExpandLevel) {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHoder(View.inflate(mContext, R.layout.list_item,null));
    }

    @Override
    public void onBindViewHolder(final Node node, RecyclerView.ViewHolder holder, int position) {

        final MyHoder viewHolder = (MyHoder) holder;

        if(flagclass ==1){
            if(node.getLevel()==5){
                viewHolder.cb.setVisibility(View.GONE);
                viewHolder.states.setVisibility(View.VISIBLE);
                viewHolder.num.setVisibility(View.GONE);
            }else{
                viewHolder.cb.setVisibility(View.GONE);
                viewHolder.states.setVisibility(View.GONE);
                viewHolder.num.setVisibility(View.VISIBLE);
                viewHolder.num.setText(node.getNumData()+"");
            }

        }else{


            viewHolder.states.setVisibility(View.GONE);
            if(node.getLevel()==5){
                viewHolder.cb.setVisibility(View.VISIBLE);
                viewHolder.num.setText("催办");
                viewHolder.num.setBackgroundResource(R.drawable.btn_change_c7);
                viewHolder.num.setTextColor(context.getResources().getColor(R.color.white));
            }else{
                viewHolder.cb.setVisibility(View.GONE);
                viewHolder.num.setText(node.getNumData()+"");
                viewHolder.num.setTextColor(context.getResources().getColor(R.color.black));
                viewHolder.num.setBackgroundResource(R.color.white);
            }

        }

        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(node,viewHolder.cb.isChecked());
            }
        });

        if (node.isChecked()){
            viewHolder.cb.setChecked(true);
        }else {
            viewHolder.cb.setChecked(false);
        }

        if (node.getIcon() == -1) {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }

        viewHolder.label.setText(node.getName());



    }

    class MyHoder extends RecyclerView.ViewHolder{

        public CheckBox cb;

        public TextView label;
        public ImageView icon;
        public TextView states;
        public TextView num;
        public MyHoder(View itemView) {
            super(itemView);

            cb = (CheckBox) itemView
                    .findViewById(R.id.cb_select_tree);
            label = (TextView) itemView
                    .findViewById(R.id.id_treenode_label);

            icon = (ImageView) itemView.findViewById(R.id.icon);


            states =(TextView) itemView
                    .findViewById(R.id.states);

            num =(TextView) itemView
                    .findViewById(R.id.num);
        }

    }
}
