package main.java.com.example.bookstore.pojo;

import java.util.Date;

public class Book {
    private Integer bookId;

    private String bookAuthor;

    private String bookPress;

    private String bookImg;

    private Double bookPrice;

    private Date bookTime;

    private Integer bookNum;

    private String bookIntro;

    private String bookName;

    private Integer bookSale;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress == null ? null : bookPress.trim();
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg == null ? null : bookImg.trim();
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public Integer getBookNum() {
        return bookNum;
    }

    public void setBookNum(Integer bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookIntro() {
        return bookIntro;
    }

    public void setBookIntro(String bookIntro) {
        this.bookIntro = bookIntro == null ? null : bookIntro.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Integer getBookSale() {
        return bookSale;
    }

    public void setBookSale(Integer bookSale) {
        this.bookSale = bookSale;
    }
}