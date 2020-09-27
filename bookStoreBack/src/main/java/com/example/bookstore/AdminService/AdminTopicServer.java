package main.java.com.example.bookstore.AdminService;

import java.util.List;

public interface AdminTopicServer {
    List<Object> reqTopic(); //请求图书专题信息

    int deleteTopic(int topicId); //删除专题

    int deleteTopicBook(int topicId, int bookId); //删除专题选中图书

    List<Object> searchTopic(String searchKey); //查找专题

    List<Object> reqBook(); //请求图书，给编辑使用

    int insertTopicBook(int topicId, List<String> books); //添加图书

    int insertTopic(String topicName, String topicImg, List<Integer> books); //发布专题
}
