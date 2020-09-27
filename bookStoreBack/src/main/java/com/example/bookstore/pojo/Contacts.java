package main.java.com.example.bookstore.pojo;

public class Contacts {
    private Integer contactId;

    private String contactPhone;

    private String contactAddress;

    private String contactName;

    private String userId;

    private Integer statu;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress == null ? null : contactAddress.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }
}