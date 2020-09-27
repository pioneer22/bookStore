package main.java.com.example.bookstore.utils.dto;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Comment;

import java.util.List;

public class BookAndCommentDto {
    private Book book;
    private List<CommentList> commentList;


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<CommentList> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentList> commentList) {
        this.commentList = commentList;
    }

    public static class CommentList{
        private String userName;
        private String userImg;
        public Comment comment;

        public Comment getComment() {
            return comment;
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

    }
}
