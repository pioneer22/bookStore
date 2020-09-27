package main.java.com.example.bookstore.UserController;

import com.example.bookstore.UserService.MyCenterServer;
import com.example.bookstore.pojo.Contacts;
import com.example.bookstore.pojo.Coupon;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.bookstore.utils.ReadTxtFile.readTxt;

@Controller
@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600)
public class MyCenterController {

    @Autowired
    private MyCenterServer myCenterServer;

    @ResponseBody
    @PostMapping("/mycenter/changeMess")
    public ServerResponse changeMess(@RequestBody Map<String,String> obj,HttpServletRequest request){
        //修改密码
//        HttpSession session = request.getSession();
//        User userObject = (User) session.getAttribute("userObject");
//        String userid = userObject.getUserId();

        String userid = obj.get("userId");
        String oldPass = obj.get("oldPass");
        String newPass = obj.get("newPass");
        return myCenterServer.changeMess(userid,oldPass,newPass);
    }

    @ResponseBody
    @GetMapping("/mycenter/getContacts")
    public ServerResponse getContacts(@RequestParam("userId") String userid){
        //获取联系人
        return myCenterServer.getContacts(userid);
    }

    @ResponseBody
    @PostMapping("/mycenter/insertAddress")
    public int insertContacts(@RequestBody Map<String,String> contacts){
        //添加联系人
        String userid = contacts.get("userId");
        String contactName = contacts.get("contactName1");
        String contactPhone = contacts.get("contactPhone1");
        String contactAddress = contacts.get("contactAddress1");
        return  myCenterServer.insertContacts(userid,contactName,contactPhone,contactAddress);
    }

    @ResponseBody
    @PostMapping("/mycenter/updateContact")
    public boolean updateContact(@RequestBody Map<String,Object> contact){
        //编辑联系人
        Integer contactId = (Integer) contact.get("contactId");
        String contactName = (String) contact.get("contactName");
        String contactPhone = (String) contact.get("contactPhone");
        String contactAddress = (String) contact.get("contactAddress");

        Contacts contacts = myCenterServer.getContactsInfo(contactId);
        contacts.setContactPhone(contactPhone);
        contacts.setContactName(contactName);
        contacts.setContactAddress(contactAddress);
        Boolean result = myCenterServer.updateContacts(contacts);
        return result;
    }

    @ResponseBody
    @PostMapping("/mycenter/deleteContact")
    public boolean deleteContact(@RequestBody Map<String,Integer> obj){
        //删除收货地址
        int contactId = obj.get("contactId");
        return myCenterServer.deleteContacts(contactId);
    }

    @ResponseBody
    @PostMapping("/mycenter/getCollect")
    public ServerResponse getCollect(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        Object result = myCenterServer.getAllCollect(userId);
        return ServerResponse.createBySuccess("获取收藏成功！",result);
    }

    @ResponseBody
    @PostMapping("/mycenter/myCoupon")
    public ServerResponse myCoupon(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        ArrayList result = myCenterServer.myCoupon(userId);
        return ServerResponse.createBySuccess("获取个人优惠卷成功",result);
    }

    @ResponseBody
    @GetMapping("/mycenter/aboutStore")
    public String[] getContent(){
        //获取文本信息
        String filePath = "E:\\inteillJProject\\bookstore\\src\\main\\resources\\static\\Img\\Txt\\aboutStore.txt";
        String result = readTxt(filePath);
        String list[] = result.split("；");
        return list;
    }

    @ResponseBody
    @PostMapping("/mycenter/getChat")
    public ServerResponse getChat(@RequestBody Map<String,String> obj){//获取聊天信息
        String userId = obj.get("userId");
        Map<String,Object> result = myCenterServer.getChat(userId);
        return ServerResponse.createBySuccess("Success",result);
    }

    @ResponseBody
    @PostMapping("/mycenter/saveChat")
    public int saveChat(@RequestBody Map<String,Object> obj){//保存聊天信息
        String userId = (String)obj.get("userId");
        String businessId = (String)obj.get("businessId");
        int statu = (int) obj.get("statu");
        Date sendTime = new Date((long) obj.get("sendTime"));
        String context = (String) obj.get("context");
        ArrayList list = new ArrayList();
        list.add(userId);
        list.add(businessId);
        list.add(statu);
        list.add(sendTime);
        list.add(context);
        return myCenterServer.saveChat(list);
    }

    @ResponseBody
    @PostMapping("/mycenter/getOrder")
    public ServerResponse getOrder(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        Map<String,Object> result = myCenterServer.getOrder(userId);
        return ServerResponse.createBySuccess("获取订单成功！",result);
    }

    @ResponseBody
    @PostMapping("/mycenter/deleteOrder")
    public int deleteOrder(@RequestBody Map<String,String> obj){
        String orderId = obj.get("orderId");
        int result = myCenterServer.deleteOneOrder(orderId);
        return result;
    }

    @ResponseBody
    @PostMapping("/mycenter/setDefaultAddress")
    public int setDefaultAddress(@RequestBody Map<String,Object> obj){
        String userId = (String)obj.get("userId");
        int contactId = (int) obj.get("contactId");
        int result = myCenterServer.setDefaultAddrss(userId,contactId);
        return result;
    }

    @ResponseBody
    @PostMapping("/mycenter/personCoupon")
    public ServerResponse personCoupon(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        List<Coupon> result = myCenterServer.personCoupon(userId);
        if(result.size()>0)
            return ServerResponse.createBySuccess("获取优惠卷成功！",result);
        else
            return ServerResponse.createByError("获取优惠卷失败！");
    }
}
