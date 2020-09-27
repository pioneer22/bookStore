package com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminCommentServer;
import com.example.bookstore.dao.BookMapper;
import com.example.bookstore.dao.BusinessMapper;
import com.example.bookstore.dao.CommentMapper;
import com.example.bookstore.dao.UserMapper;
import com.example.bookstore.pojo.*;
import com.example.bookstore.utils.dto.BookAndCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AdminCommentServerImpl implements AdminCommentServer {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Object> reqBookComment() {
        List<Object> result = new ArrayList<>();
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookIdIsNotNull();
        List<Book> list = bookMapper.selectByExample(bookExample);
        list.forEach((Book book)->{
            BookAndCommentDto bookAndCommentDto = new BookAndCommentDto();
            bookAndCommentDto.setBook(book);
            CommentExample commentExample = new CommentExample();
            CommentExample.Criteria criteria1 = commentExample.createCriteria();
            criteria1.andBookIdEqualTo(book.getBookId());
            List<Comment> comments = commentMapper.selectByExample(commentExample);
            List<BookAndCommentDto.CommentList> commentLists = new ArrayList<>();
            comments.forEach((Comment comment)->{
                User user = userMapper.selectByPrimaryKey(comment.getUserId());
                BookAndCommentDto.CommentList commentList = new BookAndCommentDto.CommentList();
                commentList.setUserName(user.getUserName());
                commentList.setUserImg(user.getUserImg());
                commentList.setComment(comment);
                commentLists.add(commentList);
            });
            bookAndCommentDto.setCommentList(commentLists);
            result.add(bookAndCommentDto);
        });
        return result;
    }

    @Override
    public int replyComment(int comId, String comReply) {
        Comment comment = commentMapper.selectByPrimaryKey(comId);
        comment.setComReply(comReply);
        comment.setReplyTime(new Date());
        int result = commentMapper.updateByPrimaryKey(comment);
        return result;
    }

    @Override
    public List<Object> searchBook(String searchKey) {
        List<Object> result = new ArrayList<>();
        BookExample bookExample = new BookExample();
        String searchValue = "%" + searchKey +"%";
        bookExample.or().andBookNameLike(searchValue);
        //判断是不是数字字符串
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if(pattern.matcher(searchKey).matches()){
            bookExample.or().andBookIdEqualTo(Integer.valueOf(searchKey));
        }
        List<Book> list = bookMapper.selectByExample(bookExample);
        list.forEach((Book book)->{
            BookAndCommentDto bookAndCommentDto = new BookAndCommentDto();
            bookAndCommentDto.setBook(book);
            CommentExample commentExample = new CommentExample();
            CommentExample.Criteria criteria1 = commentExample.createCriteria();
            criteria1.andBookIdEqualTo(book.getBookId());
            List<Comment> comments = commentMapper.selectByExample(commentExample);
            List<BookAndCommentDto.CommentList> commentLists = new ArrayList<>();
            comments.forEach((Comment comment)->{
                User user = userMapper.selectByPrimaryKey(comment.getUserId());
                BookAndCommentDto.CommentList commentList = new BookAndCommentDto.CommentList();
                commentList.setUserName(user.getUserName());
                commentList.setUserImg(user.getUserImg());
                commentList.setComment(comment);
                commentLists.add(commentList);
            });
            bookAndCommentDto.setCommentList(commentLists);
            result.add(bookAndCommentDto);
        });
        return result;
    }

    @Override
    public int changeMessage(String businessId, String businessName, String businessPhone) {
        Business business = businessMapper.selectByPrimaryKey(businessId);
        business.setBusinessName(businessName);
        business.setBusinessPhone(businessPhone);
        int result = businessMapper.updateByPrimaryKey(business);
        return result;
    }

    @Override
    public int changePassword(String businessId, String businessPass) {
        Business business = businessMapper.selectByPrimaryKey(businessId);
        business.setBusinessPass(businessPass);
        int result = businessMapper.updateByPrimaryKey(business);
        return result;
    }
}
