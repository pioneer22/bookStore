package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class Message {
    private Integer paperId;

    private String userId;

    private Integer paperState;

    private Date sendTime;

    private String sendMess;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getPaperState() {
        return paperState;
    }

    public void setPaperState(Integer paperState) {
        this.paperState = paperState;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendMess() {
        return sendMess;
    }

    public void setSendMess(String sendMess) {
        this.sendMess = sendMess == null ? null : sendMess.trim();
    }
}