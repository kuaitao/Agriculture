package com.agricluture.http.https;

import com.agricluture.http.api.HomeApi;
import com.agricluture.http.api.MessageApi;
import com.agricluture.http.api.MineApi;
import com.agricluture.http.api.SearchApi;
import com.agricluture.http.api.UpdataApi;

/**
 * Created by zem on 2018/12/6
 */
public class HttpUtils {

    /**
     * 打包修改环境
     */
    private EnvironmentalEnum environmental = EnvironmentalEnum.DEV;

    /**
     * 环境变量
     */
    public enum EnvironmentalEnum {
        DEV, QA, RELEASE, ONLINE
    }

    private volatile static HttpUtils singleton = null;

    public static HttpUtils getInstance() {
        if (singleton == null) {
            synchronized (HttpUtils.class) {
                if (singleton == null) {
                    singleton = new HttpUtils();
                }
            }
        }
        return singleton;
    }

    //主页
    public HomeApi homeApi;
    public SearchApi searchApi;
    public UpdataApi updataApi;
    public MessageApi messageApi;
    public MineApi mineApi;
    private HttpUtils() {
        homeApi = new HomeApi();
        searchApi =new SearchApi();
        updataApi= new UpdataApi();
        messageApi =new MessageApi();
        mineApi=new MineApi();
    }
    /**
     * precision服务器的baseUrl
     */
    public String getBaseUrl() {
        if (EnvironmentalEnum.DEV == environmental) {
            return BASE_URL_DEV;
        } else if (EnvironmentalEnum.RELEASE == environmental) {
            return BASE_URL_RELEASE;
        }
        return BASE_URL_DEV;
    }


    /**
     * 测试环境
     */
    private static final String BASE_URL_DEV = "http://farm.ymforever.com:6996/";
    /**
     * 正式环境
     */
    private static final String BASE_URL_RELEASE = "https://scm.kunlunlubricating.com/";


}