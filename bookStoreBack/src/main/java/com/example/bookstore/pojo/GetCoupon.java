package main.java.com.example.bookstore.pojo;

public class GetCoupon {
    private Integer getCouponId;

    private Integer couponId;

    private Integer couponNum;

    private String userId;

    public Integer getGetCouponId() {
        return getCouponId;
    }

    public void setGetCouponId(Integer getCouponId) {
        this.getCouponId = getCouponId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}