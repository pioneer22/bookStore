package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.MorningPaper;
import com.example.bookstore.pojo.MorningPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MorningPaperMapper {
    int countByExample(MorningPaperExample example);

    int deleteByExample(MorningPaperExample example);

    int deleteByPrimaryKey(Integer paperId);

    int insert(MorningPaper record);

    int insertSelective(MorningPaper record);

    List<MorningPaper> selectByExample(MorningPaperExample example);

    MorningPaper selectByPrimaryKey(Integer paperId);

    int updateByExampleSelective(@Param("record") MorningPaper record, @Param("example") MorningPaperExample example);

    int updateByExample(@Param("record") MorningPaper record, @Param("example") MorningPaperExample example);

    int updateByPrimaryKeySelective(MorningPaper record);

    int updateByPrimaryKey(MorningPaper record);
}