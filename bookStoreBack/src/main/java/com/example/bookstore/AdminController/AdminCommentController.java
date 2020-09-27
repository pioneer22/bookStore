package com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminCommentServer;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminCommentController {

    @Autowired
    private AdminCommentServer adminCommentServer;

    @ResponseBody
    @GetMapping("/comment/reqComment")
    public ServerResponse reqComment(){
        List<Object> result = adminCommentServer.reqBookComment();
        return ServerResponse.createBySuccess("getCommentSuccess",result);
    }

    @ResponseBody
    @PostMapping("comment/replyComment")
    public int replyComment(@RequestBody Map<String,Object> obj) {
        int comId = (int) obj.get("comId");
        String comReply = (String) obj.get("comReply");
        int result = adminCommentServer.replyComment(comId,comReply);
        return result;
    }

    @ResponseBody
    @PostMapping("/comment/searchBook")
    public ServerResponse searchBook(@RequestBody Map<String,String> obj){
        String searchKey = obj.get("searchKey");
        List<Object> result = adminCommentServer.searchBook(searchKey);
        if(result.size()>0)
            return ServerResponse.createBySuccess("searchSuccess",result);
        else
            return ServerResponse.createByError("searchFail");
    }

    @ResponseBody
    @PostMapping("/business/changeMessage")
    public int changeMessage(@RequestBody Map<String,String> obj){
        String businessId = obj.get("businessId");
        String businessName = obj.get("name");
        String businessPhone = obj.get("phone");
        int result = adminCommentServer.changeMessage(businessId,businessName,businessPhone);
        return result;
    }

    @ResponseBody
    @PostMapping("/business/changePassword")
    public int changePassword(@RequestBody Map<String,String> obj){
        String businessId = obj.get("businessId");
        String businessPass = obj.get("newPass");
        int result = adminCommentServer.changePassword(businessId,businessPass);
        return result;
    }
}
