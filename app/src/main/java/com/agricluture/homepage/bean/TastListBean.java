package com.agricluture.homepage.bean;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author zem
 * @date 2019/10/9.
 * description：
 */
public class TastListBean {


    private String createTime;
    private String name;
    private List<StreamsBean> streams;

    public TastListBean(String createTime, String name, List<StreamsBean> streams) {
        this.createTime = createTime;
        this.name = name;
        this.streams = streams;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StreamsBean> getStreams() {
        return streams;
    }

    public void setStreams(List<StreamsBean> streams) {
        this.streams = streams;
    }

    public static class StreamsBean {

        private String name;
        private String guige;
        private String gongjing;
        private String  chushoujia;
        private String shichangjia;
        private String agochushoujia;
        private String agonshichangjia;

        public StreamsBean(String name,String guige, String gongjing, String chushoujia, String shichangjia, String agochushoujia, String agonshichangjia) {
            this.guige = guige;
            this.gongjing = gongjing;
            this.chushoujia = chushoujia;
            this.shichangjia = shichangjia;
            this.agochushoujia = agochushoujia;
            this.agonshichangjia = agonshichangjia;
            this.name =name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGuige() {
            return guige;
        }

        public void setGuige(String guige) {
            this.guige = guige;
        }

        public String getGongjing() {
            return gongjing;
        }

        public void setGongjing(String gongjing) {
            this.gongjing = gongjing;
        }

        public String getChushoujia() {
            return chushoujia;
        }

        public void setChushoujia(String chushoujia) {
            this.chushoujia = chushoujia;
        }

        public String getShichangjia() {
            return shichangjia;
        }

        public void setShichangjia(String shichangjia) {
            this.shichangjia = shichangjia;
        }

        public String getAgochushoujia() {
            return agochushoujia;
        }

        public void setAgochushoujia(String agochushoujia) {
            this.agochushoujia = agochushoujia;
        }

        public String getAgonshichangjia() {
            return agonshichangjia;
        }

        public void setAgonshichangjia(String agonshichangjia) {
            this.agonshichangjia = agonshichangjia;
        }
    }

    @Override
    public String toString() {
        return "TastListBean{" +
                "createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                ", streams=" + streams +
                '}';
    }
}
