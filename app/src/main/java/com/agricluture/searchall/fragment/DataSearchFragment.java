package com.agricluture.searchall.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.agricluture.base.BaseFragment;
import com.agricluture.http.https.HttpResultBean;
import com.agricluture.http.https.HttpUtils;
import com.agricluture.http.https.SimpleCallBack;
import com.agricluture.main.bean.ProductNameBean;
import com.agricluture.main.bean.TableNameBean;
import com.agricluture.main.fragment.SearchFragment;
import com.agricluture.searchall.SearchResultActivity;
import com.agricluture.utils.GsonUtil;
import com.agricluture.utils.JumperUtils;
import com.agricluture.utils.Utils;
import com.agriculture.R;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhouyou.http.exception.ApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zem
 * @date 2019/10/7.
 * description：
 */
public class DataSearchFragment extends BaseFragment {
    @BindView(R.id.fd_et_bname)
    TextView fdEtBname;
    @BindView(R.id.s_bname)
    TextView sBname;
    @BindView(R.id.fd_et_pname)
    TextView fdEtPname;
    @BindView(R.id.fd_et_show)
    TextView fdEtShow;
    @BindView(R.id.fd_lly_show)
    LinearLayout fdLlyShow;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    @BindView(R.id.fd_tv_starttime)
    TextView fdTvStarttime;
    @BindView(R.id.fd_tv_endtime)
    TextView fdTvEndtime;

    private TimePickerView startTimeView, endTimeView;
    private OptionsPickerView areaSelect;
    private OptionsPickerView tableSelect;
    private OptionsPickerView dataSelect;
    private long startTimeLong = 0, endTimeLong = 0;
    private String starTimeStr, endTimeStr;
    List<String> options1Items = new ArrayList<>();
    List<String> optionsAItems = new ArrayList<>();
    List<String> dataItem = new ArrayList<>();
    @Override
    protected int layoutViewId() {
        return R.layout.fragment_data_search;
    }

    @Override
    protected void initViews() {
        fdEtShow.setText("按地区");

        dataItem.add("按地区");
        dataItem.add("按期别");
        dataSelect = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String tx = dataItem.get(options1);
                fdEtShow.setText(tx);
            }
        }).build();
        dataSelect.setPicker(dataItem, null, null);


        startTimeView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                starTimeStr = fdTvStarttime.getText().toString();
                endTimeStr = fdTvEndtime.getText().toString();


                if ("结束时间".equals(endTimeStr)) {
                    fdTvStarttime.setText(sdf.format(date));
                    //  myRefresh();
                } else {
                    try {
                        startTimeLong = sdf.parse(sdf.format(date)).getTime();
                        endTimeLong = sdf.parse(endTimeStr).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (startTimeLong >= endTimeLong) {
                        ToastUtils.showLong("开始时间必须小于结束时间");

                    } else {
                        fdTvStarttime.setText(sdf.format(date));
                        // myRefresh();
                    }
                }


            }
        }).setType(new boolean[]{true, true, true, false, false, false}).build();

        endTimeView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                starTimeStr = fdTvStarttime.getText().toString();
                endTimeStr = fdTvEndtime.getText().toString();


                if ("开始时间".equals(starTimeStr)) {
                    fdTvEndtime.setText(sdf.format(date));
                    //  myRefresh();
                } else {
                    try {
                        startTimeLong = sdf.parse(starTimeStr).getTime();
                        endTimeLong = sdf.parse(sdf.format(date)).getTime();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (startTimeLong >= endTimeLong) {
                        ToastUtils.showLong("结束时间必须大于开始时间");

                    } else {
                        fdTvEndtime.setText(sdf.format(date));
                        //   myRefresh();
                    }
                }

            }
        }).setType(new boolean[]{true, true, true, false, false, false}).build();
    }
   private   List<ProductNameBean>  productNameBean;
    @Override
    protected void initData() {
        HttpUtils.getInstance().searchApi.postProductName(new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if(null!=s.getData()){
                     productNameBean = GsonUtil.getRealListFromT(s.getData(),ProductNameBean[].class);

                    for (int i = 0; i <productNameBean.size() ; i++) {
                        optionsAItems.add(productNameBean.get(i).getProductname());
                    }
                    setShowProductName();
                }



            }

            @Override
            public void onError(ApiException t) {

            }
        });
        HttpUtils.getInstance().searchApi.postTableName(new SimpleCallBack<HttpResultBean>() {
            @Override
            public void onSuccess(HttpResultBean s) {
                if(null!=s.getData()){
                    List<TableNameBean>  tableNameBeanList= GsonUtil.getRealListFromT(s.getData(),TableNameBean[].class);

                    for (int i = 0; i < tableNameBeanList.size(); i++) {
                        options1Items.add(tableNameBeanList.get(i).getTaskname());
                    }
                    setShowTable();
                }

            }

            @Override
            public void onError(ApiException t) {

            }
        });
    }
    private void setShowProductName(){
        areaSelect = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String tx = optionsAItems.get(options1);
                fdEtPname.setText(tx);
            }
        }).build();
        areaSelect.setPicker(optionsAItems, null, null);
    }
    private void setShowTable(){
        tableSelect = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String tx = options1Items.get(options1);
                fdEtBname.setText(tx);
            }
        }).build();
        tableSelect.setPicker(options1Items, null, null);
    }

    @OnClick({R.id.fd_tv_starttime, R.id.fd_tv_endtime, R.id.login_btn_to, R.id.fd_lly_bname, R.id.fd_lly_pname, R.id.fd_lly_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fd_tv_starttime:
                startTimeView.show();
                break;
            case R.id.fd_tv_endtime:
                endTimeView.show();
                break;
            case R.id.login_btn_to:
                if(ObjectUtils.isEmpty(fdEtShow.getText().toString())){
                    Toast.makeText(activity, "请选择展示方式！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle =new Bundle();
                bundle.putString("selectType",fdEtShow.getText().toString());
                bundle.putParcelableArrayList("product",(ArrayList)productNameBean);
                JumperUtils.JumpTo(activity, SearchResultActivity.class,bundle);
                break;
            case R.id.fd_lly_bname:

                if(null!=tableSelect){
                    tableSelect.show();
                }
                break;

            case R.id.fd_lly_pname:
                //表名
                if(null!=areaSelect) {
                    areaSelect.show();
                }

                break;
            case R.id.fd_lly_show:
                //展示
                dataSelect.show();
                break;
            default:
        }
    }

    private void showDataDialog() {


        View popupView = activity.getLayoutInflater().inflate(R.layout.pop_data_list, null);
        final PopupWindow window = new PopupWindow(popupView,Utils.dip2px(activity, 208), ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView one = popupView.findViewById(R.id.one);
        TextView two = popupView.findViewById(R.id.two);
        window.setAnimationStyle(R.style.popup_window_anim);
        window.setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.colorababab)));
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(fdLlyShow, 0, Utils.dip2px(activity, 5));
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fdEtShow.setText("按地区");
                window.dismiss();
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fdEtShow.setText("按期别");
                window.dismiss();
            }
        });

    }
}
