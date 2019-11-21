package com.agricluture.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zem
 * @date 2019/10/29.
 * description：
 */
public class ProductNameBean implements Parcelable {


    /**
     * productid : 1
     * productname : 早籼米
     */

    private String productid;
    private String productname;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productid);
        dest.writeString(this.productname);
    }

    public ProductNameBean() {
    }

    protected ProductNameBean(Parcel in) {
        this.productid = in.readString();
        this.productname = in.readString();
    }

    public static final Parcelable.Creator<ProductNameBean> CREATOR = new Parcelable.Creator<ProductNameBean>() {
        @Override
        public ProductNameBean createFromParcel(Parcel source) {
            return new ProductNameBean(source);
        }

        @Override
        public ProductNameBean[] newArray(int size) {
            return new ProductNameBean[size];
        }
    };
}
