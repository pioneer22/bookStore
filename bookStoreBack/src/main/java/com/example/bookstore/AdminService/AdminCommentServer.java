package com.example.bookstore.AdminService;

import java.util.List;

public interface AdminCommentServer {
    List<Object> reqBookComment(); // 请求图书评论

    int replyComment(int comId, String comReply); //回复评论

    List<Object> searchBook(String searchKey); //查找图书

    int changeMessage(String businessId, String businessName, String businessPhone); //修改个人信息

    int changePassword(String businessId, String businessPass); // 修改密码
}
