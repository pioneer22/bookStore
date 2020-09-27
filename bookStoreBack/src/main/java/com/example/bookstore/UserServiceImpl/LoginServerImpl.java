package main.java.com.example.bookstore.UserServiceImpl;

import com.example.bookstore.UserService.LoginServer;
import com.example.bookstore.dao.BusinessMapper;
import com.example.bookstore.pojo.Business;
import com.example.bookstore.pojo.BusinessExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoginServerImpl implements LoginServer{

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> bussinessLogin(String businessId, String businessPass) {

        BusinessExample businessExample = new BusinessExample();
        BusinessExample.Criteria criteria = businessExample.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        List<Business> list = businessMapper.selectByExample(businessExample);
        if(list.size() == 1 && list.get(0).getBusinessPass().equals(businessPass))
            return list;
        else
            return new ArrayList<>();
    }
}
