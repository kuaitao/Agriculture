package com.agricluture.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDetailBean implements Parcelable {

    /**
     * id : 08a09027-cf4f-4a36-8991-2b498970c7f1
     * userid : 11D08F00-AE90-47A8-BB93-47F1FA4D5123
     * strrealname : 数据采集员6
     * strpicturepath : https://img14.360buyimg.com/n1/jfs/t227208d39c1a7b927.jpg
     * strnickname : 111
     * bitsex : 1
     * strmobilephone : 135XXXXXXX1
     * strorgid : 0
     * strorgname : 河北省省厅
     * birthdaytime : 2019-10-27T16:00:00.000+0000
     * intconstellation : 1
     * provinceid : 25e3ca3d-e545-419f-881d-226859ba7053
     * cityid : 1
     * districtid : c21966e8-1e23-47b8-9c61-09e346077bb4
     * strliveaddress : 燕郊开发区大街甲108号
     * strlivezipcode : 11
     * stroriginaladdress : 11
     * stroriginalzipcode : 11
     * originaladdress : 11
     * originalzipcode : 11
     * strremark : 11
     * createuserid : 1
     * createtime : 2019-10-27T16:00:00.000+0000
     * updateuserid : 1
     * updatetime : 2019-10-27T16:00:00.000+0000
     */

    private String id;
    private String userid;
    private String strrealname;
    private String strpicturepath;
    private String strnickname;
    private String bitsex;
    private String strmobilephone;
    private String strorgid;
    private String strorgname;
    private String birthdaytime;
    private int intconstellation;
    private String provinceid;
    private String cityid;
    private String districtid;
    private String strliveaddress;
    private String strlivezipcode;
    private String stroriginaladdress;
    private String stroriginalzipcode;
    private String originaladdress;
    private String originalzipcode;
    private String strremark;
    private int createuserid;
    private String createtime;
    private int updateuserid;
    private String updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStrrealname() {
        return strrealname;
    }

    public void setStrrealname(String strrealname) {
        this.strrealname = strrealname;
    }

    public String getStrpicturepath() {
        return strpicturepath;
    }

    public void setStrpicturepath(String strpicturepath) {
        this.strpicturepath = strpicturepath;
    }

    public String getStrnickname() {
        return strnickname;
    }

    public void setStrnickname(String strnickname) {
        this.strnickname = strnickname;
    }

    public String getBitsex() {
        return bitsex;
    }

    public void setBitsex(String bitsex) {
        this.bitsex = bitsex;
    }

    public String getStrmobilephone() {
        return strmobilephone;
    }

    public void setStrmobilephone(String strmobilephone) {
        this.strmobilephone = strmobilephone;
    }

    public String getStrorgid() {
        return strorgid;
    }

    public void setStrorgid(String strorgid) {
        this.strorgid = strorgid;
    }

    public String getStrorgname() {
        return strorgname;
    }

    public void setStrorgname(String strorgname) {
        this.strorgname = strorgname;
    }

    public String getBirthdaytime() {
        return birthdaytime;
    }

    public void setBirthdaytime(String birthdaytime) {
        this.birthdaytime = birthdaytime;
    }

    public int getIntconstellation() {
        return intconstellation;
    }

    public void setIntconstellation(int intconstellation) {
        this.intconstellation = intconstellation;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getStrliveaddress() {
        return strliveaddress;
    }

    public void setStrliveaddress(String strliveaddress) {
        this.strliveaddress = strliveaddress;
    }

    public String getStrlivezipcode() {
        return strlivezipcode;
    }

    public void setStrlivezipcode(String strlivezipcode) {
        this.strlivezipcode = strlivezipcode;
    }

    public String getStroriginaladdress() {
        return stroriginaladdress;
    }

    public void setStroriginaladdress(String stroriginaladdress) {
        this.stroriginaladdress = stroriginaladdress;
    }

    public String getStroriginalzipcode() {
        return stroriginalzipcode;
    }

    public void setStroriginalzipcode(String stroriginalzipcode) {
        this.stroriginalzipcode = stroriginalzipcode;
    }

    public String getOriginaladdress() {
        return originaladdress;
    }

    public void setOriginaladdress(String originaladdress) {
        this.originaladdress = originaladdress;
    }

    public String getOriginalzipcode() {
        return originalzipcode;
    }

    public void setOriginalzipcode(String originalzipcode) {
        this.originalzipcode = originalzipcode;
    }

    public String getStrremark() {
        return strremark;
    }

    public void setStrremark(String strremark) {
        this.strremark = strremark;
    }

    public int getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(int createuserid) {
        this.createuserid = createuserid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(int updateuserid) {
        this.updateuserid = updateuserid;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userid);
        dest.writeString(this.strrealname);
        dest.writeString(this.strpicturepath);
        dest.writeString(this.strnickname);
        dest.writeString(this.bitsex);
        dest.writeString(this.strmobilephone);
        dest.writeString(this.strorgid);
        dest.writeString(this.strorgname);
        dest.writeString(this.birthdaytime);
        dest.writeInt(this.intconstellation);
        dest.writeString(this.provinceid);
        dest.writeString(this.cityid);
        dest.writeString(this.districtid);
        dest.writeString(this.strliveaddress);
        dest.writeString(this.strlivezipcode);
        dest.writeString(this.stroriginaladdress);
        dest.writeString(this.stroriginalzipcode);
        dest.writeString(this.originaladdress);
        dest.writeString(this.originalzipcode);
        dest.writeString(this.strremark);
        dest.writeInt(this.createuserid);
        dest.writeString(this.createtime);
        dest.writeInt(this.updateuserid);
        dest.writeString(this.updatetime);
    }

    public UserDetailBean() {
    }

    protected UserDetailBean(Parcel in) {
        this.id = in.readString();
        this.userid = in.readString();
        this.strrealname = in.readString();
        this.strpicturepath = in.readString();
        this.strnickname = in.readString();
        this.bitsex = in.readString();
        this.strmobilephone = in.readString();
        this.strorgid = in.readString();
        this.strorgname = in.readString();
        this.birthdaytime = in.readString();
        this.intconstellation = in.readInt();
        this.provinceid = in.readString();
        this.cityid = in.readString();
        this.districtid = in.readString();
        this.strliveaddress = in.readString();
        this.strlivezipcode = in.readString();
        this.stroriginaladdress = in.readString();
        this.stroriginalzipcode = in.readString();
        this.originaladdress = in.readString();
        this.originalzipcode = in.readString();
        this.strremark = in.readString();
        this.createuserid = in.readInt();
        this.createtime = in.readString();
        this.updateuserid = in.readInt();
        this.updatetime = in.readString();
    }

    public static final Parcelable.Creator<UserDetailBean> CREATOR = new Parcelable.Creator<UserDetailBean>() {
        @Override
        public UserDetailBean createFromParcel(Parcel source) {
            return new UserDetailBean(source);
        }

        @Override
        public UserDetailBean[] newArray(int size) {
            return new UserDetailBean[size];
        }
    };
}
