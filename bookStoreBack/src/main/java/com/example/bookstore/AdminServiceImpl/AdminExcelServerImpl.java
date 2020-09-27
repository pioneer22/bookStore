package main.java.com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminExcelServer;
import com.example.bookstore.dao.AllOrderMapper;
import com.example.bookstore.dao.BookMapper;
import com.example.bookstore.dao.ContactsMapper;
import com.example.bookstore.dao.ItemMapper;
import com.example.bookstore.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminExcelServerImpl implements AdminExcelServer {

    @Autowired
    private AllOrderMapper allOrderMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Map<String,Object> getOrderMess() {
        Map<String,Object> result = new HashMap<>();
        List<Object> all = new ArrayList<>(); //全部订单
        List<Object> waitGet = new ArrayList<>(); //待收货
        List<Object> replace = new ArrayList<>(); //退换货
        List<Object> complete = new ArrayList<>(); //已完成
        AllOrderExample allOrderExample = new AllOrderExample();
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        list.forEach((AllOrder allOrder)->{
            Map<String,Object> map = new HashMap<>();
            map.put("orderId",allOrder.getOrderId());
            map.put("userId",allOrder.getUserId());
            map.put("orderTime",allOrder.getOrderTime());
            map.put("orderPrice",allOrder.getOrderAllprice());
            switch (allOrder.getOrderStatus()){
                case 0:
                    map.put("orderStatu","待付款");
                    break;
                case 1:
                    map.put("orderStatu","待收货");
                    break;
                case 2:
                    map.put("orderStatu","待评价");
                    break;
                case 3:
                    map.put("orderStatu","退换货");
                    break;
                case 4:
                    map.put("orderStatu","已完成交易");
                    break;
            }
            ContactsExample contactsExample = new ContactsExample();
            ContactsExample.Criteria criteria = contactsExample.createCriteria();
            criteria.andContactIdEqualTo(allOrder.getContactId());
            criteria.andUserIdEqualTo(allOrder.getUserId());
            List<Contacts> contacts = contactsMapper.selectByExample(contactsExample);
            map.put("contactMess",contacts.get(0));
            ItemExample itemExample = new ItemExample();
            ItemExample.Criteria criteria1 = itemExample.createCriteria();
            criteria1.andOrderIdEqualTo(allOrder.getOrderId());
            List<Item> item = itemMapper.selectByExample(itemExample);
            List<Object> books = new ArrayList<>();
            item.forEach((Item i)->{
                Book book = bookMapper.selectByPrimaryKey(i.getBookId());
                Map<String,Object> bookItem = new HashMap<>();
                bookItem.put("bookNum", i.getShopNum());
                bookItem.put("bookName",book.getBookName());
                bookItem.put("bookPrice",book.getBookPrice());
                books.add(bookItem);
            });
            map.put("books",books);
            all.add(map);
            if (allOrder.getOrderStatus() == 1)
                waitGet.add(map);
            else if(allOrder.getOrderStatus() == 3)
                replace.add(map);
            else if(allOrder.getOrderStatus() == 2 || allOrder.getOrderStatus() == 4)
                complete.add(map);
        });
        result.put("all",all);
        result.put("waitGet",waitGet);
        result.put("replace",replace);
        result.put("complete",complete);
        return result;
    }

    @Override
    public List<Book> getBookMess() {
        BookExample bookExample = new BookExample();
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public int importExcel(List<Map<String,Object>> books) {
        int result = 0;
        for (Map<String,Object> book : books) {
            Book book1 = new Book();
            Long lt = new Long(Long.valueOf((Integer)book.get("bookTime")));
            book1.setBookImg((String) book.get("bookImg"));
            book1.setBookAuthor((String) book.get("bookAuthor"));
            book1.setBookIntro((String) book.get("bookIntro"));
            book1.setBookName((String) book.get("bookName"));
            book1.setBookNum((Integer)book.get("bookNum"));
            book1.setBookPress((String) book.get("bookPress"));
            book1.setBookTime(new Date(lt));
            book1.setBookPrice(Double.valueOf(String.valueOf(book.get("bookPrice"))));
            book1.setBookSale(0);
            result = bookMapper.insertSelective(book1);
        }
        return result;
    }
}
