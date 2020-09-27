package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Message;
import com.example.bookstore.pojo.MessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    int countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Integer paperId);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Integer paperId);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}