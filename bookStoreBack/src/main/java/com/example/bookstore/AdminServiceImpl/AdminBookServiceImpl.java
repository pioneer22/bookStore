package main.java.com.example.bookstore.AdminServiceImpl;

import com.example.bookstore.AdminService.AdminBookServer;
import com.example.bookstore.dao.BookMapper;
import com.example.bookstore.dao.ClassifiedMapper;
import com.example.bookstore.dao.GetClassifiedMapper;
import com.example.bookstore.pojo.*;
import com.example.bookstore.utils.dto.BookAndClassifyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminBookServiceImpl implements AdminBookServer {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ClassifiedMapper classifiedMapper;

    @Autowired
    private GetClassifiedMapper getClassifiedMapper;

    @Override
    public List<Book> getBook() {
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookIdIsNotNull();
        List<Book> result = bookMapper.selectByExample(bookExample);
        return result;
    }

    @Override
    public List<Book> searchKey(String searchKey) {
        String searchValue = "%"+searchKey+"%";
        BookExample bookExample = new BookExample();
        bookExample.or().andBookNameLike(searchValue);
        bookExample.or().andBookPressLike(searchValue);
        bookExample.or().andBookAuthorLike(searchValue);
        List<Book> list = bookMapper.selectByExample(bookExample);
        return list;
    }

    @Override
    public int editBookMess(List<Object> bookMess) {//修改图书信息
        Long lt = new Long((String)bookMess.get(7));
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookIdEqualTo((Integer) bookMess.get(0));
        List<Book> list = bookMapper.selectByExample(bookExample);
        list.get(0).setBookName((String)bookMess.get(1));
        list.get(0).setBookAuthor((String)bookMess.get(2));
        list.get(0).setBookPress((String)bookMess.get(3));
        list.get(0).setBookPrice((Double)bookMess.get(4));
        list.get(0).setBookNum((Integer) bookMess.get(5));
        list.get(0).setBookIntro((String)bookMess.get(6));
        list.get(0).setBookTime(new Date(lt));
        list.get(0).setBookImg((String)bookMess.get(8));
        int result = bookMapper.updateByExampleSelective(list.get(0),bookExample);
        return result;
    }

    @Override
    public int deleteBook(int bookId) {
        int result = bookMapper.deleteByPrimaryKey(bookId);
        return result;
    }

    @Override
    public int deleteAllBook() {
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookIdIsNotNull();
        int result = bookMapper.deleteByExample(bookExample);
        return result;
    }

    @Override
    public int insertBook(List<Object> bookMess) {
        Long lt = new Long((String)bookMess.get(6));
        Book book = new Book();
        book.setBookName((String)bookMess.get(0));
        book.setBookAuthor((String)bookMess.get(1));
        book.setBookPress((String)bookMess.get(2));
        book.setBookPrice(Double.valueOf((String)bookMess.get(3)));
        book.setBookNum(Integer.valueOf((String)bookMess.get(4)));
        book.setBookIntro((String)bookMess.get(5));
        book.setBookTime(new Date(lt));
        book.setBookImg((String)bookMess.get(7));
        book.setBookSale(0);
        int result = bookMapper.insertSelective(book);
        return result;
    }

    @Override
    public List<Classified> getClassify() {
        ClassifiedExample classifiedExample = new ClassifiedExample();
        ClassifiedExample.Criteria criteria = classifiedExample.createCriteria();
        criteria.andClassifiedNameIsNotNull();
        List<Classified> list = classifiedMapper.selectByExample(classifiedExample);
        return list;
    }

    @Override
    public int insertClassified(String classifiedName) { //插入图书分类
        Classified classified = new Classified();
        classified.setClassifiedName(classifiedName);
        int result = classifiedMapper.insertSelective(classified);
        return result;
    }

    @Override
    public int deleteClassified(List<Integer> idArray) { // 删除选中分类
        int result = 0;
        for (Integer integer : idArray) {
            result = classifiedMapper.deleteByPrimaryKey(integer);
        }
        return result;
    }

    @Override
    public int insertBookClassify(int bookId, List<Integer> list) { // 为当前图书添加选中分类
        int result = 0;
        for (Integer integer : list) {
            GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria = getClassifiedExample.createCriteria();
            criteria.andClassifiedIdEqualTo(integer);
            criteria.andBookIdEqualTo(bookId);
            if(getClassifiedMapper.selectByExample(getClassifiedExample).size() == 0)
            {
                GetClassified getClassified = new GetClassified();
                getClassified.setBookId(bookId);
                getClassified.setClassifiedId(integer);
                result = getClassifiedMapper.insertSelective(getClassified);
            }
        }
        return result;
    }

    @Override
    public List<BookAndClassifyDto> getBookAndClassify() {
        BookExample bookExample = new BookExample();
        BookExample.Criteria criteria = bookExample.createCriteria();
        criteria.andBookIdIsNotNull();
        List<Book> list = bookMapper.selectByExample(bookExample);
        List<BookAndClassifyDto> restlt = new ArrayList<>();
        list.forEach((Book book)->{
            BookAndClassifyDto bookAndClassifyDto = new BookAndClassifyDto();
            int bookId = book.getBookId();
            bookAndClassifyDto.setBook(book);
            GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria1 = getClassifiedExample.createCriteria();
            criteria1.andBookIdEqualTo(bookId);
            List<GetClassified> getClassifieds = getClassifiedMapper.selectByExample(getClassifiedExample);
            List<Classified> classifieds = new ArrayList<>();
            getClassifieds.forEach((GetClassified item)->{
                classifieds.add(classifiedMapper.selectByPrimaryKey(item.getClassifiedId()));
            });
            bookAndClassifyDto.setClassify(classifieds);
            restlt.add(bookAndClassifyDto);
        });
        return restlt;
    }

    @Override
    public List<BookAndClassifyDto> searchBookAndClassify(String searchKey) {
        String searchValue = "%"+searchKey+"%";
        BookExample bookExample = new BookExample();
        bookExample.or().andBookNameLike(searchValue);
        bookExample.or().andBookPressLike(searchValue);
        bookExample.or().andBookAuthorLike(searchValue);
        List<Book> list = bookMapper.selectByExample(bookExample);
        List<BookAndClassifyDto> restlt = new ArrayList<>();
        list.forEach((Book book)->{
            BookAndClassifyDto bookAndClassifyDto = new BookAndClassifyDto();
            int bookId = book.getBookId();
            bookAndClassifyDto.setBook(book);
            GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria1 = getClassifiedExample.createCriteria();
            criteria1.andBookIdEqualTo(bookId);
            List<GetClassified> getClassifieds = getClassifiedMapper.selectByExample(getClassifiedExample);
            List<Classified> classifieds = new ArrayList<>();
            getClassifieds.forEach((GetClassified item)->{
                classifieds.add(classifiedMapper.selectByPrimaryKey(item.getClassifiedId()));
            });
            bookAndClassifyDto.setClassify(classifieds);
            restlt.add(bookAndClassifyDto);
        });
        return restlt;
    }

    @Override
    public int deleteChooseBookClassify(int bookId, List<Integer> classifyArray) {
        int result = 0;
        for (Integer integer : classifyArray) {
            GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria = getClassifiedExample.createCriteria();
            criteria.andBookIdEqualTo(bookId);
            criteria.andClassifiedIdEqualTo(integer);
            result = getClassifiedMapper.deleteByExample(getClassifiedExample);
        }
        return result;
    }

    @Override
    public int insertMultiBookClassify(List<Integer> bookArray, List<Integer> classifyArray) {
        int result = 0;
        for (Integer integer : bookArray) {
            for (Integer integer1 : classifyArray) {
                GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
                GetClassifiedExample.Criteria criteria = getClassifiedExample.createCriteria();
                criteria.andBookIdEqualTo(integer);
                criteria.andClassifiedIdEqualTo(integer1);
                List<GetClassified> list = getClassifiedMapper.selectByExample(getClassifiedExample);
                if(list.size() == 1)
                    continue;
                else{
                    GetClassified getClassified = new GetClassified();
                    getClassified.setBookId(integer);
                    getClassified.setClassifiedId(integer1);
                    result = getClassifiedMapper.insertSelective(getClassified);
                }
            }
        }
        return result;
    }

    @Override
    public List<BookAndClassifyDto> searchClassifyBook(int searchKey) {

        GetClassifiedExample getClassifiedExample = new GetClassifiedExample();
        GetClassifiedExample.Criteria criteria = getClassifiedExample.createCriteria();
        criteria.andClassifiedIdEqualTo(searchKey);
        List<GetClassified> list = getClassifiedMapper.selectByExample(getClassifiedExample);
        List<BookAndClassifyDto> restlt = new ArrayList<>();

        list.forEach((GetClassified item)->{
            BookAndClassifyDto bookAndClassifyDto = new BookAndClassifyDto();
            Book book = bookMapper.selectByPrimaryKey(item.getBookId());
            bookAndClassifyDto.setBook(book);
            int bookId = book.getBookId();
            GetClassifiedExample getClassifiedExample1 = new GetClassifiedExample();
            GetClassifiedExample.Criteria criteria1 = getClassifiedExample1.createCriteria();
            criteria1.andBookIdEqualTo(bookId);
            List<GetClassified> getClassifieds = getClassifiedMapper.selectByExample(getClassifiedExample1);
            List<Classified> classifieds = new ArrayList<>();
            getClassifieds.forEach((GetClassified item1)->{
                classifieds.add(classifiedMapper.selectByPrimaryKey(item1.getClassifiedId()));
            });
            bookAndClassifyDto.setClassify(classifieds);
            restlt.add(bookAndClassifyDto);
        });
        return restlt;
    }
}
