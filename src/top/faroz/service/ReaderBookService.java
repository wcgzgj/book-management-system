package top.faroz.service;

import top.faroz.bean.Reader;
import top.faroz.bean.ReaderBook;
import top.faroz.dao.ReaderBookDao;
import top.faroz.dao.ReaderDao;
import top.faroz.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    /**
     * 根据用户ID和ISBN删除对应数据
     * @param id
     * @param ISBN
     */
    public void deleteByReaderAndBook(int id,int ISBN) {
        String sql = "delete from reader_book where reader_id=? and book_isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.setInt(2,ISBN);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteByReader(int id) {
        String sql = "delete from reader_book where reader_id=? ";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteByBook(int ISBN) {
        String sql = "delete from reader_book where book_isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,ISBN);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
