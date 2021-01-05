package top.faroz.service;

import top.faroz.bean.Book;
import top.faroz.bean.ReaderBook;
import top.faroz.dao.ReaderBookDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/6 上午12:43
 * @Version 1.0
 **/
public class BookService {
    /**
     * 获取读者借阅的所有图书
     * @param readerID
     * @return
     */
    public List<Book> getBorrowBook(int readerID) {
        List<Book> books = new ArrayList<>();
        ReaderBookDao readerBookDao = new ReaderBookDao();
        List<ReaderBook> list = readerBookDao.list();
        list.stream()
                .filter(user -> user.getReader_id()==readerID)
                .collect(Collectors.toList());
        return books;
    }
}
