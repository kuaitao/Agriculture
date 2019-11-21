package com.agricluture.homepage.bean;

/**
 * @author zem
 * @date 2019/10/11.
 * description：
 */
public class EventTastChildren {

    private int fistPositon;
    private int thisPositon;
    private String contents;
    private String other;

    public EventTastChildren(int fistPositon, int thisPositon, String contents, String other) {
        this.fistPositon = fistPositon;
        this.thisPositon = thisPositon;
        this.contents = contents;
        this.other = other;
    }

    public int getFistPositon() {
        return fistPositon;
    }

    public int getThisPositon() {
        return thisPositon;
    }

    public String getContents() {
        return contents;
    }

    public String getOther() {
        return other;
    }
}
