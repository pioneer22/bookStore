package main.java.com.example.bookstore.utils.dto;

import com.example.bookstore.pojo.AllOrder;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Contacts;

import java.util.List;

public class GetOrderDto {
    /**
     * 获取订单时 DTO
     */
    private Contacts contacts;

    private AllOrder allOrder;

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public AllOrder getAllOrder() {
        return allOrder;
    }

    public void setAllOrder(AllOrder allOrder) {
        this.allOrder = allOrder;
    }

    public List<BookMess> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookMess> bookList) {
        this.bookList = bookList;
    }

    private List<BookMess> bookList;

    public static class BookMess{
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        private Book book;
    }
}
