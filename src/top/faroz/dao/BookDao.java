package top.faroz.dao;

import top.faroz.bean.Book;
import top.faroz.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookDao
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/3 下午8:54
 * @Version 1.0
 **/
public class BookDao {
    public List<Book> list() {
        List<Book> list = new ArrayList<>();
        String sql = "select * from book";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // private String name;
                // private float price;
                // private int num;
                // private String writer;
                // private int ISBN;
                int ISBN = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                int num = rs.getInt(4);
                String writer = rs.getString(5);
                Book book = new Book(name, price, num, writer, ISBN);
                list.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public Book get(int ISBN) {
        String sql = "select * from book where isbn=?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,ISBN);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                int num = rs.getInt(4);
                String writer = rs.getString(5);
                Book book = new Book(name, price, num, writer, ISBN);
                return book;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void add(Book bean) {
        String sql = "insert into book values(?,?,?,?,?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            // private String name;
            // private float price;
            // private int num;
            // private String writer;
            // private int ISBN;
            stmt.setInt(1,bean.getISBN());
            stmt.setString(2,bean.getName());
            stmt.setFloat(3,bean.getPrice());
            stmt.setInt(4,bean.getNum());
            stmt.setString(5,bean.getWriter());

            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int ISBN) {
        String sql = "delete from book where isbn=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,ISBN);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Book bean) {
        // private String name;
        // private float price;
        // private int num;
        // private String writer;
        // private int ISBN;
        String sql = "update book set name=?,price=?,num=?,writer=? where isbn=?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,bean.getName());
            stmt.setFloat(2,bean.getPrice());
            stmt.setInt(3,bean.getNum());
            stmt.setString(4,bean.getWriter());
            stmt.setInt(5,bean.getISBN());

            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
