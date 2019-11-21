package com.agricluture.main.bean;

/**
 * @author zem
 * @date 2019/10/29.
 * description：
 */
public class HomeRwBean {


    /**
     * state : 02
     * unreportNumber : 14
     * status : 91
     * attribute : 02
     * reportNumber : 314
     * strParentCode : null
     * strDistrict : 地方农业机构
     * strStage : O20-2019
     * tastId : dabcd4f3-2eeb-43a2-a6bc-c29f81e65f73
     * urgent : 0
     * taskLevel : 1
     * strAreaNO : 0
     * tastName : 全国主要经济作物和养殖产品价格统计
     * tastDistrictId : 82cb64b7-1b51-477c-932b-32434dc5c425
     */

    private String state;
    private int unreportNumber;
    private String status;
    private String attribute;
    private int reportNumber;
    private Object strParentCode;
    private String strDistrict;
    private String strStage;
    private String tastId;
    private String urgent;
    private String taskLevel;
    private String strAreaNO;
    private String tastName;
    private String tastDistrictId;
    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUnreportNumber() {
        return unreportNumber;
    }

    public void setUnreportNumber(int unreportNumber) {
        this.unreportNumber = unreportNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Object getStrParentCode() {
        return strParentCode;
    }

    public void setStrParentCode(Object strParentCode) {
        this.strParentCode = strParentCode;
    }

    public String getStrDistrict() {
        return strDistrict;
    }

    public void setStrDistrict(String strDistrict) {
        this.strDistrict = strDistrict;
    }

    public String getStrStage() {
        return strStage;
    }

    public void setStrStage(String strStage) {
        this.strStage = strStage;
    }

    public String getTastId() {
        return tastId;
    }

    public void setTastId(String tastId) {
        this.tastId = tastId;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    public String getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(String taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getStrAreaNO() {
        return strAreaNO;
    }

    public void setStrAreaNO(String strAreaNO) {
        this.strAreaNO = strAreaNO;
    }

    public String getTastName() {
        return tastName;
    }

    public void setTastName(String tastName) {
        this.tastName = tastName;
    }

    public String getTastDistrictId() {
        return tastDistrictId;
    }

    public void setTastDistrictId(String tastDistrictId) {
        this.tastDistrictId = tastDistrictId;
    }
}
