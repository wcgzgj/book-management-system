package top.faroz.service;

import top.faroz.bean.Reader;
import top.faroz.bean.ReaderBook;
import top.faroz.dao.ReaderBookDao;
import top.faroz.dao.ReaderDao;

import java.util.List;

/**
 * @ClassName ReaderBookService
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/4 下午2:21
 * @Version 1.0
 **/
public class ReaderBookService {
    public ReaderBook getByReaderAndBook(int id, int ISBN) {
        ReaderBookDao readerBookDao = new ReaderBookDao();
        List<ReaderBook> list = readerBookDao.list();
        for (ReaderBook readerBook : list) {
            if (readerBook.getReader_id()==id && readerBook.getBook_ISBN()==ISBN)
                return readerBook;
        }
        return null;
    }
}
