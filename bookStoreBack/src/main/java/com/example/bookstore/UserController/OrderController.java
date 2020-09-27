package main.java.com.example.bookstore.UserController;

import com.example.bookstore.UserService.OrderServer;
import com.example.bookstore.utils.ServerResponse;
import com.example.bookstore.utils.dto.GenerateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderServer orderServer;

    @ResponseBody
    @PostMapping("/Order/getCollect")
    public int getCollect(@RequestBody Map<String,Object> obj, HttpServletRequest request) {
        String userid = (String) obj.get("userId");
//        int bookId = Integer.valueOf((String) obj.get("bookId"));
        int bookId = (int) obj.get("bookId");
//        HttpSession session = request.getSession();
//        User userObject = (User) session.getAttribute("userObject");
//        String userid = userObject.getUserId();
        int statu = orderServer.getColllect(userid, bookId);
        return statu;
    }

    @ResponseBody
    @PostMapping("/Order/toggleCollect")
    public int toggleCollect(@RequestBody Map<String,Object> obj, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        User userObject = (User) session.getAttribute("userObject");
//        String userid = userObject.getUserId();
        String userid = (String) obj.get("userId");
        int bookId = (int) obj.get("bookId");
        int statu = orderServer.toggleCollect(userid, bookId);
        return statu;
    }

    @ResponseBody
    @PostMapping("/Order/getOrderStatuNum")
    public ServerResponse getOrderStatuNum(@RequestBody Map<String, String> obj) {
        String userId = obj.get("userId");
        int[] result = orderServer.getOrderStatuNum(userId);
        return ServerResponse.createBySuccess("获取数量成功！", result);
    }

    @ResponseBody
    @PostMapping("/Order/generateOrder")
    public String generateOrder(@RequestBody GenerateOrderDto generateOrderDto) {
        //生成订单
        List<GenerateOrderDto.CarBook> carBookId = generateOrderDto.getCarBookId();
        Object[] orderMess = generateOrderDto.getOrderMess();
        String result = orderServer.generateOrder(carBookId, orderMess);
        return result;
    }

    @ResponseBody
    @PostMapping("/Order/setOrderStatu")
    public int setOrderStatu(@RequestBody Map<String, String> obj) {
        //支付修改订单状态
        String userId = obj.get("userId");
        String orderId = obj.get("orderId");
        int result = orderServer.setOrderStatu(userId, orderId);
        return result;
    }

    @ResponseBody
    @PostMapping("/Order/setOrderStatu1")
    public int setOrderStatu1(@RequestBody Map<String, String> obj) {
        //支付修改订单状态
        String userId = obj.get("userId");
        String orderId = obj.get("orderId");
        int result = orderServer.setOrderStatu1(userId, orderId);
        return result;
    }

    @ResponseBody
    @PostMapping("/Order/surePay")
    public int surePay(@RequestBody Map<String,String> obj){
        //确认收货修改订单状态
        String orderId = obj.get("orderId");
        int result = orderServer.surePay(orderId);
        return result;
    }

    @ResponseBody
    @PostMapping("/Order/cancelOrder")
    public int cancelOrder(@RequestBody Map<String,String> obj){
        //确认收货
        String orderId = obj.get("orderId");
        int result = orderServer.cancelOrder(orderId);
        return result;
    }

    @ResponseBody
    @PostMapping("/Order/submitComment")
    public int submitComment(@RequestBody Map<String,String> obj){
        String orderId = obj.get("orderId");
        String comment = obj.get("comment");
        String userId = obj.get("userId");
        int result = orderServer.submitComment(orderId,comment,userId);
        return result;
    }
}
