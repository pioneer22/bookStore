package main.java.com.example.bookstore.utils.dto;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Item;

/**
 * 用于关联 条目和书籍信息
 * 用户订单使用
 */
public class ItemAndBookDto {

    private Item item;

    private Book book;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
