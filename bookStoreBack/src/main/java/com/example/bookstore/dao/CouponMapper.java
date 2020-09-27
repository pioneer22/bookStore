package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Coupon;
import com.example.bookstore.pojo.CouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponMapper {
    int countByExample(CouponExample example);

    int deleteByExample(CouponExample example);

    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    List<Coupon> selectByExample(CouponExample example);

    Coupon selectByPrimaryKey(Integer couponId);

    int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}