package com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminBookServer;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Classified;
import com.example.bookstore.utils.SaveMultipartFile;
import com.example.bookstore.utils.ServerResponse;
import com.example.bookstore.utils.dto.BookAndClassifyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

//@EnableAutoConfiguration//启动自动配置
@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminBookController {

    @Autowired
    private AdminBookServer adminBookServer;

    @ResponseBody
    @GetMapping("/book/reqBook")
    public ServerResponse businessLogin(){//获取图书
        List<Book> result = adminBookServer.getBook();
        if(result.size()>0)
            return ServerResponse.createBySuccess("getBookSuccess",result);
        else
            return ServerResponse.createByError("getBookFail");
    }

    @ResponseBody
    @GetMapping("/book/searchBook")
    public ServerResponse searchBook(@RequestParam("searchKey") String searchKey){
        //查询图书
        List<Book> result = adminBookServer.searchKey(searchKey);
        if(result.size()>0)
            return ServerResponse.createBySuccess("getBookSuccess",result);
        else
            return ServerResponse.createByError("getBookFail");
    }

    @ResponseBody
    @PostMapping("/upload")
    public ServerResponse uploadImg(MultipartFile file){// 保存图片
//        String name = saveFile.addImg(file);
        String name = SaveMultipartFile.addImg(file);
        String result = "/api/Img/"+name;
        return ServerResponse.createBySuccess("success",result);
    }

    @ResponseBody
    @PostMapping("/book/editBookMess")
    public int editBookMess(@RequestBody Map<String,Object> obj){
        List<Object> searchMess = (List<Object>) obj.get("searchMess");
        int result = adminBookServer.editBookMess(searchMess);
        return result;
    }

    @ResponseBody
    @GetMapping("/book/deleteBook")
    public int deleteBook(@RequestParam ("bookId") int bookId){
        int result = adminBookServer.deleteBook(bookId);
        return result;
    }

    @ResponseBody
    @GetMapping("/book/deleteAllBook")
    public int deleteAllBook(){ // 清空
        int result = adminBookServer.deleteAllBook();
        return result;
    }

    @ResponseBody
    @PostMapping("/book/insertBook")
    public int insertBook(@RequestBody Map<String,Object> obj){ //图书上架
        List<Object> insertMess = (List<Object>) obj.get("insertMess");
        int result = adminBookServer.insertBook(insertMess);
        return result;
    }

    @ResponseBody
    @GetMapping("/book/getClassify")
    public ServerResponse getClassify(){
        List<Classified> result = adminBookServer.getClassify();
        if(result.size()>0)
            return ServerResponse.createBySuccess("getClassifySuccess",result);
        else
            return ServerResponse.createByError("getClassifyFail");
    }

    @ResponseBody
    @PostMapping("/book/insertClassify")
    public int insertClassify(@RequestBody Map<String,String> obj){ // 添加分类
        String classifiedName = obj.get("classifiedName");
        int result = adminBookServer.insertClassified(classifiedName);
        return result;
    }

    @ResponseBody
    @PostMapping("/book/deleteClassify")
    public int deleteClassify(@RequestBody Map<String,Object> obj){ //删除具体分类
        List<Integer> idArray = (List<Integer>)obj.get("idArray");
        int result = adminBookServer.deleteClassified(idArray);
        return result;
    }

    @ResponseBody
    @PostMapping("/book/insertBookClassify")
    public int insertBookClassify(@RequestBody Map<String,Object> obj){ //添加图书的分类信息
        int bookId = (int) obj.get("bookId");
        List<Integer> list = (List<Integer>) obj.get("idArray");
        int result = adminBookServer.insertBookClassify(bookId,list);
        return result;
    }

    @ResponseBody
    @GetMapping("/book/getBookAndClassify")
    public ServerResponse getBookAndClassify(){
        List<BookAndClassifyDto> result = adminBookServer.getBookAndClassify();
        if(result.size()>0)
            return ServerResponse.createBySuccess("getBookAndClassifySuccess",result);
        else
            return ServerResponse.createByError("getBookAndClassifyFail");
    }

    @ResponseBody
    @GetMapping("/book/searchBookAndClassify")
    public ServerResponse searchBookAndClassify(@RequestParam("searchKey") String searchKey){
        List<BookAndClassifyDto> result = adminBookServer.searchBookAndClassify(searchKey);
        if(result.size()>0)
            return ServerResponse.createBySuccess("getBookAndClassifySuccess",result);
        else
            return ServerResponse.createByError("getBookAndClassifyFail");
    }

    @ResponseBody
    @PostMapping("/book/deleteChooseBookClassify")
    public int deleteChooseBookClassify(@RequestBody Map<String,Object> obj){
        int bookId = (int) obj.get("bookId");
        List<Integer> classifyArray = (List<Integer>) obj.get("classifyArray");
        int result = adminBookServer.deleteChooseBookClassify(bookId,classifyArray);
        return result;
    }

    @ResponseBody
    @PostMapping("/book/insertMultiBookClassify")
    public int insertMultiBookClassify(@RequestBody Map<String,Object> obj){
        List<Integer> bookArray = (List<Integer>) obj.get("bookArray");
        List<Integer> classifyArray = (List<Integer>) obj.get("classifyArray");
        int result = adminBookServer.insertMultiBookClassify(bookArray,classifyArray);
        return result;
    }

    @ResponseBody
    @GetMapping("/book/searchClassify")
    public ServerResponse searchClassify(@RequestParam("searchKey") int searchKey){ // 按分类查找图书
        List<BookAndClassifyDto> result = adminBookServer.searchClassifyBook(searchKey);
        if(result.size()>0)
            return ServerResponse.createBySuccess("getClassifyBookSuccess",result);
        else
            return ServerResponse.createByError("getClassifyBookFail");
    }
}
