package main.java.com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminChartsServer;
import com.example.bookstore.dao.*;
import com.example.bookstore.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminChartsServerImpl implements AdminChartsServer{

    @Autowired
    private AllOrderMapper allOrderMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ClassifiedMapper classifiedMapper;

    @Autowired
    private GetClassifiedMapper getClassifiedMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Map<String, ArrayList> reqShowData() {
        ArrayList saleNum = new ArrayList<>();
        ArrayList saleMoney = new ArrayList<>();
        ArrayList classified = new ArrayList<>();
        ArrayList classifiedNum = new ArrayList<>();
        for (int i = 0; i <12 ; i++) {
            saleNum.add(0);
            saleMoney.add(0.0);
        }
        //当前月第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = cale.getTime();
        // 上一年当前月第一天
        cale = Calendar.getInstance();
        cale.setTime(firstDay);
        cale.add(Calendar.YEAR, -1);
        Date lastYearMonth = cale.getTime();

        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        criteria.andOrderTimeBetween(lastYearMonth,firstDay);
        criteria.andOrderStatusGreaterThan(0); // 除了待付款
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH);//从0开始
        list.forEach((AllOrder ao)->{
            Calendar c = Calendar.getInstance();
            c.setTime(ao.getOrderTime());
            int itMonth = c.get(Calendar.MONTH);
            int index = itMonth - month >= 0 ? itMonth - month : (itMonth - month + 12);
            saleMoney.set(index,(Double)saleMoney.get(index) + ao.getOrderAllprice());
            ItemExample itemExample = new ItemExample();
            ItemExample.Criteria criteria1 = itemExample.createCriteria();
            criteria1.andOrderIdEqualTo(ao.getOrderId());
            List<Item> item = itemMapper.selectByExample(itemExample);
            item.forEach((Item i)->{
                saleNum.set(index,(Integer)saleNum.get(index) + i.getShopNum());
            });
        });
        Map<String,ArrayList> map = new HashMap<>();
        map.put("saleNum",saleNum);
        map.put("saleMoney",saleMoney);

        ClassifiedExample classifiedExample = new ClassifiedExample();
        ClassifiedExample.Criteria criteria1 = classifiedExample.createCriteria();
        criteria1.andClassifiedIdIsNotNull();
        List<Classified> list1 = classifiedMapper.selectByExample(classifiedExample);
        list1.forEach((Classified cd)->{
            classified.add(cd.getClassifiedName());
            GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria2 = getClassifiedExample.createCriteria();
            criteria2.andClassifiedIdEqualTo(cd.getClassifiedId());
            int num = getClassifiedMapper.countByExample(getClassifiedExample);
            classifiedNum.add(num);
        });
        map.put("classified",classified);
        map.put("classifiedNum",classifiedNum);

        BookExample bookExample = new BookExample();
        bookExample.setOrderByClause("book_sale desc");
        List<Book> books = bookMapper.selectByExample(bookExample);
        ArrayList bookName = new ArrayList<>();
        ArrayList bookSale = new ArrayList<>();
        ArrayList bookNum = new ArrayList<>();
        books.forEach((Book book)->{
            bookName.add(book.getBookName());
            bookSale.add(book.getBookSale());
            bookNum.add(book.getBookNum());
        });
        map.put("bookName",bookName);
        map.put("bookSale",bookSale);
        map.put("bookNum",bookNum);
        return map;
    }

    @Override
    public Map<String, Object> reqPieData() {
        Map<String,Object> classifiedName = new HashMap<>();
        ArrayList saleNum = new ArrayList<>();
        AllOrderExample allOrderExample = new AllOrderExample();
        allOrderExample.or().andOrderStatusEqualTo(2);
        allOrderExample.or().andOrderStatusEqualTo(4);
        List<AllOrder> allOrders = allOrderMapper.selectByExample(allOrderExample);
        allOrders.forEach((AllOrder ao)->{
            ItemExample itemExample = new ItemExample();
            ItemExample.Criteria criteria = itemExample.createCriteria();
            criteria.andOrderIdEqualTo(ao.getOrderId());
            List<Item> items = itemMapper.selectByExample(itemExample);
            items.forEach((Item i)->{
                GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
                GetClassifiedExample.Criteria criteria1 = getClassifiedExample.createCriteria();
                criteria1.andBookIdEqualTo(i.getBookId());
                List<GetClassified> gc = getClassifiedMapper.selectByExample(getClassifiedExample);
                gc.forEach((GetClassified g)->{
                    Classified classified = classifiedMapper.selectByPrimaryKey(g.getClassifiedId());
                    if(classifiedName.get(classified.getClassifiedName()) != null){
                        classifiedName.put(classified.getClassifiedName(),(int)classifiedName.get(classified.getClassifiedName()) + i.getShopNum());
                    } else {
                        saleNum.add(classified.getClassifiedName());
                        classifiedName.put(classified.getClassifiedName(),i.getShopNum());
                    }
                });
            });
        });
        Map<String,Object> result = new HashMap<>();
        result.put("classifiedName",classifiedName);
        result.put("saleNum",saleNum);
        return result;
    }

    @Override
    public Map<String, Object> reqSaleMoney() {

        ArrayList saleMoney = new ArrayList<>();
        for (int i = 0; i <12 ; i++) {
            saleMoney.add(0.0);
        }
        //当前月第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = cale.getTime();
        // 上一年当前月第一天
        cale = Calendar.getInstance();
        cale.setTime(firstDay);
        cale.add(Calendar.YEAR, -1);
        Date lastYearMonth = cale.getTime();

        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        criteria.andOrderTimeBetween(lastYearMonth,firstDay);
        criteria.andOrderStatusGreaterThan(0); // 除了待付款
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
//        cale = Calendar.getInstance();
//        int month = cale.get(Calendar.MONTH);//从0开始
        list.forEach((AllOrder ao)->{
            Calendar c = Calendar.getInstance();
            c.setTime(ao.getOrderTime());
            int index = c.get(Calendar.MONTH);
            saleMoney.set(index,(Double)saleMoney.get(index) + ao.getOrderAllprice());
        });
        Map<String,Object> map = new HashMap<>();
        map.put("saleMoney",saleMoney);
        return map;
    }
}
