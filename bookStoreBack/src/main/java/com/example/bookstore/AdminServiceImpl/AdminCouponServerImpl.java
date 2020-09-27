package main.java.com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminCouponServer;
import com.example.bookstore.dao.CouponMapper;
import com.example.bookstore.pojo.Coupon;
import com.example.bookstore.pojo.CouponExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminCouponServerImpl implements AdminCouponServer{
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public Map<String,Object> reqCoupon(String businessId) {
        CouponExample couponExample = new CouponExample();
        CouponExample.Criteria criteria = couponExample.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        List<Coupon> list = couponMapper.selectByExample(couponExample);
        Date date = new Date();
        List<Coupon> valid = new ArrayList<>();
        List<Coupon> disabled = new ArrayList<>();
        list.forEach((Coupon coupon)->{
            // 有效优惠卷
            if(date.before(coupon.getCouponVaild())) {
                valid.add(coupon);
            }else{
                disabled.add(coupon);
            }
        });
        Map<String,Object> result = new HashMap<>();
        result.put("coupon",list);
        result.put("valid",valid);
        result.put("disabled",disabled);
        return result;
    }

    @Override
    public int insertCoupon(List<Object> couponMess) {
        Coupon coupon = new Coupon();
        coupon.setBusinessId((String) couponMess.get(0));
        coupon.setCouponUse(Integer.valueOf((String) couponMess.get(1)));
        coupon.setCouponMoney(Integer.valueOf((String) couponMess.get(2)));
        coupon.setCouponNum(Integer.valueOf((String) couponMess.get(4)));
        String imgUrl = couponMess.get(5).equals("")?"/api/Img/category.png": (String) couponMess.get(5);
        coupon.setCouponImg(imgUrl);
        Long lt = new Long(String.valueOf(couponMess.get(3)));
        coupon.setCouponGetnum(0);
        coupon.setCouponTime(new Date());
        coupon.setCouponVaild(new Date(lt));
        int result = couponMapper.insertSelective(coupon);
        return result>0?result:0;
    }

    @Override
    public int deleteCoupon(int couponId) {
        int result = couponMapper.deleteByPrimaryKey(couponId);
        return result;
    }
}
