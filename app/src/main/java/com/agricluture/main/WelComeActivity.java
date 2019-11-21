package com.agricluture.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agricluture.base.BaseActivity;

import com.agricluture.login.LoginActivity;
import com.agricluture.login.LoginSelectsActivity;
import com.agricluture.utils.JumperUtils;
import com.agricluture.utils.SharePrefrenceInto;
import com.agriculture.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelComeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;
    @BindView(R.id.Dots)
    View Dots;
    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.btn_next)
    Button btnNext;
    private GuideAdapter adapter;
    private TextView[] dots;

    private List<View> mViews = new ArrayList<View>();
    /**
     * 开机引导页的图片展示
     */
    private int[] mResIds = new int[]{
            R.mipmap.welcome_slide1,
            R.mipmap.welcome_slide2,
            R.mipmap.welcome_slide3,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //在应用的入口activity加入以下代码，解决首次安装应用，点击应用图标打开应用，点击home健回到桌面，再次点击应用图标，进入应用时多次初始化SplashActivity的问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
    }



    @Override
    protected int layoutViewId() {
        return R.layout.activity_wel_come;
    }

    @Override
    protected void initViews() {

        boolean islogin = SharePrefrenceInto.getInstance().readBooleanValue("isInto", false);
        if(islogin){
            Intent intent = new Intent(activity, IntoActivity.class);
            startActivity(intent);
            WelComeActivity.this.finish();
            return;
        }else {
            SharePrefrenceInto.getInstance().saveBooleanValue("isInto", true);
        }
    }

    @Override
    protected void initData() {
        for (int i = 0; i < mResIds.length; i++) {
            ImageView img = new ImageView(this);
            //延迟设置图片，在PagerAdapter内设置，解决OOM问题
            /*ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            img.setBackgroundResource(mResIds[i]);
            img.setLayoutParams(params);*/
            mViews.add(img);
        }
        adapter = new GuideAdapter(mViews);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
    }




    @OnClick({R.id.btn_skip, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_skip:
                launchHomeScreen();
                break;
            case R.id.btn_next:
                int current = getItem(+1);
                if(current < mResIds.length){
                    viewPager.setCurrentItem(current);
                }else{
                    launchHomeScreen();
                }
                break;
                default:
        }
    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    //  进行页面跳转进入app
    private void launchHomeScreen(){
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        WelComeActivity.this.finish();

    }
    class GuideAdapter extends PagerAdapter {
        private List<View> views;

        public GuideAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //在此设置背景图片，提高加载速度，解决OOM问题
            View view = views.get(position);
            int count = getCount();
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            view.setBackgroundResource(mResIds[position % count]);
            view.setLayoutParams(params);
            container.addView(view, 0);
            return views.get(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == mResIds.length - 1){
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText("进入");
            btnSkip.setVisibility(View.GONE);
        }else{
            btnSkip.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
