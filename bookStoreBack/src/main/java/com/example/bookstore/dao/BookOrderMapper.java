package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.BookOrder;
import com.example.bookstore.pojo.BookOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookOrderMapper {
    int countByExample(BookOrderExample example);

    int deleteByExample(BookOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(BookOrder record);

    int insertSelective(BookOrder record);

    List<BookOrder> selectByExample(BookOrderExample example);

    BookOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") BookOrder record, @Param("example") BookOrderExample example);

    int updateByExample(@Param("record") BookOrder record, @Param("example") BookOrderExample example);

    int updateByPrimaryKeySelective(BookOrder record);

    int updateByPrimaryKey(BookOrder record);
}