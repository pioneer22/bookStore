package main.java.com.example.bookstore.UserServiceImpl;

import com.example.bookstore.UserService.ShopCarServer;
import com.example.bookstore.dao.BookMapper;
import com.example.bookstore.dao.ShopCarMapper;
import com.example.bookstore.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCarServerImpl implements ShopCarServer{
    @Autowired
    private ShopCarMapper shopCarMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List getShopCar(String userId) {
        //获取购物车信息
        ShopCarExample shopCarExample = new ShopCarExample();
        ShopCarExample.Criteria criteria = shopCarExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<ShopCar> result = shopCarMapper.selectByExample(shopCarExample);

        List list = new ArrayList<>();
        for(int i=0;i<result.size();i++)
        {
            BookExample bookExample = new BookExample();
            BookExample.Criteria criteria1 = bookExample.createCriteria();
            criteria1.andBookIdEqualTo(result.get(i).getBookId());
            List<Book> result1 = bookMapper.selectByExample(bookExample);
            List temp = new ArrayList<>();
            temp.add(result.get(i).getShopNum());
            temp.add(result1);
            list.add(temp);
        }
        return list;
    }

    @Override
    public int addShopCar(String userId, int bookId) {
        //添加购物车
        ShopCarExample shopcarExample = new ShopCarExample();
        ShopCarExample.Criteria criteria = shopcarExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andBookIdEqualTo(bookId);
        List<ShopCar> result = shopCarMapper.selectByExample(shopcarExample);
        ShopCar sc = new ShopCar();
        sc.setBookId(bookId);
        sc.setUserId(userId);
        if(result.size()>0)
        {
            //若存在，数量加1
            sc.setShopNum(result.get(0).getShopNum()+1);
            shopCarMapper.updateByExample(sc,shopcarExample);
        }else{
            //不存在直接插入
            sc.setShopNum(1);
            shopCarMapper.insert(sc);
        }
        return 1;
    }

    @Override
    public int delShopCar(int[] bookArray,String userid) {
        //删除购物车选中图书
        int list = 0;
        for (int i : bookArray) {
            ShopCarExample shopcarExample = new ShopCarExample();
            ShopCarExample.Criteria criteria=shopcarExample.createCriteria();
            criteria.andUserIdEqualTo(userid);
            criteria.andBookIdEqualTo(i);
            list =  shopCarMapper.deleteByExample(shopcarExample);
        }
        return list>0?1:0;
    }

    @Override
    public int getCarNum(String userId) {
        //获取购物车图书数量
        ShopCarExample shopcarExample = new ShopCarExample();
        ShopCarExample.Criteria criteria = shopcarExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<ShopCar> result = shopCarMapper.selectByExample(shopcarExample);
        int bookNum = 0;
        for (int i = 0; i < result.size() ; i++) {
            bookNum += result.get(i).getShopNum();
        }
        return bookNum;
    }
}
