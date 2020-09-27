package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Classified;
import com.example.bookstore.pojo.ClassifiedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassifiedMapper {
    int countByExample(ClassifiedExample example);

    int deleteByExample(ClassifiedExample example);

    int deleteByPrimaryKey(Integer classifiedId);

    int insert(Classified record);

    int insertSelective(Classified record);

    List<Classified> selectByExample(ClassifiedExample example);

    Classified selectByPrimaryKey(Integer classifiedId);

    int updateByExampleSelective(@Param("record") Classified record, @Param("example") ClassifiedExample example);

    int updateByExample(@Param("record") Classified record, @Param("example") ClassifiedExample example);

    int updateByPrimaryKeySelective(Classified record);

    int updateByPrimaryKey(Classified record);
}