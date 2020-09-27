package main.java.com.example.bookstore.AdminService;

import com.example.bookstore.pojo.Chat;
import com.example.bookstore.pojo.MorningPaper;

import java.util.List;
import java.util.Map;

public interface AdminNoticeServer {
    List<MorningPaper> getMorningPaper(); //获取信息

    int sendNotice(String paperMess, String businessId); //发送信息

    int deleteNotice(int paperId); //删除当前消息

    int deleteAllNotice(String businessId); //清空早报信息

    List<Chat> reqChatMess(String businessId); // 获取聊天信息

    int deleteOneChat(int chatId); // 删除单条聊天内容

    List<Chat> searchChat(String businessId, String searchKey); // 查找聊天记录

    int deleteChooseChat(List<Integer> chatIdMess); // 删除选中

    List<Object> reqUser(); // 获取用户

    List<Object> reqChat(String businessId, String userId); // 获取聊天记录

    int saveMess(String businessId, String userId, String context); // 保存聊天记录，单发

    int saveMessAll(String businessId, String context); // 保存聊天记录,群发

    List<Object> searchUser(String searchKey); // 查找用户

    int reqNoReadCount(String businessId); // 请求个数

    Map<String,Object> reqNoReadMess(String businessId); // 请求未读消息

    int turnRead(int chatId); // 标记已读

    int turnKnow(String orderId); // 订单标记已知
}
