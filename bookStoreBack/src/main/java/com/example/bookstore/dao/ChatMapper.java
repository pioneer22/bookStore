package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Chat;
import com.example.bookstore.pojo.ChatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatMapper {
    int countByExample(ChatExample example);

    int deleteByExample(ChatExample example);

    int deleteByPrimaryKey(Integer chatId);

    int insert(Chat record);

    int insertSelective(Chat record);

    List<Chat> selectByExample(ChatExample example);

    Chat selectByPrimaryKey(Integer chatId);

    int updateByExampleSelective(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByExample(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);
}