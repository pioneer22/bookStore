package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.GetClassified;
import com.example.bookstore.pojo.GetClassifiedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GetClassifiedMapper {
    int countByExample(GetClassifiedExample example);

    int deleteByExample(GetClassifiedExample example);

    int deleteByPrimaryKey(Integer getClassifiedId);

    int insert(GetClassified record);

    int insertSelective(GetClassified record);

    List<GetClassified> selectByExample(GetClassifiedExample example);

    GetClassified selectByPrimaryKey(Integer getClassifiedId);

    int updateByExampleSelective(@Param("record") GetClassified record, @Param("example") GetClassifiedExample example);

    int updateByExample(@Param("record") GetClassified record, @Param("example") GetClassifiedExample example);

    int updateByPrimaryKeySelective(GetClassified record);

    int updateByPrimaryKey(GetClassified record);
}