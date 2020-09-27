package main.java.com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminNoticeServer;
import com.example.bookstore.pojo.Chat;
import com.example.bookstore.pojo.MorningPaper;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminNoticeController {

    @Autowired
    private AdminNoticeServer adminNoticeServer;

    @ResponseBody
    @GetMapping("/notice/getMorningPaper")
    public ServerResponse getMorningPaper(){
        List<MorningPaper> result = adminNoticeServer.getMorningPaper();
        if(result.size()>0)
            return ServerResponse.createBySuccess("getMorningPaperSuccess",result);
        else
            return ServerResponse.createByError("getMorningPaperFail");
    }

    @ResponseBody
    @PostMapping("/notice/sendNotice")
    public int sendNotice(@RequestBody Map<String,String> obj){
        String paperMess = obj.get("noticeMess");
        String businessId = obj.get("businessId");
        int result = adminNoticeServer.sendNotice(paperMess,businessId);
        return result;
    }

    @ResponseBody
    @GetMapping("/notice/deleteNotice")
    public int deleteNotice(@RequestParam("paperId") int paperId){
        int result = adminNoticeServer.deleteNotice(paperId);
        return result;
    }

    @ResponseBody
    @GetMapping("/notice/deleteAllNotice")
    public int deleteAllNotice(@RequestParam("businessId") String businessId){
        int result = adminNoticeServer.deleteAllNotice(businessId);
        return result;
    }

    @ResponseBody
    @GetMapping("/notice/reqChatMess")
    public ServerResponse reqChatMess(@RequestParam("businessId") String businessId){
        List<Chat> result = adminNoticeServer.reqChatMess(businessId);
        if(result.size()>0)
            return ServerResponse.createBySuccess("getChatMessSuccess",result);
        else
            return ServerResponse.createByError("getChatMessFail");
    }

    @ResponseBody
    @GetMapping("/notice/deleteOneChat")
    public int deleteOneChat(@RequestParam ("chatId") int chatId){
        int result = adminNoticeServer.deleteOneChat(chatId);
        return result;
    }

    @ResponseBody
    @PostMapping("/notice/searchChat")
    public ServerResponse searchChat(@RequestBody Map<String,String> obj) {
        String businessId = obj.get("businessId");
        String searchKey = obj.get("searchKey");
        List<Chat> result = adminNoticeServer.searchChat(businessId,searchKey);
        if (result.size()>0)
            return ServerResponse.createBySuccess("searchSuccess",result);
        else
            return ServerResponse.createByError("searchFail");
    }

    @ResponseBody
    @PostMapping("/notice/deleteChooseChat")
    public int deleteChooseChat(@RequestBody Map<String,Object> obj) {
        List<Integer> chatIdMess = (List<Integer>) obj.get("chatIdMess");
        int result = adminNoticeServer.deleteChooseChat(chatIdMess);
        return result;
    }

    @ResponseBody
    @GetMapping("/notice/reqUser")
    public ServerResponse reqUser(){
        List<Object> result = adminNoticeServer.reqUser();
        if (result.size()>0) {
            return  ServerResponse.createBySuccess("getUserSuccess",result);
        } else {
            return ServerResponse.createByError("getUserFail");
        }
    }

    @ResponseBody
    @PostMapping("/notice/reqChat")
    public ServerResponse reqChat(@RequestBody Map<String,String> obj){
        String businessId = obj.get("businessId");
        String userId = obj.get("userId");
        List<Object> result = adminNoticeServer.reqChat(businessId,userId);
        if(result.size()>0){
            return ServerResponse.createBySuccess("reqChatSuccess",result);
        } else {
            return ServerResponse.createByError("reqChatFail");
        }
    }

    @ResponseBody
    @PostMapping("/notice/saveMess")
    public int saveMess(@RequestBody Map<String,String> obj){
        String businessId = obj.get("businessId");
        String userId = obj.get("userId");
        String context = obj.get("context");
        int result = adminNoticeServer.saveMess(businessId,userId,context);
        return result;
    }

    @ResponseBody
    @PostMapping("/notice/saveMessAll")
    public int saveMessAll(@RequestBody Map<String,String> obj){
        String businessId = obj.get("businessId");
        String context = obj.get("context");
        int result = adminNoticeServer.saveMessAll(businessId,context);
        return result;
    }

    @ResponseBody
    @PostMapping("/notice/searchUser")
    public ServerResponse searchUser(@RequestBody Map<String,String> obj){
        List<Object> result = adminNoticeServer.searchUser(obj.get("searchKey"));
        if (result.size()>0) {
            return  ServerResponse.createBySuccess("getUserSuccess",result);
        } else {
            return ServerResponse.createByError("getUserFail");
        }
    }

    @ResponseBody
    @GetMapping("/notice/reqNoReadCount")
    public int reqNoReadCount(@RequestParam("businessId") String businessId){
        int result = adminNoticeServer.reqNoReadCount(businessId);
        return result;
    }

    @ResponseBody
    @GetMapping("/notice/reqNoReadMess")
    public ServerResponse reqNoReadMess(@RequestParam("businessId") String businessId){
        Map<String,Object> result = adminNoticeServer.reqNoReadMess(businessId);
        return ServerResponse.createBySuccess("getNoReadMessSuccess",result);
    }

    @ResponseBody
    @GetMapping("/notice/turnRead")
    public int turnRead(@RequestParam("Id") int chatId) {
        int result = adminNoticeServer.turnRead(chatId);
        return result;
    }

    @ResponseBody
    @GetMapping("/notice/turnKnow")
    public int turnKnow(@RequestParam("Id") String orderId) {
        int result = adminNoticeServer.turnKnow(orderId);
        return result;
    }
}
