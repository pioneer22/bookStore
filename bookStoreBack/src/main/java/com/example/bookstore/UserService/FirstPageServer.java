package main.java.com.example.bookstore.UserService;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Coupon;
import com.example.bookstore.pojo.MorningPaper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FirstPageServer {
    //获取早报
    List<MorningPaper> getMorning();
    //获取商品信息
    List<Book> getBookMess();
    //获取热销图书
    List<Book> getSellWell();
    //获取实时查询结果
    Map<String,String[]> realTimeSearch(String searchValue);
    //获取查询结果
    Map<String,List<Book>> getSearchBook(String searchValue);
    //获取排行榜
    Map<String,Object> getSort();
    //获取优惠卷
    List<Coupon> getCoupon();
    //领取优惠卷
    int gainCoupon(String userId, int couponId);
    //获取信息
    Map<String,Object> getMessage(String userId);
    //全部标记为已读
    int tagRead(String userId);
    //删除单条消息
    int deleteOneMess(String userId, int paperId);
    //标记单挑未读消息为已读
    int tagOneMessage(String userId, int paperId);
    //清空消息通知
    int clearMessage(String userId);
    // 获取专题
    ArrayList reqTopic();
    //判断是否有未读信息
    boolean reqUnReadCount(String userId);
}
