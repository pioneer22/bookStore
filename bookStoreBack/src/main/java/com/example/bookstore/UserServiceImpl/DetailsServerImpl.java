package main.java.com.example.bookstore.UserServiceImpl;

import com.example.bookstore.UserService.DetailsServer;
import com.example.bookstore.dao.BookMapper;
import com.example.bookstore.dao.CommentMapper;
import com.example.bookstore.dao.UserMapper;
import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Comment;
import com.example.bookstore.pojo.CommentExample;
import com.example.bookstore.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailsServerImpl implements DetailsServer{

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    //获取评论
    @Override
    public ArrayList getComment(int bookId) {
        ArrayList arrayList = new ArrayList();
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("com_time desc");
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andBookIdEqualTo(bookId);
        List<Comment> list = commentMapper.selectByExample(commentExample);
        list.forEach((Comment c)->{
            User user = userMapper.selectByPrimaryKey(c.getUserId());
            Map<String,Object> map = new HashMap<>();
            map.put("userImg",user.getUserImg());
            map.put("userName",user.getUserName());
            map.put("comment",c);
            arrayList.add(map);
        });
        return arrayList;
    }

    @Override
    public Book getOneBook(int bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public boolean changeUserImg(User user) {
        //更改头像
        int result = userMapper.updateByPrimaryKey(user);
        return result>0?true:false;
    }

    @Override
    public User getUserInfo(String userId) {
        //获取用户个人信息
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }
}
