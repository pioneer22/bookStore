package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class Comment {
    private Integer comId;

    private String userId;

    private Integer bookId;

    private String comMess;

    private Date comTime;

    private Integer comStatus;

    private String picAddress;

    private String comReply;

    private Date replyTime;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getComMess() {
        return comMess;
    }

    public void setComMess(String comMess) {
        this.comMess = comMess == null ? null : comMess.trim();
    }

    public Date getComTime() {
        return comTime;
    }

    public void setComTime(Date comTime) {
        this.comTime = comTime;
    }

    public Integer getComStatus() {
        return comStatus;
    }

    public void setComStatus(Integer comStatus) {
        this.comStatus = comStatus;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress == null ? null : picAddress.trim();
    }

    public String getComReply() {
        return comReply;
    }

    public void setComReply(String comReply) {
        this.comReply = comReply == null ? null : comReply.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}