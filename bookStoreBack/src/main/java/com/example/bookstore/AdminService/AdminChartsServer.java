package main.java.com.example.bookstore.AdminService;

import java.util.ArrayList;
import java.util.Map;

public interface AdminChartsServer {
    Map<String,ArrayList> reqShowData(); // 图表显示数据

    Map<String,Object> reqPieData();

    Map<String,Object> reqSaleMoney();
}
