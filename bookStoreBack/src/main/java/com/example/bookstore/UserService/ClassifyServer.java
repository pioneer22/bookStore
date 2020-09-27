package main.java.com.example.bookstore.UserService;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Classified;

import java.util.List;

public interface ClassifyServer {

    public List<Classified> getClassify();//获得分类
    public List<Book> getClassifyBook(int classifiedId);//获得分类图书详情
}
