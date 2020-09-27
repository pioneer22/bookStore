package main.java.com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminTopicServer;
import com.example.bookstore.dao.BookMapper;
import com.example.bookstore.dao.TopicBookMapper;
import com.example.bookstore.dao.TopicMapper;
import com.example.bookstore.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class AdminTopicServerImpl implements AdminTopicServer{

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicBookMapper topicBookMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Object> reqTopic() {
        TopicExample topicExample = new TopicExample();
        TopicExample.Criteria criteria = topicExample.createCriteria();
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        List<Object> result = new ArrayList<>();
        topics.forEach((Topic t)->{
            Map<String,Object> map = new HashMap<>();
            map.put("topic",t);
            TopicBookExample topicBookExample = new TopicBookExample();
            TopicBookExample.Criteria criteria1 = topicBookExample.createCriteria();
            criteria1.andTopicIdEqualTo(t.getTopicId());
            List<TopicBook> topicBooks = topicBookMapper.selectByExample(topicBookExample);
            List<Book> books = new ArrayList<>();
            topicBooks.forEach((TopicBook tb)->{
                BookExample bookExample = new BookExample();
                BookExample.Criteria criteria2 = bookExample.createCriteria();
                criteria2.andBookIdEqualTo(tb.getBookId());
                books.add(bookMapper.selectByExample(bookExample).get(0));
            });
            map.put("book",books);
            result.add(map);
        });
        return result;
    }

    @Override
    public int deleteTopic(int topicId) {
        int result = topicMapper.deleteByPrimaryKey(topicId);
        TopicBookExample topicBookExample = new TopicBookExample();
        TopicBookExample.Criteria criteria = topicBookExample.createCriteria();
        criteria.andTopicIdEqualTo(topicId);
        topicBookMapper.deleteByExample(topicBookExample);
        return result;
    }

    @Override
    public int deleteTopicBook(int topicId, int bookId) {
        TopicBookExample topicBookExample = new TopicBookExample();
        TopicBookExample.Criteria criteria = topicBookExample.createCriteria();
        criteria.andTopicIdEqualTo(topicId);
        criteria.andBookIdEqualTo(bookId);
        int result = topicBookMapper.deleteByExample(topicBookExample);
        return result;
    }

    @Override
    public List<Object> searchTopic(String searchKey) {
        String searchValue = "%"+searchKey+"%";
        TopicExample topicExample = new TopicExample();
        topicExample.or().andTopicNameLike(searchValue);
        //判断是不是数字字符串
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if(pattern.matcher(searchKey).matches()){
            topicExample.or().andTopicIdEqualTo(Integer.valueOf(searchKey));
        }
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        List<Object> result = new ArrayList<>();
        topics.forEach((Topic t)->{
            Map<String,Object> map = new HashMap<>();
            map.put("topic",t);
            TopicBookExample topicBookExample = new TopicBookExample();
            TopicBookExample.Criteria criteria1 = topicBookExample.createCriteria();
            criteria1.andTopicIdEqualTo(t.getTopicId());
            List<TopicBook> topicBooks = topicBookMapper.selectByExample(topicBookExample);
            List<Book> books = new ArrayList<>();
            topicBooks.forEach((TopicBook tb)->{
                BookExample bookExample = new BookExample();
                BookExample.Criteria criteria2 = bookExample.createCriteria();
                criteria2.andBookIdEqualTo(tb.getBookId());
                books.add(bookMapper.selectByExample(bookExample).get(0));
            });
            map.put("book",books);
            result.add(map);
        });
        return result;
    }

    @Override
    public List<Object> reqBook() {
        BookExample bookExample = new BookExample();
        List<Book> list = bookMapper.selectByExample(bookExample);
        List<Object> result = new ArrayList<>();
        list.forEach((Book book)->{
            Map<String,String> temp = new HashMap<>();
            temp.put("bookId",String.valueOf(book.getBookId()));
            temp.put("bookImg",book.getBookImg());
            temp.put("bookAuthor",book.getBookAuthor());
            temp.put("bookName",book.getBookName());
            result.add(temp);
        });
        return result;
    }

    @Override
    public int insertTopicBook(int topicId, List<String> books) {
        int result = 0;
        TopicBookExample topicBookExample = new TopicBookExample();
        TopicBookExample.Criteria criteria = topicBookExample.createCriteria();
        criteria.andTopicIdEqualTo(topicId);
        for (String bookIds : books) {
            int bookId = Integer.valueOf(bookIds);
            criteria.andBookIdEqualTo(bookId);
            if (topicBookMapper.selectByExample(topicBookExample).size() > 0)
                continue;
            TopicBook topicBook = new TopicBook();
            topicBook.setBookId(bookId);
            topicBook.setTopicId(topicId);
            result = topicBookMapper.insertSelective(topicBook);
        }
        return result;
    }

    @Override
    public int insertTopic(String topicName, String topicImg, List<Integer> books) {
        Date date = new Date();
        Topic topic = new Topic();
        topic.setTopicImg(topicImg);
        topic.setTopicName(topicName);
        topic.setTopicTime(date);
        int result = topicMapper.insertSelective(topic);

        TopicExample topicExample = new TopicExample();
        topicExample.setOrderByClause("topic_id desc");
        int topicId = topicMapper.selectByExample(topicExample).get(0).getTopicId();

        for (Integer book : books) {
            TopicBook topicBook = new TopicBook();
            topicBook.setBookId(book);
            topicBook.setTopicId(topicId);
            topicBookMapper.insertSelective(topicBook);
        }
        return result;
    }
}
