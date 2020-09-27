package main.java.com.example.bookstore.UserController;

import com.example.bookstore.UserService.FirstPageServer;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Coupon;
import com.example.bookstore.pojo.MorningPaper;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600)
public class FirstPageController {

    @Autowired
    private FirstPageServer firstPageServer;

    @ResponseBody
    @GetMapping("/firstpage/getBookMess")
    public ServerResponse getBookMess(){
        List<Book> result = firstPageServer.getBookMess();
        if(result.size()>0)
            return ServerResponse.createBySuccess("获取图书成功！",result);
        else
            return ServerResponse.createByError("获取图书失败！");
    }

    @ResponseBody
    @GetMapping("/firstpage/getMorning")
    public ServerResponse getMorning(){
        List<MorningPaper> morningPapers = firstPageServer.getMorning();
        if(morningPapers.size()>0)
            return ServerResponse.createBySuccess("获取早报成功！",morningPapers);
        else
            return ServerResponse.createByError("获取早报失败！");
    }

    @ResponseBody
    @GetMapping("/firstpage/getSellWell")
    public ServerResponse getSellWell(){
        //获得热销书
        List<Book> list = firstPageServer.getSellWell();
        if(list.size()>0)
            return ServerResponse.createBySuccess("获取热销图书成功！",list);
        else
            return ServerResponse.createByError("获取热销图书失败！");
    }

    @ResponseBody
    @GetMapping("/firstpage/realTimeSearch")
    public ServerResponse realTimeSearch(@RequestParam("searchValue") String searchValue){
        Map<String,String[]> result = firstPageServer.realTimeSearch(searchValue);
        if(result.size()>0)
            return ServerResponse.createBySuccess("实时查询成功！",result);
        else
            return ServerResponse.createByError("实时查询失败！");
    }

    @ResponseBody
    @GetMapping("/firstpage/getSearchBook")
    public ServerResponse getSearchBook(@RequestParam("searchValue") String searchValue) {
        Map<String, List<Book>> result = firstPageServer.getSearchBook(searchValue);
        if (result.size() > 0)
            return ServerResponse.createBySuccess("查询成功！", result);
        return ServerResponse.createByError("查询失败!");
    }

    @ResponseBody
    @GetMapping("/firstpage/getSort")
    public ServerResponse getSort(){
        Map<String,Object> result = firstPageServer.getSort();
        return ServerResponse.createBySuccess("获取数据成功！",result);
    }

    @ResponseBody
    @GetMapping("/firstpage/getCoupon")
    public ServerResponse getCoupon(){
        List<Coupon> result = firstPageServer.getCoupon();
        if(result.size()>0)
            return ServerResponse.createBySuccess("获取优惠卷成功！",result);
        return ServerResponse.createByError("获取优惠卷失败");
    }

    @ResponseBody
    @PostMapping("/firstpage/gainCoupon")
    public int gainCoupon(@RequestBody Map<String,Object> obj){
//优惠卷信息
        String userId =(String) obj.get("userId");
        int couponId = (Integer) obj.get("couponId");
        return firstPageServer.gainCoupon(userId,couponId);
    }

    @ResponseBody
    @PostMapping("/firstpage/getMessage")
    public ServerResponse getMessage(@RequestBody Map<String, String> obj){
        String userId = obj.get("userId");
        Map<String,Object> result = firstPageServer.getMessage(userId);
        return ServerResponse.createBySuccess("获取消息成功！",result);
    }

    @ResponseBody
    @PostMapping("/firstpage/tagRead")
    public int tagRead(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        int reault = firstPageServer.tagRead(userId);
        return reault;
    }

    @ResponseBody
    @PostMapping("/firstpage/deleteMessage")
    public int deleteMessage(@RequestBody Map<String,Object> obj){
        String userId = (String)obj.get("userId");
        int paperId = (int)obj.get("paperId");
        int result = firstPageServer.deleteOneMess(userId,paperId);
        return result;
    }

    @ResponseBody
    @PostMapping("/firstpage/tagOneMessage")
    public int tagOneMessage(@RequestBody Map<String,Object> obj){
        String userId = (String)obj.get("userId");
        int paperId = (int)obj.get("paperId");
        int result = firstPageServer.tagOneMessage(userId,paperId);
        return result;
    }

    @ResponseBody
    @PostMapping("/firstpage/clearMessage")
    public int clearMessage(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        int result = firstPageServer.clearMessage(userId);
        return result;
    }

    @ResponseBody
    @GetMapping("/firstpage/reqTopic")
    public ServerResponse reqTopic() {
        ArrayList result = firstPageServer.reqTopic();
        if(result.size()>0)
            return ServerResponse.createBySuccess("getTopicSuccess",result);
        else
            return ServerResponse.createByError("getTopicFail");
    }

    @ResponseBody
    @GetMapping("/firstpage/reqUnReadCount")
    public boolean reqUnReadCount(@RequestParam("userId") String userId) {
        boolean result = firstPageServer.reqUnReadCount(userId);
        return result;
    }
}