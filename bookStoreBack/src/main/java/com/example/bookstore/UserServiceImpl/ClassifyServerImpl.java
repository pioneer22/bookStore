package main.java.com.example.bookstore.UserServiceImpl;

import com.example.bookstore.UserService.ClassifyServer;
import com.example.bookstore.dao.BookMapper;
import com.example.bookstore.dao.ClassifiedMapper;
import com.example.bookstore.dao.GetClassifiedMapper;
import com.example.bookstore.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassifyServerImpl implements ClassifyServer {

    @Autowired
    private ClassifiedMapper classifiedMapper;

    @Autowired
    private GetClassifiedMapper getClassifiedMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Classified> getClassify() {
        //获取分类
        ClassifiedExample classifiedExample = new ClassifiedExample();
        classifiedExample.setOrderByClause("classified_id asc");
        List<Classified> result =classifiedMapper.selectByExample(classifiedExample);
        return result;
    }

    @Override
    public List<Book> getClassifyBook(int classifiedId) {
        //获取同个分类图书信息
        GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
        GetClassifiedExample.Criteria criteria = getClassifiedExample.createCriteria();
        criteria.andClassifiedIdEqualTo(classifiedId);
        //获得图书
        List<GetClassified> list = getClassifiedMapper.selectByExample(getClassifiedExample);
        //获得图书信息
        List<Book> book = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            BookExample bookExample = new BookExample();
            BookExample.Criteria criteria1 = bookExample.createCriteria();
            criteria1.andBookIdEqualTo(list.get(i).getBookId());
            book.add(bookMapper.selectByExample(bookExample).get(0));
        }
        return book;
    }
}
