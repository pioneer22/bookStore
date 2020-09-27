package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.AllOrder;
import com.example.bookstore.pojo.AllOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AllOrderMapper {
    int countByExample(AllOrderExample example);

    int deleteByExample(AllOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(AllOrder record);

    int insertSelective(AllOrder record);

    List<AllOrder> selectByExample(AllOrderExample example);

    AllOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") AllOrder record, @Param("example") AllOrderExample example);

    int updateByExample(@Param("record") AllOrder record, @Param("example") AllOrderExample example);

    int updateByPrimaryKeySelective(AllOrder record);

    int updateByPrimaryKey(AllOrder record);
}