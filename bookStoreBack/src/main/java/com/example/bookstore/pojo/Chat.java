package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class Chat {
    private Integer chatId;

    private String userId;

    private String businessId;

    private Date sendTime;

    private String context;

    private Integer statu;

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }
}