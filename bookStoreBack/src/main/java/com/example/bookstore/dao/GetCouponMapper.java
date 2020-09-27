package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.GetCoupon;
import com.example.bookstore.pojo.GetCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GetCouponMapper {
    int countByExample(GetCouponExample example);

    int deleteByExample(GetCouponExample example);

    int deleteByPrimaryKey(Integer getCouponId);

    int insert(GetCoupon record);

    int insertSelective(GetCoupon record);

    List<GetCoupon> selectByExample(GetCouponExample example);

    GetCoupon selectByPrimaryKey(Integer getCouponId);

    int updateByExampleSelective(@Param("record") GetCoupon record, @Param("example") GetCouponExample example);

    int updateByExample(@Param("record") GetCoupon record, @Param("example") GetCouponExample example);

    int updateByPrimaryKeySelective(GetCoupon record);

    int updateByPrimaryKey(GetCoupon record);
}