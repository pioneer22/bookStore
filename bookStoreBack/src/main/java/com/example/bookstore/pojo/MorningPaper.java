package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class MorningPaper {
    private Integer paperId;

    private Date paperTime;

    private String paperMess;

    private String businessId;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Date getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(Date paperTime) {
        this.paperTime = paperTime;
    }

    public String getPaperMess() {
        return paperMess;
    }

    public void setPaperMess(String paperMess) {
        this.paperMess = paperMess == null ? null : paperMess.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }
}