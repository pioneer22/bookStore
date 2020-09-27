package main.java.com.example.bookstore.UserController;

import com.example.bookstore.UserService.LoginServer;
import com.example.bookstore.dao.UserMapper;
import com.example.bookstore.pojo.Business;
import com.example.bookstore.pojo.User;
import com.example.bookstore.pojo.UserExample;
import com.example.bookstore.utils.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//@EnableAutoConfiguration//启动自动配置
@Controller
@CrossOrigin(origins = "http://localhost:8088", maxAge = 3600)
public class LoginController {
    Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServer loginServer;

    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @PostMapping("/login")
    public ServerResponse login(@RequestBody Map<String,String> obj, HttpServletRequest request)
    {
        String user_id = obj.get("userid");
        String password = obj.get("password");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(user_id);
        criteria.andUserPassEqualTo(password);
        List<User> list = userMapper.selectByExample(userExample);
        if(list!=null && list.size()>0)
        {
            //使用request对象的getSession()获取session，如果session不存在则创建一个
            HttpSession session = request.getSession();
            //将数据存储到session中
            session.setAttribute("userObject", list.get(0));
            list.get(0).setUserPass(null);
            return ServerResponse.createBySuccess("登录成功!",list);
        }
        else {
            return ServerResponse.createByError("登录失败!");
        }
    }

    @ResponseBody
    @PostMapping("/exit")
    public void exitSession(HttpServletRequest request)
    {//销毁session
        request.getSession().invalidate();
    }

    @ResponseBody
    @PostMapping("/business/login")
    public ServerResponse businessLogin(@RequestBody Map<String,String> obj){
        String businessId = obj.get("businessId");
        String businessPass = obj.get("businessPass");
        List<Business> result = loginServer.bussinessLogin(businessId,businessPass);
        return ServerResponse.createBySuccess("登陆成功！",result);
    }
}
