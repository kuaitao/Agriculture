package com.agricluture.updata;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agriculture.R;
import com.multilevel.treelist.Node;
import com.multilevel.treelist.TreeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TellNoActivity extends BaseActivity {


    protected List<Node> mDatas = new ArrayList<Node>();
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_yib)
    TextView tvYib;
    private TreeRecyclerAdapter mAdapter;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_tell_yes;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.tell_no);
        tvYib.setVisibility(View.GONE);
        mDatas.add(new Node(3 + "", 1 + "", "河北省", 222));
        mDatas.add(new Node(221 + "", 2 + "", "河南", 224));
        mDatas.add(new Node(222 + "", 221 + "", "郑州", 221));
        mDatas.add(new Node(223 + "", 222 + "", "桥西区", 252));
        mDatas.add(new Node(224 + "", 223 + "", "神马镇", 242));
        mDatas.add(new Node(225 + "", 224 + "", "西山村", 222));
        mDatas.add(new Node(226 + "", 225 + "", "八组", 222));
        mDatas.add(new Node(227 + "", 225 + "", "九组", 022));
        mDatas.add(new Node(18 + "", 3 + "", "廊坊市", 922));
        mDatas.add(new Node(19 + "", 18 + "", "三河市", 232));
        mDatas.add(new Node(20 + "", 19 + "", "燕郊镇", 252));
        mDatas.add(new Node(21 + "", 20 + "", "左新庄村", 282));
        mDatas.add(new Node(22 + "", 21 + "", "四组", 422));
        mDatas.add(new Node(23 + "", 21 + "", "五组", 222));


        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        //第一个参数  RecyclerView
        //第二个参数  上下文
        //第三个参数  数据集
        //第四个参数  默认展开层级数 0为不展开
        //第五个参数  展开的图标
        //第六个参数  闭合的图标
        // 1已报2未报
        mAdapter = new SimpleTreeRecyclerAdapter(recyclerview, activity,
                mDatas, 0, R.mipmap.more_up, R.mipmap.more_content_black, 2);

        recyclerview.setAdapter(mAdapter);
    }

    /**
     * 显示选中数据
     */
    public void clickShow(View v) {
        StringBuilder sb = new StringBuilder();
        final List<Node> allNodes = mAdapter.getAllNodes();
        for (int i = 0; i < allNodes.size(); i++) {
            if (allNodes.get(i).isChecked()) {
                sb.append(allNodes.get(i).getName() + ",");
            }
        }
        String strNodesName = sb.toString();
        if (!TextUtils.isEmpty(strNodesName)) {
            Toast.makeText(this, strNodesName.substring(0, strNodesName.length() - 1), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.rly_bottom_iv_select, R.id.rly_bottom_btn_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rly_bottom_iv_select:
                break;
            case R.id.rly_bottom_btn_select:
                clickShow(view);
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
