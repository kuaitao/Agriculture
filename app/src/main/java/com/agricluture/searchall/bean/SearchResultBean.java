package com.agricluture.searchall.bean;


import java.util.List;

/**
 * @author zem
 * @date 2019/10/10.
 * description：
 */
public class SearchResultBean {


    public String name;
    private List<ChildrenFirstBean> childrenFirstBeanList;
    public boolean isShow;

    public SearchResultBean(String name, List<ChildrenFirstBean> childrenFirstBeanList,boolean isShow) {
        this.name = name;
        this.childrenFirstBeanList = childrenFirstBeanList;
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

    public List<ChildrenFirstBean> getChildrenFirstBeanList() {
        return childrenFirstBeanList;
    }

    public void setChildrenFirstBeanList(List<ChildrenFirstBean> childrenFirstBeanList) {
        this.childrenFirstBeanList = childrenFirstBeanList;
    }

    public static class ChildrenFirstBean {


        public String address;

        private List<ChildrenBean> childrenBeans;

        public ChildrenFirstBean(String address, List<ChildrenBean> childrenBeans) {
            this.address = address;
            this.childrenBeans = childrenBeans;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<ChildrenBean> getChildrenBeans() {
            return childrenBeans;
        }

        public void setChildrenBeans(List<ChildrenBean> childrenBeans) {
            this.childrenBeans = childrenBeans;
        }

        public static class ChildrenBean {

            public String data;

            public String personPrice;

            public String marketPrice;


            public ChildrenBean(String data, String personPrice, String marketPrice) {
                this.data = data;
                this.personPrice = personPrice;
                this.marketPrice = marketPrice;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getPersonPrice() {
                return personPrice;
            }

            public void setPersonPrice(String personPrice) {
                this.personPrice = personPrice;
            }

            public String getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(String marketPrice) {
                this.marketPrice = marketPrice;
            }
        }
    }
}
