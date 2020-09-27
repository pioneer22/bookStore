package main.java.com.example.bookstore.AdminService;

import java.util.List;
import java.util.Map;

public interface AdminOrderServer {

    Map<String,Object> getOrder();//获取订单页面信息

    int deleteOrder(String orderId); //删除订单

    int remindPay(String userId, String orderId); //提醒支付

    int changeContact(List<Object> contactMess); //修改收货人信息

    Map<String,Object> searchOrder(String searchKey); //查找订单
}
