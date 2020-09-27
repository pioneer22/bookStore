package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Collect;
import com.example.bookstore.pojo.CollectExample;
import com.example.bookstore.pojo.CollectKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectMapper {
    int countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(CollectKey key);

    int insert(Collect record);

    int insertSelective(Collect record);

    List<Collect> selectByExample(CollectExample example);

    Collect selectByPrimaryKey(CollectKey key);

    int updateByExampleSelective(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByExample(@Param("record") Collect record, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}