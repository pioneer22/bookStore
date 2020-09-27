package main.java.com.example.bookstore.UserService;

import com.example.bookstore.utils.dto.GenerateOrderDto;

import java.util.List;

public interface OrderServer {
    int getColllect(String userId, int bookId);//进入页面获取收藏信息

    int toggleCollect(String userId, int bookId);//点击收藏按钮

    int[] getOrderStatuNum(String userId);//商品状态数量

    String generateOrder(List<GenerateOrderDto.CarBook> carBookId, Object[] orderMess);//生成订单

    int setOrderStatu(String userId, String orderId);//付款更改订单状态
    int setOrderStatu1(String userId, String orderId);//付款更改订单状态

    int surePay(String orderId);//确认收货更改订单状态

    int cancelOrder(String orderId);//取消订单

    int submitComment(String orderId, String comment, String userId);//提交评论;
}
