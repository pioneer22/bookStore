package main.java.com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminCouponServer;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminCouponController {

    @Autowired
    private AdminCouponServer adminCouponServer;

    @ResponseBody
    @GetMapping("/coupon/reqCoupon")
    public ServerResponse reqCoupon(@RequestParam("businessId") String businessId){
        Map<String,Object> result = adminCouponServer.reqCoupon(businessId);
        return ServerResponse.createBySuccess("getCouponSuccess",result);
    }

    @ResponseBody
    @PostMapping("/coupon/insertCoupon")
    public int insertCoupon(@RequestBody Map<String,Object> obj){
        List<Object> couponMess = (List<Object>) obj.get("couponMess");
        int result = adminCouponServer.insertCoupon(couponMess);
        return result;
    }

    @ResponseBody
    @GetMapping("/coupon/deleteCoupon")
    public int deleteCoupon(@RequestParam("couponId") int couponId){
        int result = adminCouponServer.deleteCoupon(couponId);
        return result;
    }
}
