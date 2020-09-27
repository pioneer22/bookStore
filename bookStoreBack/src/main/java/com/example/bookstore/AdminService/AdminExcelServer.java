package main.java.com.example.bookstore.AdminService;

import com.example.bookstore.pojo.Book;

import java.util.List;
import java.util.Map;

public interface AdminExcelServer {
    Map<String,Object> getOrderMess(); //获取订单信息

    List<Book> getBookMess(); //获取图书信息

    int importExcel(List<Map<String, Object>> books); //一键上传图书
}
