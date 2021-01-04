package top.faroz.dao;

import top.faroz.bean.ReaderBook;
import top.faroz.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReaderBookDao
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/3 下午9:49
 * @Version 1.0
 **/
public class ReaderBookDao {
    public List<ReaderBook> list() {
        String sql = "select * from reader_book";
        List<ReaderBook> list = new ArrayList<>();
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            // private int reader_id;
            // private int book_ISBN;
            while (rs.next()) {
                int id = rs.getInt(1);
                int readerID = rs.getInt(2);
                int bookISBN = rs.getInt(3);
                ReaderBook readerBook = new ReaderBook(id,readerID, bookISBN);
                list.add(readerBook);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void delete(int id) {

    }

    public void add(ReaderBook bean) {
        String sql = "insert into reader_book values(null,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,bean.getReader_id());
            stmt.setInt(2,bean.getBook_ISBN());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
