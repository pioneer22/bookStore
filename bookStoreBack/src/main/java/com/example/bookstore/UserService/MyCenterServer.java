package main.java.com.example.bookstore.UserService;

import com.example.bookstore.pojo.Contacts;
import com.example.bookstore.pojo.Coupon;
import com.example.bookstore.utils.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MyCenterServer {

    //修改密码
    ServerResponse changeMess(String userid, String oldPass, String newPass);
    //获取联系人
    ServerResponse getContacts(String id);
    //新增联系人
    int insertContacts(String id, String contactName, String contactPhone, String contactAddress);
    //修改联系人
    boolean updateContacts(Contacts contacts);
    //通过contactId查询对应联系人
    Contacts getContactsInfo(int contactid);
    //删除联系人
    boolean deleteContacts(int contactid);
    //获取个人收藏信息
    Object getAllCollect(String userId);
    //获取个人优惠卷信息
    ArrayList myCoupon(String userId);
    //获取聊天信息
    Map<String,Object> getChat(String userId);
    //保存聊天信息
    int saveChat(ArrayList list);
    //获取个人订单信息
    Map<String,Object> getOrder(String userId);
    //删除个人订单
    int deleteOneOrder(String orderId);
    //设置默认地址
    int setDefaultAddrss(String userId, int contactId);
    //获取个人优惠卷信息
    List<Coupon> personCoupon(String userId);
}
