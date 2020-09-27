package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class Coupon {
    private Integer couponId;

    private String couponImg;

    private Integer couponUse;

    private Integer couponMoney;

    private Date couponVaild;

    private Date couponTime;

    private Integer couponNum;

    private String businessId;

    private Integer couponGetnum;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponImg() {
        return couponImg;
    }

    public void setCouponImg(String couponImg) {
        this.couponImg = couponImg == null ? null : couponImg.trim();
    }

    public Integer getCouponUse() {
        return couponUse;
    }

    public void setCouponUse(Integer couponUse) {
        this.couponUse = couponUse;
    }

    public Integer getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(Integer couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Date getCouponVaild() {
        return couponVaild;
    }

    public void setCouponVaild(Date couponVaild) {
        this.couponVaild = couponVaild;
    }

    public Date getCouponTime() {
        return couponTime;
    }

    public void setCouponTime(Date couponTime) {
        this.couponTime = couponTime;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public Integer getCouponGetnum() {
        return couponGetnum;
    }

    public void setCouponGetnum(Integer couponGetnum) {
        this.couponGetnum = couponGetnum;
    }
}