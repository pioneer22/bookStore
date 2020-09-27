package main.java.com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminExcelServer;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminExcelController {

    @Autowired
    private AdminExcelServer adminExcelServer;

    @ResponseBody
    @GetMapping("/article/exportSelectExcel")
    public ServerResponse exportSelectExcel(){
        Map<String,Object> result = adminExcelServer.getOrderMess();
        return ServerResponse.createBySuccess("getOrderSuccess",result);
    }

    @ResponseBody
    @GetMapping("/article/exportExcel")
    public ServerResponse exportExcel(){
        List<Book> result = adminExcelServer.getBookMess();
        return ServerResponse.createBySuccess("getBookSuccess",result);
    }

    @ResponseBody
    @PostMapping("/article/importExcel")
    public int importExcel(@RequestBody Map<String,Object> obj){
        List<Map<String,Object>> books = (List<Map<String,Object>>) obj.get("books");
        int result = adminExcelServer.importExcel(books);
        return result;
    }
}
