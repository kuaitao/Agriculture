package com.agricluture.mine.bean;

/**
 * @author zem
 * @date 2019/10/14.
 * description：
 */
public class PersonalEvent {

    public int flags;
    public String contents;

    public PersonalEvent(int flags, String contents) {
        this.flags = flags;
        this.contents = contents;
    }

    public int getFlags() {
        return flags;
    }

    public String getContents() {
        return contents;
    }
}
