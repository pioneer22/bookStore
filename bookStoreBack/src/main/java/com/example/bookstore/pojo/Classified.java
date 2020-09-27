package main.java.com.example.bookstore.pojo;

public class Classified {
    private Integer classifiedId;

    private String classifiedName;

    public Integer getClassifiedId() {
        return classifiedId;
    }

    public void setClassifiedId(Integer classifiedId) {
        this.classifiedId = classifiedId;
    }

    public String getClassifiedName() {
        return classifiedName;
    }

    public void setClassifiedName(String classifiedName) {
        this.classifiedName = classifiedName == null ? null : classifiedName.trim();
    }
}