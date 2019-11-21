package com.agricluture.homepage.bean;

import java.util.List;

/**
 * @author zem
 * @date 2019/10/30.
 * description：
 */
public class PurchaseBean {


    /**
     * applicant : 数据采集员3
     * orgName : 燕郊开发区
     * stage : 010-2019
     * dateTime : 2019-10-08
     * products : [{"productName":"第0号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第1号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第2号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第3号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第4号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第5号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第6号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第7号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第8号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第9号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第10号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第11号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null},{"productName":"第12号蔬菜","unit":null,"specification":null,"sellingPrice":null,"marketPrice":null}]
     */

    private String applicant;
    private String orgName;
    private String stage;
    private String dateTime;
    private List<ProductsBean> products;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * productName : 第0号蔬菜
         * unit : null
         * specification : null
         * sellingPrice : null
         * marketPrice : null
         */

        private String productName;
        private String unit;
        private String specification;
        private String sellingPrice;
        private String marketPrice;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(String sellingPrice) {
            this.sellingPrice = sellingPrice;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }
    }
}
