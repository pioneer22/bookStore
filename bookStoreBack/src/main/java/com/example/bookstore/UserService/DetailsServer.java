package main.java.com.example.bookstore.UserService;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface DetailsServer {

     ArrayList getComment(int bookId);//获取评论
     Book getOneBook(int bookId);//获取跳转页书的信息
     boolean changeUserImg(User user);//修改用户头像
     User getUserInfo(String userId);//获取用户信息

}
