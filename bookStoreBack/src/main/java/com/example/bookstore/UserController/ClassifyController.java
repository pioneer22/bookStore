package main.java.com.example.bookstore.UserController;

import com.example.bookstore.UserService.ClassifyServer;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Classified;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600)
public class ClassifyController {

    @Autowired
    private ClassifyServer classifyServer;

    @ResponseBody
    @GetMapping("/classify/getClassify")
    public ServerResponse getClassify(){//获取图书分类信息
        List<Classified> result = classifyServer.getClassify();
        if(result.size()>0){
            return ServerResponse.createBySuccess("获取分类成功！",result);
        }else{
            return ServerResponse.createByError("获取分类失败！");
        }
    }

    @ResponseBody
    @GetMapping("/classify/getClassifyBook")
    public ServerResponse getClassifyBook(@RequestParam("classifiedId") int classifiedId){
    //获得分类图书信息
        List<Book> list = classifyServer.getClassifyBook(classifiedId);
        if(list.size()>0){
            return ServerResponse.createBySuccess("获取图书分类信息成功！",list);
        }else{
            return ServerResponse.createByError("获取图书分类失败！");
        }
    }
}
