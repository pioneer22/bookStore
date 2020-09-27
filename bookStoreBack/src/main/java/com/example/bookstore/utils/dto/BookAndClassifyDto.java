package main.java.com.example.bookstore.utils.dto;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Classified;

import java.util.List;

/**
 * 获取图书和对应分类
 */

public class BookAndClassifyDto {

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Classified> getClassify() {
        return classify;
    }

    public void setClassify(List<Classified> classify) {
        this.classify = classify;
    }

    private List<Classified> classify;
}
