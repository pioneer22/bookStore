package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Comment;
import com.example.bookstore.pojo.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer comId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer comId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}