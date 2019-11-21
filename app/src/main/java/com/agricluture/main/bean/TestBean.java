package com.agricluture.main.bean;

/**
 * @author zem
 * @date 2019/9/24.
 * description：
 */
public class TestBean {

    private String name;
    private String time;
    private int jdFlag;
    private String date;
    private String upTimes;
    private String noTimes;
    private int state;
    private int pageflag;
    private boolean isShow;

    public TestBean(String name, String time, int jdFlag, String date, String upTimes, String noTimes, int state, int pageflag,boolean isShow) {
        this.name = name;
        this.time = time;
        this.jdFlag = jdFlag;
        this.date = date;
        this.upTimes = upTimes;
        this.noTimes = noTimes;
        this.state = state;
        this.pageflag = pageflag;
        this.isShow =isShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getJdFlag() {
        return jdFlag;
    }

    public void setJdFlag(int jdFlag) {
        this.jdFlag = jdFlag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpTimes() {
        return upTimes;
    }

    public void setUpTimes(String upTimes) {
        this.upTimes = upTimes;
    }

    public String getNoTimes() {
        return noTimes;
    }

    public void setNoTimes(String noTimes) {
        this.noTimes = noTimes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPageflag() {
        return pageflag;
    }

    public void setPageflag(int pageflag) {
        this.pageflag = pageflag;
    }
}
