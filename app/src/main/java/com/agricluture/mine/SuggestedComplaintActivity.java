package com.agricluture.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.agricluture.base.BaseActivity;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agriculture.R;
import com.blankj.utilcode.util.ObjectUtils;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuggestedComplaintActivity extends BaseActivity {


    @BindView(R.id.asc_iv_jy)
    ImageView ascIvJy;
    @BindView(R.id.asc_iv_tellme)
    ImageView ascIvTellme;
    @BindView(R.id.asc_et_content)
    EditText ascEtContent;

    /**
     * 1建议2投诉
     */
    private String selectFlag = "02";

    @Override
    protected int layoutViewId() {
        return R.layout.activity_suggested_complaint;
    }

    @Override
    protected void initViews() {
        initTitleBar(R.string.mine_tellme);

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.asc_lly_jy, R.id.asc_lly_tellme, R.id.asc_btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.asc_lly_jy:

                if ("01".equals(selectFlag)) {
                    selectClick();
                }


                break;
            case R.id.asc_lly_tellme:
                if ("02".equals(selectFlag)) {
                    selectClick();
                }
                break;
            case R.id.asc_btn_commit:
                String contents= ascEtContent.getText().toString();
                if (!ObjectUtils.isEmpty(contents)) {
                    HttpUtils.getInstance().mineApi.postSuggestedComplaint(selectFlag, contents, new SimpleCallBack<HttpResultBean>() {
                        @Override
                        public void onSuccess(HttpResultBean s) {

                            if (!ObjectUtils.isEmpty(s.getData())){
                                boolean isGo =(boolean) s.getData();
                                if(isGo){
                                    Toast.makeText(activity, "提交成功", Toast.LENGTH_SHORT).show();
                                    SuggestedComplaintActivity.this.finish();
                                }
                            }
                        }

                        @Override
                        public void onError(ApiException t) {

                        }
                    });
                }else{
                    Toast.makeText(activity, "请填写内容", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void selectClick() {
        if ("01".equals(selectFlag)) {

            selectFlag = "02";
            ascIvJy.setBackgroundResource(R.mipmap.double_select_in);
            ascIvTellme.setBackgroundResource(R.mipmap.double_select_out);

        } else {
            selectFlag = "01";
            ascIvJy.setBackgroundResource(R.mipmap.double_select_out);
            ascIvTellme.setBackgroundResource(R.mipmap.double_select_in);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
