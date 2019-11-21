package com.agricluture.main;

import com.agricluture.http.https.HttpUtils;
import com.agriculture.R;
import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cookie.CookieManger;

import androidx.multidex.MultiDexApplication;
import me.jessyan.autosize.AutoSizeConfig;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zem
 * @date 2019/2/25.
 * description：
 */
public class MyApplication extends MultiDexApplication {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化工具类
        Utils.init(this);
        instance = this;
        initHttp();
        AutoSizeConfig.getInstance().setCustomFragment(true);


//初始化推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
//        //初始化友盟分享
//        UMConfigure.init(this, "5d0af0350cafb270b4000d24"
//          , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
//
//        PlatformConfig.setWeixin("wx5b775b2e20976b4c", "df6f488f4efe80d733b49a2f1c0d4df2");
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            // 指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }


    public static MyApplication getInstance() {
        return instance;
    }

    public void initHttp() {

        //设置请求头
        EasyHttp.init(instance);
        EasyHttp.getInstance().debug("RxEasyHttp", true)
                .addConverterFactory(GsonConverterFactory.create())
                //如果不想让本库管理cookie,以下不需要
                .setCookieStore(new CookieManger(this)) //cookie持久化存储，如果cookie不过期，则一直有效
                .setReadTimeOut(30 * 1000)
                .setWriteTimeOut(30 * 1000)
                .setConnectTimeout(30 * 1000)
                .setRetryCount(0)
                .setBaseUrl(HttpUtils.getInstance().getBaseUrl());

    }
}
