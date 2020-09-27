package main.java.com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminNoticeServer;
import com.example.bookstore.dao.*;
import com.example.bookstore.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminNoticeServerImpl implements AdminNoticeServer{

    @Autowired
    private MorningPaperMapper morningPaperMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private AllOrderMapper allOrderMapper;

    @Override
    public List<MorningPaper> getMorningPaper() {
        MorningPaperExample morningPaperExample = new MorningPaperExample();
        MorningPaperExample.Criteria criteria = morningPaperExample.createCriteria();
        criteria.andPaperMessIsNotNull();
        criteria.andPaperIdIsNotNull();
        List<MorningPaper> list = morningPaperMapper.selectByExample(morningPaperExample);
        return list;
    }

    @Override
    public int sendNotice(String paperMess, String businessId) {
        Date date = new Date();
        MorningPaper morningPaper = new MorningPaper();
        morningPaper.setPaperMess(paperMess);
        morningPaper.setPaperTime(date);
        morningPaper.setBusinessId(businessId);
        int result = morningPaperMapper.insertSelective(morningPaper);

        // 信息发送给每个用户
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdIsNotNull();
        List<User> list = userMapper.selectByExample(userExample);
        list.forEach((User user)->{
            Message message = new Message();
            message.setSendMess(paperMess);
            message.setPaperState(0);
            message.setSendTime(date);
            message.setUserId(user.getUserId());
            messageMapper.insertSelective(message);
        });
        return result;
    }

    @Override
    public int deleteNotice(int paperId) {
        int result = morningPaperMapper.deleteByPrimaryKey(paperId);
        return result;
    }

    @Override
    public int deleteAllNotice(String businessId) {
        MorningPaperExample morningPaperExample = new MorningPaperExample();
        MorningPaperExample.Criteria criteria = morningPaperExample.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        List<MorningPaper> list = morningPaperMapper.selectByExample(morningPaperExample);
        list.forEach((MorningPaper morningPaper)->{
            morningPaperMapper.deleteByPrimaryKey(morningPaper.getPaperId());
        });
        if(list.size()>0)
            return 1;
        return 0;
    }

    @Override
    public List<Chat> reqChatMess(String businessId) { // 获取聊天信息
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        chatExample.setOrderByClause("send_time desc");
        criteria.andBusinessIdEqualTo(businessId);
        List<Chat> result = chatMapper.selectByExample(chatExample);
        return result;
    }

    @Override
    public int deleteOneChat(int chatId) {
        int result = chatMapper.deleteByPrimaryKey(chatId);
        return result;
    }

    @Override
    public List<Chat> searchChat(String businessId, String searchKey) {
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        criteria.andUserIdLike(searchKey);
        List<Chat> result = chatMapper.selectByExample(chatExample);
        return result;
    }

    @Override
    public int deleteChooseChat(List<Integer> chatIdMess) {
        int result = 0;
        for (Integer idMess : chatIdMess) {
            result = chatMapper.deleteByPrimaryKey(idMess);
        }
        return result;
    }

    @Override
    public List<Object> reqUser() {
        UserExample userExample = new UserExample();
        List<User> list = userMapper.selectByExample(userExample);
        List<Object> result = new ArrayList<>();
        list.forEach((User user)->{
            Map<String,String> map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("userName",user.getUserName());
            map.put("userImg",user.getUserImg());
            result.add(map);
        });
        return result;
    }

    @Override
    public List<Object> reqChat(String businessId, String userId) {
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        chatExample.setOrderByClause("send_time asc");
        criteria.andBusinessIdEqualTo(businessId);
        criteria.andUserIdEqualTo(userId);
        List<Chat> chats = chatMapper.selectByExample(chatExample);
        List<Object> result = new ArrayList<>();
        chats.forEach((Chat chat)->{
            Map<String,Object> map = new HashMap<>();
            map.put("sendTime",chat.getSendTime());
            map.put("context",chat.getContext());
            map.put("statu",chat.getStatu());
            result.add(map);
        });
        return result;
    }

    @Override
    public int saveMess(String businessId, String userId, String context) {
        Chat chat = new Chat();
        Date date = new Date();
        chat.setBusinessId(businessId);
        chat.setContext(context);
        chat.setSendTime(date);
        chat.setUserId(userId);
        chat.setStatu(2);
        int result = chatMapper.insertSelective(chat);
        return result;
    }

    @Override
    public int saveMessAll(String businessId, String context) {
        UserExample userExample = new UserExample();
        List<User> list = userMapper.selectByExample(userExample);
        Date date = new Date();
        list.forEach((User user)->{
            Chat chat = new Chat();
            chat.setBusinessId(businessId);
            chat.setContext(context);
            chat.setSendTime(date);
            chat.setUserId(user.getUserId());
            chat.setStatu(2);
            chatMapper.insertSelective(chat);
        });
        if(list.size()>0)
            return 1;
        return 0;
    }

    @Override
    public List<Object> searchUser(String searchKey) {
        UserExample userExample = new UserExample();
        userExample.or().andUserNameLike(searchKey);
        userExample.or().andUserIdLike(searchKey);
        List<User> list = userMapper.selectByExample(userExample);
        List<Object> result = new ArrayList<>();
        list.forEach((User user)->{
            Map<String,String> map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("userName",user.getUserName());
            map.put("userImg",user.getUserImg());
            result.add(map);
        });
        return result;
    }

    @Override
    public int reqNoReadCount(String businessId) {
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        criteria.andStatuEqualTo(0);
        int unReadChatCount = chatMapper.countByExample(chatExample);
        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria1 = allOrderExample.createCriteria();
        criteria1.andMessStatusIsNull();
        int unReadOrderCount = allOrderMapper.countByExample(allOrderExample);
        return unReadChatCount+unReadOrderCount;
    }

    @Override
    public Map<String, Object> reqNoReadMess(String businessId) {
        ChatExample chatExample = new ChatExample();
        ChatExample.Criteria criteria = chatExample.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        criteria.andStatuEqualTo(0);
        List<Chat> unReadChat = chatMapper.selectByExample(chatExample);
        AllOrderExample allOrderExample = new AllOrderExample();
        AllOrderExample.Criteria criteria1 = allOrderExample.createCriteria();
        criteria1.andMessStatusIsNull();
        List<AllOrder> unReadOrder = allOrderMapper.selectByExample(allOrderExample);
        Map<String,Object> result = new HashMap<>();
        result.put("unReadChat",unReadChat);
        result.put("unReadOrder",unReadOrder);
        return result;
    }

    @Override
    public int turnRead(int chatId) {
        Chat chat = chatMapper.selectByPrimaryKey(chatId);
        chat.setStatu(1);
        int result = chatMapper.updateByPrimaryKey(chat);
        return result;
    }

    @Override
    public int turnKnow(String orderId) {
        AllOrder allOrder = allOrderMapper.selectByPrimaryKey(orderId);
        allOrder.setMessStatus(1);
        int result = allOrderMapper.updateByPrimaryKey(allOrder);
        return result;
    }
}
