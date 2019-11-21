package com.agricluture.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.agricluture.utils.InputMethodUtils;
import com.agricluture.view.NoDoubleClickListener;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.internal.CustomAdapt;

/**
 * Created by zem on 2018/11/5.
 * 类描述:基类fragment
 */
public abstract class BaseFragment extends Fragment implements CustomAdapt {

    protected FragmentActivity activity;
    protected View rootView;
    private Unbinder mUnbinder;
    protected boolean isViewInit;
    protected boolean isDataInit;
    public Fragment fragmentSelf;

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected int pageNumber = 1, pageSize = 10;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(layoutViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        if (isRegisterEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        InputMethodUtils.hideSoftInput(getActivity());
        solveOverlap(savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentSelf = this;
        activity = getActivity();
        isViewInit = true;
        initViews();
        initData();
        lazyLoad();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 是否开启eventBus
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    protected abstract int layoutViewId();

    protected abstract void initViews();


    protected abstract void initData();

    private void lazyLoad() {
        if (getUserVisibleHint() && isViewInit && !isDataInit) {
            onLazyLoad();
            isDataInit = true;
        }
    }

    protected void onLazyLoad() {
    }


    private void solveOverlap(@Nullable Bundle savedInstanceState) {//解决重叠 非必现的bug无法确认是否有效
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    public boolean isFastTwiceClick(View view) {
        return NoDoubleClickListener.isFastTwiceClick(view);
    }

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 0;
    }


    @Override
    public void onDestroy() {
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}