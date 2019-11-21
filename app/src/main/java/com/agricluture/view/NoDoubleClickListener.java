package com.agricluture.view;

import android.view.View;
import android.view.View.OnClickListener;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;


/**
 * 禁止连续点击
 * Created by ${zem} on 2018/3/15.
 *
 * @author zem
 */

public abstract class NoDoubleClickListener implements OnClickListener {

    /**
     * 点击间隔
     */
    private static final int MIN_CLICK_DELAY_TIME = 800;
    private long lastClickTime = 0;

    public NoDoubleClickListener() {
    }

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);

    public static boolean isFastTwiceClick(View view) {
        Object object = view.getTag(view.getId());
        if (ObjectUtils.isEmpty(object)) {
            object = 0L;
        }
        if (object instanceof Long) {
            Long currentTime = System.currentTimeMillis();
            Long lastClickTime = (Long) object;
            LogUtils.d("isFastTwiceClick!!!", currentTime + "---" + lastClickTime + "____" + (currentTime - lastClickTime));
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                view.setTag(view.getId(), currentTime);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
