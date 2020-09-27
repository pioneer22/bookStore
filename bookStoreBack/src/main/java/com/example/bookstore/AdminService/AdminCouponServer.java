package main.java.com.example.bookstore.AdminService;

import java.util.List;
import java.util.Map;

public interface AdminCouponServer {

    Map<String,Object> reqCoupon(String businessId);//获取优惠卷信息

    int insertCoupon(List<Object> couponMess);//发布优惠卷

    int deleteCoupon(int couponId);//删除优惠卷
}
