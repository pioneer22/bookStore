package main.java.com.example.bookstore.AdminService;

import com.example.bookstore.pojo.Book;
import com.example.bookstore.pojo.Classified;
import com.example.bookstore.utils.dto.BookAndClassifyDto;

import java.util.List;

public interface AdminBookServer {

    List<Book> getBook();//获取图书

    List<Book> searchKey(String searchKey);//查找图书

    int editBookMess(List<Object> bookMess);//修改图书信息

    int deleteBook(int bookId);//删除图书信息

    int deleteAllBook();//清空图书

    int insertBook(List<Object> bookMess); //图书上架

    List<Classified> getClassify();//获取图书分类

    int insertClassified(String classifiedName);//添加分类

    int deleteClassified(List<Integer> idArray);//删除分类

    int insertBookClassify(int bookId, List<Integer> list);//添加图书分类

    List<BookAndClassifyDto> getBookAndClassify();// 获取图书信息及对应分类

    List<BookAndClassifyDto> searchBookAndClassify(String searchKey);// 查找图书信息及对应分类

    int deleteChooseBookClassify(int bookId, List<Integer> classifyArray);// 删除当前图书分类

    int insertMultiBookClassify(List<Integer> bookArray, List<Integer> classifyArray);// 给选中的图书添加选中的分类

    List<BookAndClassifyDto> searchClassifyBook(int searchKey); // 按分类查找图书
}
