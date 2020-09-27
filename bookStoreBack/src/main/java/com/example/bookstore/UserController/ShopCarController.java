package main.java.com.example.bookstore.UserController;

import com.example.bookstore.UserService.ShopCarServer;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Controller
@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600)
public class ShopCarController {

    @Autowired
    private ShopCarServer shopCarServer;

    @ResponseBody
    @PostMapping("/Shopcar/getShopCar")
    public ServerResponse getShopCar(@RequestBody Map<String,String> obj){
        String userId = obj.get("userId");
        List result = shopCarServer.getShopCar(userId);
        if(result.size()>0){
            return ServerResponse.createBySuccess("获取购物车信息成功！",result);
        }else{
            return ServerResponse.createByError("获取购物车信息失败！");
        }
    }

    @ResponseBody
    @PostMapping("/Shopcar/addShopCar")
    public int addShopCar(@RequestBody Map<String,Object> obj, HttpServletRequest request){
//        添加购物车
//        HttpSession session = request.getSession();
//        User userObject = (User)session.getAttribute("userObject");
//        String userid = userObject.getUserId();

        int bookId = (int) obj.get("bookId");
        String userid = (String) obj.get("userId");
        int isAdd = shopCarServer.addShopCar(userid,bookId);
        return isAdd;
    }

    @ResponseBody
    @GetMapping("/Shopcar/delBook")
    public int delBook(@RequestParam("delBookId") String bookId,@RequestParam("userId") String userid){
        //删除购物车图书
//        HttpSession session = request.getSession();
//        User userObject = (User)session.getAttribute("userObject");
//        String userid = userObject.getUserId();

//        String bookId = obj.get("delBookId");
//        String userid = obj.get("userId");
        String bookArr[] =bookId.split(",");
        int bookArray[] = new int[bookArr.length];
        for(int i=0;i<bookArr.length;i++)
            bookArray[i] = parseInt(bookArr[i]);
        int statu = shopCarServer.delShopCar(bookArray,userid);
        return statu;
    }

    @ResponseBody
    @GetMapping("/Shopcar/getCarNum")
    public int getCarNum(@RequestParam("userId") String userId){
        int bookNum = shopCarServer.getCarNum(userId);
        return bookNum;
    }

}
