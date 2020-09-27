package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class Topic {
    private Integer topicId;

    private String topicImg;

    private Date topicTime;

    private String topicName;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg == null ? null : topicImg.trim();
    }

    public Date getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Date topicTime) {
        this.topicTime = topicTime;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName == null ? null : topicName.trim();
    }
}