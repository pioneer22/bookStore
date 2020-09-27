package main.java.com.example.bookstore.UserServiceImpl;

import com.example.bookstore.UserService.FirstPageServer;
import com.example.bookstore.dao.*;
import com.example.bookstore.pojo.*;
import com.example.bookstore.utils.SearchKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FirstPageServerImpl implements FirstPageServer {

    @Autowired
    private MorningPaperMapper morningPaperMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ClassifiedMapper classifiedMapper;

    @Autowired
    private GetClassifiedMapper getClassifiedMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private GetCouponMapper getCouponMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicBookMapper topicBookMapper;

    @Override
    public List<MorningPaper> getMorning() {
        //按时间先后顺序获取三条早报
        MorningPaperExample morningPaperExample = new MorningPaperExample();
        morningPaperExample.setOrderByClause("paper_time desc");
        List<MorningPaper> morningPapers = morningPaperMapper.selectByExample(morningPaperExample);
//        return morningPapers;
        return morningPapers.subList(0, 4);
    }

    @Override
    public List<Book> getBookMess() {
        //按图书上架时间获取图书信息
        BookExample bookExample = new BookExample();
        bookExample.setOrderByClause("book_time desc");
        List<Book> resultList = bookMapper.selectByExample(bookExample);
        return resultList;
    }

    @Override
    public List<Book> getSellWell() {
        //获得热销图书
        BookExample bookExample = new BookExample();
        bookExample.setOrderByClause("book_sale desc");
        List<Book> resultList = bookMapper.selectByExample(bookExample);
        return resultList.subList(0, 5);
    }

    @Override
    public Map<String, String[]> realTimeSearch(String searchValue) {
        String searchKey = SearchKeyUtil.getStrForMYSQL(searchValue);
//        String searchKey = "%" + searchValue + "%";
        Map<String, String[]> obj = new HashMap<>();
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookAuthorLike(searchKey);
        List<Book> author = bookMapper.selectByExample(bookExample);
        String[] ar = new String[author.size()];
        for (int i = 0; i < author.size(); i++)
            ar[i] = author.get(i).getBookAuthor();
        obj.put("author", ar);

        BookExample bookExample1 = new BookExample();
        BookExample.Criteria criteria1 = bookExample1.createCriteria();
        criteria1.andBookNameLike(searchKey);
        List<Book> bookName = bookMapper.selectByExample(bookExample1);
        ar = new String[bookName.size()];
        for (int i = 0; i < bookName.size(); i++)
            ar[i] = bookName.get(i).getBookName();
        obj.put("bookName", ar);

        BookExample bookExample2 = new BookExample();
        BookExample.Criteria criteria2 = bookExample2.createCriteria();
        criteria2.andBookPressLike(searchKey);
        List<Book> bookPress = bookMapper.selectByExample(bookExample2);
        ar = new String[bookPress.size()];
        for (int i = 0; i < bookPress.size(); i++)
            ar[i] = bookPress.get(i).getBookPress();
        obj.put("bookPress", ar);
        return obj;
    }

    @Override
    public Map<String, List<Book>> getSearchBook(String searchValue) {
        String searchKey = "%" + searchValue + "%";
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookNameLike(searchKey);

        BookExample.Criteria criteria1 = bookExample.createCriteria();
        criteria1.andBookPressLike(searchKey);

        BookExample.Criteria criteria2 = bookExample.createCriteria();
        criteria2.andBookAuthorLike(searchKey);

        bookExample.or(criteria1);
        bookExample.or(criteria2);

        List<Book> list = bookMapper.selectByExample(bookExample);
        Map<String, List<Book>> result = new HashMap<>();
        result.put("comprehensive", list);

        bookExample.setOrderByClause("book_price asc");
        List<Book> asc = bookMapper.selectByExample(bookExample);
        result.put("priceasc", asc);

        bookExample.setOrderByClause("book_price desc");
        List<Book> desc = bookMapper.selectByExample(bookExample);
        result.put("pricedesc", desc);

        bookExample.setOrderByClause("book_sale desc");
        List<Book> sale = bookMapper.selectByExample(bookExample);
        result.put("saledesc", sale);

        return result;
    }

    @Override
    public Map<String, Object> getSort() {
        ClassifiedExample classifiedExample = new ClassifiedExample();
        ClassifiedExample.Criteria criteria = classifiedExample.createCriteria();
        criteria.andClassifiedNameIsNotNull();
        List<Classified> list = classifiedMapper.selectByExample(classifiedExample);

        Map<String, Object> result = new HashMap<>();
        String[] classified = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            classified[i] = list.get(i).getClassifiedName();
            int classifiedId = list.get(i).getClassifiedId();
            GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria1 = getClassifiedExample.createCriteria();
            criteria1.andClassifiedIdEqualTo(classifiedId);
            List<GetClassified> getClassifieds = getClassifiedMapper.selectByExample(getClassifiedExample);

            List<Book> content = new ArrayList<>();
            getClassifieds.forEach((GetClassified temp) -> {
                BookExample bookExample = new BookExample();
                BookExample.Criteria criteria2 = bookExample.createCriteria();
                criteria2.andBookIdEqualTo(temp.getBookId());
//                bookExample.setOrderByClause("book_sale desc");
                List<Book> book = bookMapper.selectByExample(bookExample);
                content.add(book.get(0));
            });

            result.put(list.get(i).getClassifiedName(), content);
        }
        result.put("classified", classified);
        return result;
    }

    @Override
    public List<Coupon> getCoupon() {//获取优惠卷

        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        couponExample.setOrderByClause("coupon_vaild desc");
        Date date = new Date();
        criteria.andCouponVaildGreaterThan(date);
//        criteria.andCouponNumGreaterThan(0);
        List<Coupon> result = couponMapper.selectByExample(couponExample);
        return result;
    }

    @Override
    public int gainCoupon(String userId, int couponId) {
        GetCouponExample getCouponExample = new GetCouponExample();
        GetCouponExample.Criteria criteria = getCouponExample.createCriteria();
        criteria.andCouponIdEqualTo(couponId);
        criteria.andUserIdEqualTo(userId);
        List<GetCoupon> list = getCouponMapper.selectByExample(getCouponExample);
        if (list.size() > 0)
            return 0;
        else {
            CouponExample couponExample = new CouponExample();
            CouponExample.Criteria criteria1 = couponExample.createCriteria();
            criteria1.andCouponIdEqualTo(couponId);
            List<Coupon> coupon = couponMapper.selectByExample(couponExample);
            //判断是否有足够优惠卷
            if (coupon.get(0).getCouponGetnum() < coupon.get(0).getCouponNum()) {
                GetCoupon getCoupon = new GetCoupon();
                getCoupon.setUserId(userId);
                getCoupon.setCouponId(couponId);
                getCoupon.setCouponNum(1);
                int result = getCouponMapper.insert(getCoupon);
                return result;
            } else {
                return -1;
            }
        }
    }

    @Override
    public Map<String, Object> getMessage(String userId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Message> list = messageMapper.selectByExample(messageExample);

        Map<String, Object> result = new HashMap<>();
      /*  List<MorningPaper> read = new ArrayList<>();
        List<MorningPaper> unread = new ArrayList<>();*/
        List<Message> read = new ArrayList<>();
        List<Message> unread = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
          /*  int paperId = list.get(i).getPaperId();
            MorningPaperExample morningPaperExample = new MorningPaperExample();
            MorningPaperExample.Criteria criteria1 = morningPaperExample.createCriteria();
            criteria1.andPaperIdEqualTo(paperId);
            List<MorningPaper> list1 = morningPaperMapper.selectByExample(morningPaperExample);
            if (list.get(i).getPaperState() == 1)
                read.add(list1.get(0));
            else if (list.get(i).getPaperState() == 0)
                unread.add(list1.get(0));*/

            if (list.get(i).getPaperState() == 1)
                read.add(list.get(i));
            else if (list.get(i).getPaperState() == 0)
                unread.add(list.get(i));
        }
        result.put("read", read);
        result.put("unread", unread);
        return result;
    }

    @Override
    public int tagRead(String userId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPaperStateEqualTo(0);
        List<Message> list = messageMapper.selectByExample(messageExample);
        int result = 0;
        if (list.size() > 0) {
            list.forEach((Message message) -> {
                Message message1 = new Message();
                message1.setPaperState(1);
                messageMapper.updateByExampleSelective(message1, messageExample);
            });
            result = 1;
        }
        return result;
    }

    @Override
    public int deleteOneMess(String userId, int paperId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPaperIdEqualTo(paperId);
        int result = messageMapper.deleteByExample(messageExample);
        return result;
    }

    @Override
    public int tagOneMessage(String userId, int paperId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPaperIdEqualTo(paperId);
        Message message = new Message();
        message.setPaperState(1);
        int result = messageMapper.updateByExampleSelective(message, messageExample);
        return result;
    }

    @Override
    public int clearMessage(String userId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        int result = messageMapper.deleteByExample(messageExample);
        return result;
    }

    @Override
    public ArrayList reqTopic() {
        ArrayList result = new ArrayList<>();
        TopicExample topicExample = new TopicExample();
        List<Topic> list = topicMapper.selectByExample(topicExample);
        list.forEach((Topic topic) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("topicId", topic.getTopicId());
            map.put("topicImg", topic.getTopicImg());
            map.put("topicName", topic.getTopicName());
            TopicBookExample topicBookExample = new TopicBookExample();
            TopicBookExample.Criteria criteria = topicBookExample.createCriteria();
            criteria.andTopicIdEqualTo(topic.getTopicId());
            List<TopicBook> list1 = topicBookMapper.selectByExample(topicBookExample);
            List<Book> books = new ArrayList<>();
            list1.forEach((TopicBook t) -> {
                Book book = bookMapper.selectByPrimaryKey(t.getBookId());
                books.add(book);
            });
            map.put("books", books);
            result.add(map);
        });
        return result;
    }

    @Override
    public boolean reqUnReadCount(String userId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPaperStateEqualTo(0);
        List<Message> list = messageMapper.selectByExample(messageExample);
        return list.size() > 0 ? true : false;
    }
}
