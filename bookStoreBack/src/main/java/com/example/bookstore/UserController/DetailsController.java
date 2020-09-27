package main.java.com.example.bookstore.UserController;

import com.example.bookstore.UserService.DetailsServer;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.User;
import com.example.bookstore.utils.SaveMultipartFile;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Controller
@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600)
public class DetailsController {

    @Autowired
    private DetailsServer detailsServer;

    @ResponseBody
    @GetMapping("/details/getComment")
    public ServerResponse getComment(@RequestParam ("bookId") int bookId){
        //获取评论
        ArrayList comment = detailsServer.getComment(bookId);
        if(comment.size()>0){
            return ServerResponse.createBySuccess("获取评论成功！",comment);
        }else{
            return ServerResponse.createByError("获取评论失败");
        }
    }

    @ResponseBody
    @GetMapping("/details/getOneBook")
    public ServerResponse getOneBook(@RequestParam ("bookId") int bookId){
        //获取图书界面信息
        Book book = detailsServer.getOneBook(bookId);
        if(book != null){
            return ServerResponse.createBySuccess("获取图书页面成功！",book);
        }else{
            return ServerResponse.createByError("获取图书页面失败!");
        }
    }

    @RequestMapping(value="/details/changeUserImg",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse changeUserImg(MultipartFile imgUrl, HttpServletRequest request){

        String userid = request.getParameter("userId");
        String username = request.getParameter("username");
        int userage =parseInt( request.getParameter("userage"));
        String usersex = request.getParameter("usersex");
        String userphone = request.getParameter("userphone");

        String link = "";
        if (Objects.isNull(imgUrl) || imgUrl.isEmpty()) {
        }else{
//            String headImgName = saveFile.addImg(imgUrl);
            String headImgName = SaveMultipartFile.addImg(imgUrl);
            //存储头像
            link = "/api/Img/"+headImgName;
        }
        //写入本地

//        HttpSession session = request.getSession();
//        User userObject = (User) session.getAttribute("userObject");
//        String userid = userObject.getUserId();
        User user = detailsServer.getUserInfo(userid);

        if(link != ""){
            user.setUserImg(link);
        }
        user.setUserAge(userage);
        user.setUserName(username);
        user.setUserPhone(userphone);
        user.setUserSex(usersex);
        Boolean result = detailsServer.changeUserImg(user);
        if(result){
            return ServerResponse.createBySuccess("成功修改头像！",user);
        }else{
            return ServerResponse.createByError("头像修改失败！");
        }
    }

}
