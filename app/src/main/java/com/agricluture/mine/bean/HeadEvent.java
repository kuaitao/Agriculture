package com.agricluture.mine.bean;

/**
 * @author zem
 * @date 2019/10/14.
 * description：
 */
public class HeadEvent {

    public String headString;
    public boolean isGet;

    public HeadEvent(String headString, boolean isGet) {
        this.headString = headString;
        this.isGet = isGet;
    }

    public String getHeadString() {
        return headString;
    }

    public boolean isGet() {
        return isGet;
    }
}
