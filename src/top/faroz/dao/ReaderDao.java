package top.faroz.dao;

import top.faroz.bean.Reader;
import top.faroz.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReaderDao
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/3 下午8:54
 * @Version 1.0
 **/
public class ReaderDao {
    public int getTotal() {
        int total=0;
        // 使用数据库中的聚合函数
        String sql = "select count(*) from reader";
        Connection conn = DBUtil.getConnection();
        ResultSet resultSet=null;
        try (Statement stmt = conn.createStatement()){
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                total=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return total;
    }

    /**
     * 向数据库中插入教师信息
     * @param bean
     */
    public void add(Reader bean) {
        String sql = "insert into reader values(?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,bean.getId());
            stmt.setString(2,bean.getName());
            stmt.setString(3,bean.getPassword());

            //插入数据
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(Reader bean) {

    }

    public void delete(int id) {
        String sql = "delete from reader where id=?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Reader get(int id) {
        Reader Reader = null;
        String sql = "select * from reader where id=?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            // 如果没有查到对应的学生，就不会发生下一步，返回的就是null
            while (rs.next()) {
                int Reader_id = rs.getInt(1);
                String Reader_name = rs.getString(2);
                String Reader_password = rs.getString(3);
                Reader=new Reader();
                Reader.setId(Reader_id);
                Reader.setName(Reader_name);
                Reader.setPassword(Reader_password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Reader;
    }

    /**
     * 获取数据库中所有行的数据
     * @return
     */
    public List<Reader> list() {
        String sql = "select * from reader";
        List<Reader> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reader Reader = new Reader();
                Reader.setId(rs.getInt(1));
                Reader.setName(rs.getString(2));
                Reader.setPassword(rs.getString(3));
                list.add(Reader);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Reader> list(int start,int end) {
        return null;
    }
}
