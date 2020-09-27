package main.java.com.example.bookstore.UserServiceImpl;

import com.example.bookstore.UserService.MyCenterServer;
import com.example.bookstore.dao.*;
import com.example.bookstore.pojo.*;
import com.example.bookstore.utils.ServerResponse;
import com.example.bookstore.utils.dto.ItemAndBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyCenterServerImpl implements MyCenterServer {

    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private GetClassifiedMapper getClassifiedMapper;
    @Autowired
    private ClassifiedMapper classifiedMapper;
    @Autowired
    private GetCouponMapper getCouponMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BookOrderMapper bookOrderMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ServerResponse changeMess(String userid, String oldPass, String newPass) {
        //修改密码
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(userid);
        criteria.andUserPassEqualTo(oldPass);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            return ServerResponse.createByError("密码输入错误！");
        } else {
            User user = users.get(0);//拿到符合的对象
            user.setUserPass(newPass);
            userMapper.updateByPrimaryKey(user);
            return ServerResponse.createBySuccess("密码修改成功！", user);
        }
    }

    @Override
    public ServerResponse getContacts(String id) {
        //获取联系人
        ContactsExample contactsExample = new ContactsExample();
        contactsExample.setOrderByClause("statu desc");
        ContactsExample.Criteria criteria = contactsExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<Contacts> contacts = contactsMapper.selectByExample(contactsExample);
        if (contacts != null && contacts.size() > 0) {
            return ServerResponse.createBySuccess("获取联系人成功!", contacts);
        } else {
            return ServerResponse.createByError("获取联系人失败！");
        }
    }

    @Override
    public int insertContacts(String id, String contactName, String contactPhone, String contactAddress) {
//       新增联系人
        Contacts contacts = new Contacts();
        contacts.setUserId(id);
        contacts.setContactName(contactName);
        contacts.setContactPhone(contactPhone);
        contacts.setContactAddress(contactAddress);
        contacts.setStatu(0);
        int result = contactsMapper.insert(contacts);
        return result;
    }

    @Override
    public boolean updateContacts(Contacts contacts) {
        //修改联系人
        int result = contactsMapper.updateByPrimaryKey(contacts);
        return result > 0 ? true : false;
    }

    @Override
    public Contacts getContactsInfo(int contactid) {
        Contacts contacts = contactsMapper.selectByPrimaryKey(contactid);
        return contacts;
    }

    @Override
    public boolean deleteContacts(int contactid) {
        //删除联系人
        int result = contactsMapper.deleteByPrimaryKey(contactid);
        return result > 0 ? true : false;
    }

    @Override
    public Object getAllCollect(String userId) {
        //个人收藏信息
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andCollectStatuEqualTo(1);
        List<Collect> list = collectMapper.selectByExample(collectExample);

        Map<String, Object> classifiedMap = new HashMap<>();//分类 Map
        Set<String> classfiedNameSet = new TreeSet<>();//有序的，没有重复元素的集合
        int[] count = new int[list.size()];
        // 遍历书籍
        for (int i = 0; i < list.size(); i++) {
            int temp = list.get(i).getBookId();
            CollectExample collectExampleCount = new CollectExample();
            CollectExample.Criteria criteria4 = collectExampleCount.createCriteria();
            criteria4.andBookIdEqualTo(temp);
            criteria4.andCollectStatuEqualTo(1);
            count[i] = collectMapper.countByExample(collectExampleCount);

            //得到当前书分类id
            GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria2 = getClassifiedExample.createCriteria();
            criteria2.andBookIdEqualTo(temp);
            List<GetClassified> getClassified = getClassifiedMapper.selectByExample(getClassifiedExample);
            //得到分类名
            getClassified.forEach((GetClassified item) -> {
                ClassifiedExample classifiedExample = new ClassifiedExample();
                ClassifiedExample.Criteria criteria3 = classifiedExample.createCriteria();
                criteria3.andClassifiedIdEqualTo(item.getClassifiedId());
                Classified classified = classifiedMapper.selectByExample(classifiedExample).get(0);

                String classifiedName = classified.getClassifiedName();
                classfiedNameSet.add(classifiedName);
                List<Book> books = (List<Book>) classifiedMap.get(classifiedName);

                BookExample bookExample = new BookExample();
                BookExample.Criteria criteria1 = bookExample.createCriteria();
                criteria1.andBookIdEqualTo(item.getBookId());
                List<Book> book = bookMapper.selectByExample(bookExample);

                if (books == null)
                    books = new ArrayList<>();
                books.add(book.get(0));
                classifiedMap.put(classifiedName, books);
            });

        }
        classifiedMap.put("classfiedNameSet", classfiedNameSet);
        classifiedMap.put("count", count);
        return classifiedMap;
    }

    @Override
    public ArrayList myCoupon(String userId) {
        ArrayList result = new ArrayList();
        List<Coupon> coupons = new ArrayList<>();//未使用
        List<Coupon> coupons1 = new ArrayList<>();//已使用
        List<Coupon> coupons2 = new ArrayList<>();//已失效

        GetCouponExample getCouponExample = new GetCouponExample();
        GetCouponExample.Criteria criteria = getCouponExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        Date date = new Date();
        List<GetCoupon> list = getCouponMapper.selectByExample(getCouponExample);
        list.forEach((GetCoupon item) -> {
            int couponId = item.getCouponId();
            CouponExample couponExample = new CouponExample();
            CouponExample.Criteria criteria2 = couponExample.createCriteria();
            criteria2.andCouponIdEqualTo(couponId);
            criteria2.andCouponVaildGreaterThan(date);
            List<Coupon> list1 = couponMapper.selectByExample(couponExample);
            if (list1.size() > 0 && item.getCouponNum() > 0)
                coupons.add(list1.get(0));
            if (list1.size() > 0 && item.getCouponNum() == 0)
                coupons1.add(list1.get(0));

            CouponExample couponExample1 = new CouponExample();
            CouponExample.Criteria criteria3 = couponExample1.createCriteria();
            criteria3.andCouponIdEqualTo(couponId);
            criteria3.andCouponVaildLessThan(date);
            List<Coupon> list2 = couponMapper.selectByExample(couponExample1);
            if (list2.size() > 0 && item.getCouponNum() > 0)
                coupons2.add(list2.get(0));
        });
        result.add(coupons);
        result.add(coupons1);
        result.add(coupons2);
        return result;
    }

    @Override
    public Map<String, Object> getChat(String userId) {
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Chat> list = chatMapper.selectByExample(chatExample);
        Map<String, Object> result = new HashMap<>();
        result.put("chat", list);
        Business list1 = businessMapper.selectByPrimaryKey(list.get(0).getBusinessId());
        result.put("business", list1);
        return result;
    }

    @Override
    public int saveChat(ArrayList list) {
        Chat chat = new Chat();
        chat.setUserId((String) list.get(0));
        chat.setBusinessId((String) list.get(1));
        chat.setStatu((int) list.get(2));
        chat.setSendTime((Date) list.get(3));
        chat.setContext((String) list.get(4));
        int result = chatMapper.insertSelective(chat);
        return result;
    }

    @Override
    public Map<String, Object> getOrder(String userId) {
        //拿到所有订单
        BookOrderExample bookOrderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria = bookOrderExample.createCriteria();
        bookOrderExample.setOrderByClause("order_time desc");
        criteria.andUserIdEqualTo(userId);
        List<BookOrder> list = bookOrderMapper.selectByExample(bookOrderExample);//所有订单

        Map<String, Object> result = new HashMap<>();
        List<Object> all = new ArrayList<>();//全部订单
        List<Object> Obligations = new ArrayList<>();//待付款
        List<Object> receipt = new ArrayList<>();//待收货
        List<Object> evaluated = new ArrayList<>();//待评价
        List<Object> replace = new ArrayList<>();//退换货

        //通过订单号获取订单条目
        list.forEach((BookOrder item) -> {
            //订单号获取订单下的条目信息
            ItemExample itemExample = new ItemExample();
            ItemExample.Criteria criteria1 = itemExample.createCriteria();
            criteria1.andOrderIdEqualTo(item.getOrderId());
            List<Item> list1 = itemMapper.selectByExample(itemExample);
            List<ItemAndBookDto> eachItemBook = new ArrayList<>();//这就相当于一个订单了
            list1.forEach((Item item1) -> {
                BookExample bookExample = new BookExample();
                BookExample.Criteria criteria2 = bookExample.createCriteria();
                criteria2.andBookIdEqualTo(item1.getBookId());
                List<Book> books = bookMapper.selectByExample(bookExample);//只有一本书
                ItemAndBookDto dto = new ItemAndBookDto();
                dto.setBook(books.get(0));//book 和 item 关联到一个对象中
                dto.setItem(item1);
                eachItemBook.add(dto);//这个订单条目完成
            });

            Map<String, Object> orderInfo = new HashMap<>();
            //把书的订单详情关联 并put
            BookOrderExample bookOrderExample1 = new BookOrderExample();
            BookOrderExample.Criteria bookCriteria = bookOrderExample1.createCriteria();
            bookCriteria.andOrderIdEqualTo(item.getOrderId());
            List<BookOrder> bookOrders = bookOrderMapper.selectByExample(bookOrderExample1);
            BookOrder bookOrder = bookOrders.get(0);
            orderInfo.put("orderId", bookOrder.getOrderId());//订单号
            orderInfo.put("orderTime", bookOrder.getOrderTime().getTime());//时间
            orderInfo.put("orderStatus", bookOrder.getOrderStatus());//订单状态
            orderInfo.put("orderPrice", bookOrder.getOrderAllprice());//总价
            orderInfo.put("userMessage", bookOrder.getUserLeaveMess());//留言
            //关联一个联系人
            ContactsExample contactsExample = new ContactsExample();
            ContactsExample.Criteria contactsExampleCriteria = contactsExample.createCriteria();
            contactsExampleCriteria.andContactIdEqualTo(bookOrder.getContactId());
            List<Contacts> contacts = contactsMapper.selectByExample(contactsExample);

            Map<String, Object> orderItemInfo = new HashMap<>();
            orderItemInfo.put("item", eachItemBook);
            orderItemInfo.put("orderInfo", orderInfo);
            orderItemInfo.put("contactInfo", contacts.get(0));

            //完成1张订单
            all.add(orderItemInfo);
            switch (item.getOrderStatus()) {
                case 0://
                    Obligations.add(orderItemInfo);
                    break;
                case 1:
                    receipt.add(orderItemInfo);
                    break;
                case 2:
                    evaluated.add(orderItemInfo);
                    break;
                case 3:
                    replace.add(orderItemInfo);
                    break;
            }
        });
        result.put("all", all);
        result.put("Obligations", Obligations);
        result.put("receipt", receipt);
        result.put("evaluated", evaluated);
        result.put("replace", replace);
        return result;
    }

    @Override
    public int deleteOneOrder(String orderId) {
        int result = bookOrderMapper.deleteByPrimaryKey(orderId);
        return result;
    }

    @Override
    public int setDefaultAddrss(String userId, int contactId) {
        ContactsExample contactsExample = new ContactsExample();
        ContactsExample.Criteria criteria = contactsExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatuEqualTo(1);
        List<Contacts> list = contactsMapper.selectByExample(contactsExample);
        list.get(0).setStatu(0);
        contactsMapper.updateByExampleSelective(list.get(0),contactsExample);

        ContactsExample contactsExample1 = new ContactsExample();
        ContactsExample.Criteria criteria1 = contactsExample1.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        criteria1.andContactIdEqualTo(contactId);
        List<Contacts> list1 = contactsMapper.selectByExample(contactsExample1);
        list1.get(0).setStatu(1);
        int result = contactsMapper.updateByExampleSelective(list1.get(0),contactsExample1);
        return result;
    }

    @Override
    public List<Coupon> personCoupon(String userId) {
        GetCouponExample getCouponExample = new GetCouponExample();
        GetCouponExample.Criteria criteria = getCouponExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<GetCoupon> list = getCouponMapper.selectByExample(getCouponExample);

        Date date = new Date();
        List<Coupon> coupons = new ArrayList<>();
        list.forEach((GetCoupon item)->{
            int couponId = item.getCouponId();
            CouponExample couponExample = new CouponExample();
            CouponExample.Criteria criteria1 = couponExample.createCriteria();
            criteria1.andCouponIdEqualTo(couponId);
            criteria1.andCouponVaildGreaterThan(date);
            List<Coupon> list1 = couponMapper.selectByExample(couponExample);
            if(list1.size()>0 && item.getCouponNum() >0)
                coupons.add(list1.get(0));
        });

        return coupons;
    }
}
