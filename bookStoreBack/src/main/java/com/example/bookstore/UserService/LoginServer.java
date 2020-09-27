package main.java.com.example.bookstore.UserService;

import com.example.bookstore.pojo.Business;

import java.util.List;

public interface LoginServer {

    List<Business> bussinessLogin(String businessId, String businessPass);//商家登陆

}
