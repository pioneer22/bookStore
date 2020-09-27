package main.java.com.example.bookstore.UserServiceImpl;

import com.example.bookstore.UserService.OrderServer;
import com.example.bookstore.dao.*;
import com.example.bookstore.pojo.*;
import com.example.bookstore.utils.dto.GenerateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServerImpl implements OrderServer{

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private BookOrderMapper bookOrderMapper;

    @Autowired
    private AllOrderMapper allOrderMapper;

    @Autowired
    private GetCouponMapper getCouponMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ShopCarMapper shopCarMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int getColllect(String userId, int bookId) {
        //获得收藏图书
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andBookIdEqualTo(bookId);
        criteria.andUserIdEqualTo(userId);
        List<Collect> collect = collectMapper.selectByExample(collectExample);
        //没有记录则插入
        if(collect.size()==0)
        {
            Collect ct = new Collect();
            ct.setBookId(bookId);
            ct.setUserId(userId);
            ct.setCollectStatu(0);
            collectMapper.insert(ct);
            return 0;
        }else{
            //有记录判断是已经收藏还是没收藏
            if(collect.get(0).getCollectStatu() == 1)
                return 1;
            else
                return 0;
        }
    }

    @Override
    public int toggleCollect(String userId, int bookId) {

        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andBookIdEqualTo(bookId);
        criteria.andUserIdEqualTo(userId);
        List<Collect> collect = collectMapper.selectByExample(collectExample);

        Collect ct = new Collect();
        ct.setBookId(bookId);
        ct.setUserId(userId);
        //判断是已经收藏还是没收藏
        if(collect.get(0).getCollectStatu() == 1)
        {
            ct.setCollectStatu(0);
            collectMapper.updateByExample(ct,collectExample);
            return 0;
        }else{
            ct.setCollectStatu(1);
            collectMapper.updateByExample(ct,collectExample);
            return 1;
        }
    }

    @Override
    public int[] getOrderStatuNum(String userId) {
        //获取订单图书状态数量
        BookOrderExample orderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<BookOrder> result = bookOrderMapper.selectByExample(orderExample);
        int[] orderStatu = new int[4];
        if(result.size()>0){
            for (int i = 0; i < result.size(); i++) {
//                排除已评价
                if(result.get(i).getOrderStatus() != 4)
                {
                    int num = result.get(i).getOrderStatus();
                    orderStatu[num] += 1;
                }
            }
        }
        return orderStatu;
    }

    @Override
    public String generateOrder(List<GenerateOrderDto.CarBook> carBookId, Object[] orderMess) {
        String userId = (String)orderMess[0];
        int contactId = (int)orderMess[1];
//        double allPrice = (double)orderMess[2];
        double allPrice = Double.valueOf((String)orderMess[2]);
        String leaveMess = (String)orderMess[3];
        int couponId = (int)orderMess[4];

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderId = userId + sdf.format(date);

        AllOrder allOrder = new AllOrder();
        allOrder.setOrderId(orderId);
        allOrder.setContactId(contactId);
        allOrder.setOrderAllprice(allPrice);
        allOrder.setOrderTime(date);
        allOrder.setOrderStatus(0);
        allOrder.setUserId(userId);
        allOrder.setUserLeaveMess(leaveMess);
        allOrderMapper.insert(allOrder);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setOrderId(orderId);
        bookOrder.setContactId(contactId);
        bookOrder.setOrderAllprice(allPrice);
        bookOrder.setOrderTime(date);
        bookOrder.setOrderStatus(0);
        bookOrder.setUserId(userId);
        bookOrder.setUserLeaveMess(leaveMess);
        int result = bookOrderMapper.insert(bookOrder);

        if(couponId != 0)
        {
            GetCouponExample getCouponExample = new GetCouponExample();
            GetCouponExample.Criteria criteria = getCouponExample.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andCouponIdEqualTo(couponId);
            List<GetCoupon> list = getCouponMapper.selectByExample(getCouponExample);
            list.get(0).setCouponNum(0);
            getCouponMapper.updateByExampleSelective(list.get(0),getCouponExample);
        }

        if(carBookId.size()>0)
            carBookId.forEach((GenerateOrderDto.CarBook item)->{
                int bookId = item.getBookId();
                int shopNum = item.getCount();

                //添加条目
                Item item1 = new Item();
                item1.setUserId(userId);
                item1.setBookId(bookId);
                item1.setOrderId(orderId);
                item1.setShopNum(shopNum);
                itemMapper.insertSelective(item1);

                //删除购物车对应
                ShopCarExample shopCarExample = new ShopCarExample();
                ShopCarExample.Criteria criteria = shopCarExample.createCriteria();
                criteria.andBookIdEqualTo(bookId);
                criteria.andUserIdEqualTo(userId);
                shopCarMapper.deleteByExample(shopCarExample);
            });
        return result>0 ? orderId : "0";
    }

    @Override
    public int setOrderStatu(String userId, String orderId) {

        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        allOrderExample.setOrderByClause("order_time desc");
        criteria.andUserIdEqualTo(userId);
//        criteria.andOrderIdEqualTo(orderId);
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        list.get(0).setOrderStatus(1);
        allOrderMapper.updateByPrimaryKey(list.get(0));

        BookOrderExample bookOrderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria1 = bookOrderExample.createCriteria();
        bookOrderExample.setOrderByClause("order_time desc");
        criteria1.andUserIdEqualTo(userId);
//        criteria1.andOrderIdEqualTo(orderId);
        List<BookOrder> list1 = bookOrderMapper.selectByExample(bookOrderExample);
        list1.get(0).setOrderStatus(1);
        int result = bookOrderMapper.updateByPrimaryKey(list1.get(0));
        return result;
    }

    @Override
    public int setOrderStatu1(String userId, String orderId) {

        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andOrderIdEqualTo(orderId);
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        list.get(0).setOrderStatus(1);
        allOrderMapper.updateByExample(list.get(0),allOrderExample);

        BookOrderExample bookOrderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria1 = bookOrderExample.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        criteria1.andOrderIdEqualTo(orderId);
        List<BookOrder> list1 = bookOrderMapper.selectByExample(bookOrderExample);
        list1.get(0).setOrderStatus(1);
        int result = bookOrderMapper.updateByExample(list1.get(0),bookOrderExample);
        return result;
    }

    @Override
    public int surePay(String orderId) {

        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        list.get(0).setOrderStatus(2);
        allOrderMapper.updateByExampleSelective(list.get(0),allOrderExample);

        BookOrderExample bookOrderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria1 = bookOrderExample.createCriteria();
        criteria1.andOrderIdEqualTo(orderId);
        List<BookOrder> list1 = bookOrderMapper.selectByExample(bookOrderExample);
        list1.get(0).setOrderStatus(2);
        int result = bookOrderMapper.updateByExampleSelective(list1.get(0),bookOrderExample);
        return result;
    }

    @Override
    public int cancelOrder(String orderId) {
        allOrderMapper.deleteByPrimaryKey(orderId);
        int result = bookOrderMapper.deleteByPrimaryKey(orderId);
        return result;
    }

    @Override
    public int submitComment(String orderId,String Comment,String userId) {

        String[] message = Comment.split(",");

        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria = allOrderExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<AllOrder> list = allOrderMapper.selectByExample(allOrderExample);
        list.get(0).setOrderStatus(4);
        allOrderMapper.updateByExampleSelective(list.get(0),allOrderExample);

        BookOrderExample bookOrderExample = new BookOrderExample();
        BookOrderExample.Criteria criteria1 = bookOrderExample.createCriteria();
        criteria1.andOrderIdEqualTo(orderId);
        List<BookOrder> list1 = bookOrderMapper.selectByExample(bookOrderExample);
        list1.get(0).setOrderStatus(4);
        int result = bookOrderMapper.updateByExampleSelective(list1.get(0),bookOrderExample);

        //得到订单条目
        ItemExample itemExample = new ItemExample();
        ItemExample.Criteria criteria2 = itemExample.createCriteria();
        criteria2.andOrderIdEqualTo(orderId);
        List<Item> list2 = itemMapper.selectByExample(itemExample);

        for (int i = 0; i < list2.size(); i++) {
            Comment comment = new Comment();
            comment.setBookId(list2.get(i).getBookId());
            comment.setComMess(message[i]);
            comment.setComStatus(0);
            comment.setComTime(new Date());
            comment.setUserId(userId);
            commentMapper.insertSelective(comment);
        }
        return result;
    }

}


