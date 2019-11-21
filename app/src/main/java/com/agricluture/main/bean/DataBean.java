package com.agricluture.main.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author zem
 * @date 2019/10/8.
 * description：
 */
public class DataBean implements MultiItemEntity {
    public static final int SINGLE = 1;
    public static final int DOUBLE = 2;
    public String title;
    public int yesNum;
    public int noNum;
    private String stats;//1.处理中 0其他
    public int type;

    public DataBean(String title, int yesNum, int noNum, String stats, int type) {
        this.title = title;
        this.yesNum = yesNum;
        this.noNum = noNum;
        this.stats = stats;
        this.type = type;
    }

    public static int getSINGLE() {
        return SINGLE;
    }

    public static int getDOUBLE() {
        return DOUBLE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYesNum() {
        return yesNum;
    }

    public void setYesNum(int yesNum) {
        this.yesNum = yesNum;
    }

    public int getNoNum() {
        return noNum;
    }

    public void setNoNum(int noNum) {
        this.noNum = noNum;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
