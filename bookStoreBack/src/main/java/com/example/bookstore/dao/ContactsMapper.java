package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.Contacts;
import com.example.bookstore.pojo.ContactsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactsMapper {
    int countByExample(ContactsExample example);

    int deleteByExample(ContactsExample example);

    int deleteByPrimaryKey(Integer contactId);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    List<Contacts> selectByExample(ContactsExample example);

    Contacts selectByPrimaryKey(Integer contactId);

    int updateByExampleSelective(@Param("record") Contacts record, @Param("example") ContactsExample example);

    int updateByExample(@Param("record") Contacts record, @Param("example") ContactsExample example);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);
}