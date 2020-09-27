package main.java.com.example.bookstore.dao;

import com.example.bookstore.pojo.ShopCar;
import com.example.bookstore.pojo.ShopCarExample;
import com.example.bookstore.pojo.ShopCarKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCarMapper {
    int countByExample(ShopCarExample example);

    int deleteByExample(ShopCarExample example);

    int deleteByPrimaryKey(ShopCarKey key);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    List<ShopCar> selectByExample(ShopCarExample example);

    ShopCar selectByPrimaryKey(ShopCarKey key);

    int updateByExampleSelective(@Param("record") ShopCar record, @Param("example") ShopCarExample example);

    int updateByExample(@Param("record") ShopCar record, @Param("example") ShopCarExample example);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
}