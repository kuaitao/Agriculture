package com.agricluture.main.bean;

/**
 * @author zem
 * @date 2019/10/31.
 * description：
 */
public class PopSelectBean {

    public String name;
    //1 已选
    public int flag;

    public PopSelectBean(String name, int flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
