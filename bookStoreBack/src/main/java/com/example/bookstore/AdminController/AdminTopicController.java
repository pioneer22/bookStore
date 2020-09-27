package main.java.com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminTopicServer;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminTopicController {

    @Autowired
    private AdminTopicServer adminTopicServer;

    @ResponseBody
    @GetMapping("/topic/reqTopic")
    public ServerResponse reqTopic(){
        List<Object> result = adminTopicServer.reqTopic();
        return ServerResponse.createBySuccess("getTopicSuccess",result);
    }

    @ResponseBody
    @GetMapping("/topic/deleteTopic")
    public int deleteTopic(@RequestParam("topicId") int topicId){
        int result = adminTopicServer.deleteTopic(topicId);
        return result;
    }

    @ResponseBody
    @PostMapping("/topic/deleteTopicBook")
    public int deleteTopicBook(@RequestBody Map<String,Integer> obj){
        int topicId = obj.get("topicId");
        int bookId = obj.get("bookId");
        int result = adminTopicServer.deleteTopicBook(topicId,bookId);
        return result;
    }

    @ResponseBody
    @PostMapping("/topic/searchTopic")
    public ServerResponse searchTopic(@RequestBody Map<String,String> obj){
        String searchKey = obj.get("searchKey");
        List<Object> result = adminTopicServer.searchTopic(searchKey);
        return ServerResponse.createBySuccess("getTopicSuccess",result);
    }

    @ResponseBody
    @GetMapping("/topic/reqBook")
    public ServerResponse reqBook(){
        List<Object> result = adminTopicServer.reqBook();
        if (result.size()>0)
            return ServerResponse.createBySuccess("reqBookSuccess",result);
        else
            return ServerResponse.createByError("reqBookFail");
    }

    @ResponseBody
    @PostMapping("/topic/insertTopicBook")
    public int insertTopicBook(@RequestBody Map<String,Object> obj){
        int topicId = (int) obj.get("topicId");
        List<String> books = (List<String>) obj.get("books");
        int result = adminTopicServer.insertTopicBook(topicId,books);
        return result;
    }

    @ResponseBody
    @PostMapping("/topic/insertTopic")
    public int insertTopic(@RequestBody Map<String,Object> obj){
        String topicName = (String) obj.get("topicName");
        String topicImg = (String) obj.get("topicImg");
        List<Integer> books = (List<Integer>) obj.get("books");
        int result = adminTopicServer.insertTopic(topicName,topicImg,books);
        return result;
    }
}
