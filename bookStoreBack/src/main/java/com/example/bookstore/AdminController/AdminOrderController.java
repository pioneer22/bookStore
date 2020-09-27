package main.java.com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminOrderServer;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminOrderController {

    @Autowired
    private AdminOrderServer adminOrderServer;

    @ResponseBody
    @GetMapping("/order/getOrder")
    public ServerResponse getOrder(){
        Map<String,Object> result = adminOrderServer.getOrder();
        return ServerResponse.createBySuccess("getOrderSuccess",result);
    }

    @ResponseBody
    @GetMapping("/order/deleteOrder")
    public int deleteOrder(@RequestParam ("orderId") String orderId){
        int result = adminOrderServer.deleteOrder(orderId);
        return result;
    }

    @ResponseBody
    @PostMapping("/order/remindPay")
    public int remindPay(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        String orderId = obj.get("orderId");
        int result = adminOrderServer.remindPay(userId,orderId);
        return result;
    }

    @ResponseBody
    @PostMapping("/order/changeContact")
    public int changeContact(@RequestBody Map<String,Object> obj){
        List<Object> contactMess = (List<Object>) obj.get("contactMess");
        int result = adminOrderServer.changeContact(contactMess);
        return 1;
    }

    @ResponseBody
    @GetMapping("/order/searchOrder")
    public ServerResponse searchOrder(@RequestParam ("searchKey") String searchKey){
        Map<String,Object> result = adminOrderServer.searchOrder(searchKey);
        return ServerResponse.createBySuccess("searchOrderSuccess",result);
    }
}