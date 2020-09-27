package main.java.com.example.bookstore.UserService;

import java.util.List;

public interface ShopCarServer {

    List getShopCar(String userId);//获取购物车
    int addShopCar(String userId, int bookId);//添加购物车
    int delShopCar(int[] bookArray, String userid);//删除购物车信息
    int getCarNum(String userId);//获取购物车数量
}
