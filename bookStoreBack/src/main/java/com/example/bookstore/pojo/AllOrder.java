package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class AllOrder {
    private String orderId;

    private String userId;

    private Integer contactId;

    private Date orderTime;

    private Integer orderStatus;

    private Double orderAllprice;

    private String userLeaveMess;

    private Integer messStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderAllprice() {
        return orderAllprice;
    }

    public void setOrderAllprice(Double orderAllprice) {
        this.orderAllprice = orderAllprice;
    }

    public String getUserLeaveMess() {
        return userLeaveMess;
    }

    public void setUserLeaveMess(String userLeaveMess) {
        this.userLeaveMess = userLeaveMess == null ? null : userLeaveMess.trim();
    }

    public Integer getMessStatus() {
        return messStatus;
    }

    public void setMessStatus(Integer messStatus) {
        this.messStatus = messStatus;
    }
}