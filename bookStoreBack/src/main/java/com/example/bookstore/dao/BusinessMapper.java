package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Business;
import com.example.bookstore.pojo.BusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessMapper {
    int countByExample(BusinessExample example);

    int deleteByExample(BusinessExample example);

    int deleteByPrimaryKey(String businessId);

    int insert(Business record);

    int insertSelective(Business record);

    List<Business> selectByExample(BusinessExample example);

    Business selectByPrimaryKey(String businessId);

    int updateByExampleSelective(@Param("record") Business record, @Param("example") BusinessExample example);

    int updateByExample(@Param("record") Business record, @Param("example") BusinessExample example);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);
}