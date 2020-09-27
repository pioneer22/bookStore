package main.java.com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminOrderServer;
import com.example.bookstore.dao.*;
import com.example.bookstore.pojo.*;
import com.example.bookstore.utils.dto.GetOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminOrderServerImpl implements AdminOrderServer {

    @Autowired
    private AllOrderMapper allOrderMapper;

    @Autowired
    private BookOrderMapper bookOrderMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Map<String, Object> getOrder() {

        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        allOrderExample.setOrderByClause("order_time desc");
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        Map<String,Object> result = new HashMap<>();

        List<GetOrderDto> allOrder = new ArrayList<>();
        List<GetOrderDto> waitPay = new ArrayList<>();
        List<GetOrderDto> waitGet = new ArrayList<>();
        List<GetOrderDto> replace = new ArrayList<>();
        List<GetOrderDto> achieve = new ArrayList<>();
        list.forEach((AllOrder item)->{
            GetOrderDto getOrderDto = new GetOrderDto();
            getOrderDto.setAllOrder(item);
            ContactsExample contactsExample = new ContactsExample();
            ContactsExample.Criteria criteria1 = contactsExample.createCriteria();
            criteria1.andUserIdEqualTo(item.getUserId());
            criteria1.andContactIdEqualTo(item.getContactId());
            getOrderDto.setContacts(contactsMapper.selectByExample(contactsExample).get(0));

            ItemExample itemExample = new ItemExample();
            ItemExample.Criteria criteria2 = itemExample.createCriteria();
            criteria2.andOrderIdEqualTo(item.getOrderId());
            List<Item> items = itemMapper.selectByExample(itemExample);
            List<GetOrderDto.BookMess> bookMess= new ArrayList<>();
            items.forEach((Item item1)->{
                GetOrderDto.BookMess bookMesses = new GetOrderDto.BookMess();
                bookMesses.setBook(bookMapper.selectByPrimaryKey(item1.getBookId()));
                bookMesses.setCount(item1.getShopNum());
                bookMess.add(bookMesses);
            });
            getOrderDto.setBookList(bookMess);
            allOrder.add(getOrderDto);
            if(item.getOrderStatus() == 0)
                waitPay.add(getOrderDto);
            else if(item.getOrderStatus() == 1)
                waitGet.add(getOrderDto);
            else if(item.getOrderStatus() == 2)
                replace.add(getOrderDto);
            else if(item.getOrderStatus() == 4 || item.getOrderStatus() == 3)
                achieve.add(getOrderDto);
        });
        result.put("allOrder",allOrder);
        result.put("waitPay",waitPay);
        result.put("waitGet",waitGet);
        result.put("replace",replace);
        result.put("achieve",achieve);
        return result;
    }

    @Override
    public int deleteOrder(String orderId) {
        int result = allOrderMapper.deleteByPrimaryKey(orderId);
        return result;
    }

    @Override
    public int remindPay(String userId, String orderId) {
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        String sendMess = "%"+orderId+"%";
        criteria.andSendMessLike(sendMess);
        List<Message> list = messageMapper.selectByExample(messageExample);
        if(list.size()>0)
            return 0;
        else{
            Message message = new Message();
            message.setUserId(userId);
            Date date = new Date();
            message.setSendTime(date);
            message.setPaperState(0);
            String mess = "您的订单" + orderId + "已提交，请及时付款";
            message.setSendMess(mess);
            int result = messageMapper.insertSelective(message);
            return result;
        }
    }

    @Override
    public int changeContact(List<Object> contactMess) {
        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        criteria.andOrderIdEqualTo((String) contactMess.get(3));
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        Contacts contacts = new Contacts();
        contacts.setStatu(0);
        contacts.setUserId(list.get(0).getUserId());
        contacts.setContactName((String) contactMess.get(0));
        contacts.setContactPhone((String) contactMess.get(1));
        contacts.setContactAddress((String) contactMess.get(2));
        contactsMapper.insertSelective(contacts);

        ContactsExample contactsExample = new ContactsExample();
        ContactsExample.Criteria criteria1 = contactsExample.createCriteria();
        criteria1.andUserIdEqualTo(list.get(0).getUserId());
        criteria1.andContactNameEqualTo((String) contactMess.get(0));
        criteria1.andContactPhoneEqualTo((String) contactMess.get(1));
        criteria1.andContactAddressEqualTo((String) contactMess.get(2));
        List<Contacts> list1 = contactsMapper.selectByExample(contactsExample);

        list.get(0).setContactId(list1.get(0).getContactId());
        int result = allOrderMapper.updateByExample(list.get(0),allOrderExample);

        BookOrderExample bookOrderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria2 = bookOrderExample.createCriteria();
        criteria2.andOrderIdEqualTo((String) contactMess.get(3));
        List<BookOrder> list2 = bookOrderMapper.selectByExample(bookOrderExample);
        list2.get(0).setContactId(list1.get(0).getContactId());
        bookOrderMapper.updateByExample(list2.get(0),bookOrderExample);
        return result;
    }

    @Override
    public Map<String, Object> searchOrder(String searchKey) {
        String key = "%" + searchKey + "%";
        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        allOrderExample.setOrderByClause("order_time desc");
        criteria.andOrderIdLike(key);
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        Map<String,Object> result = new HashMap<>();

        List<GetOrderDto> allOrder = new ArrayList<>();
        List<GetOrderDto> waitPay = new ArrayList<>();
        List<GetOrderDto> waitGet = new ArrayList<>();
        List<GetOrderDto> replace = new ArrayList<>();
        List<GetOrderDto> achieve = new ArrayList<>();
        list.forEach((AllOrder item)->{
            GetOrderDto getOrderDto = new GetOrderDto();
            getOrderDto.setAllOrder(item);
            ContactsExample contactsExample = new ContactsExample();
            ContactsExample.Criteria criteria1 = contactsExample.createCriteria();
            criteria1.andUserIdEqualTo(item.getUserId());
            criteria1.andContactIdEqualTo(item.getContactId());
            getOrderDto.setContacts(contactsMapper.selectByExample(contactsExample).get(0));

            ItemExample itemExample = new ItemExample();
            ItemExample.Criteria criteria2 = itemExample.createCriteria();
            criteria2.andOrderIdEqualTo(item.getOrderId());
            List<Item> items = itemMapper.selectByExample(itemExample);
            List<GetOrderDto.BookMess> bookMess= new ArrayList<>();
            items.forEach((Item item1)->{
                GetOrderDto.BookMess bookMesses = new GetOrderDto.BookMess();
                bookMesses.setBook(bookMapper.selectByPrimaryKey(item1.getBookId()));
                bookMesses.setCount(item1.getShopNum());
                bookMess.add(bookMesses);
            });
            getOrderDto.setBookList(bookMess);
            allOrder.add(getOrderDto);
            if(item.getOrderStatus() == 0)
                waitPay.add(getOrderDto);
            else if(item.getOrderStatus() == 1)
                waitGet.add(getOrderDto);
            else if(item.getOrderStatus() == 2)
                replace.add(getOrderDto);
            else if(item.getOrderStatus() == 4)
                achieve.add(getOrderDto);
        });
        result.put("allOrder",allOrder);
        result.put("waitPay",waitPay);
        result.put("waitGet",waitGet);
        result.put("replace",replace);
        result.put("achieve",achieve);
        return result;
    }
}
