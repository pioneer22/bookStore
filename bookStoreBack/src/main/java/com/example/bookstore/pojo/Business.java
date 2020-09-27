package main.java.com.example.bookstore.pojo;

public class Business {
    private String businessId;

    private String businessPass;

    private String businessName;

    private String businessPhone;

    private String businessImg;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getBusinessPass() {
        return businessPass;
    }

    public void setBusinessPass(String businessPass) {
        this.businessPass = businessPass == null ? null : businessPass.trim();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone == null ? null : businessPhone.trim();
    }

    public String getBusinessImg() {
        return businessImg;
    }

    public void setBusinessImg(String businessImg) {
        this.businessImg = businessImg == null ? null : businessImg.trim();
    }
}