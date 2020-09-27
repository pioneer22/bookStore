package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.TopicBook;
import com.example.bookstore.pojo.TopicBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicBookMapper {
    int countByExample(TopicBookExample example);

    int deleteByExample(TopicBookExample example);

    int deleteByPrimaryKey(Integer topicItemId);

    int insert(TopicBook record);

    int insertSelective(TopicBook record);

    List<TopicBook> selectByExample(TopicBookExample example);

    TopicBook selectByPrimaryKey(Integer topicItemId);

    int updateByExampleSelective(@Param("record") TopicBook record, @Param("example") TopicBookExample example);

    int updateByExample(@Param("record") TopicBook record, @Param("example") TopicBookExample example);

    int updateByPrimaryKeySelective(TopicBook record);

    int updateByPrimaryKey(TopicBook record);
}