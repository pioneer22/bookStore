package com.example.bookstore.AdminController;

import com.example.bookstore.AdminService.AdminChartsServer;
import com.example.bookstore.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8099", maxAge = 3600)
public class AdminChartsController {

    @Autowired
    private AdminChartsServer adminChartsServer;

    @ResponseBody
    @GetMapping("/charts/reqShowData")
    public ServerResponse reqShowData(){
        Map<String,ArrayList> result = adminChartsServer.reqShowData();
        return ServerResponse.createBySuccess("requestSuccess",result);
    }

    @ResponseBody
    @GetMapping("/charts/reqPieData")
    public ServerResponse reqPieData(){
        Map<String,Object> result = adminChartsServer.reqPieData();
        return ServerResponse.createBySuccess("reqPieDataSuccess",result);
    }

    @ResponseBody
    @GetMapping("/charts/reqSaleMoney")
    public ServerResponse reqSaleMoney(){
        Map<String,Object> result = adminChartsServer.reqSaleMoney();
        return ServerResponse.createBySuccess("reqSaleMoneySuccess",result);
    }
}
