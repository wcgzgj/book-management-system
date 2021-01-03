package top.faroz.dao;

import top.faroz.bean.Admin;
import top.faroz.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AdminDao
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/3 下午8:54
 * @Version 1.0
 **/
public class AdminDao {
    public int getTotal() {
        int total=0;
        // 使用数据库中的聚合函数
        String sql = "select count(*) from admin";
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
    public void add(Admin bean) {
        String sql = "insert into admin values(?,?,?)";
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

    public void update(Admin bean) {

    }

    public void delete(int id) {

    }

    public Admin get(int id) {
        Admin Admin = null;
        String sql = "select * from admin where id=?";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            // 如果没有查到对应的学生，就不会发生下一步，返回的就是null
            while (rs.next()) {
                int Admin_id = rs.getInt(1);
                String Admin_name = rs.getString(2);
                String Admin_password = rs.getString(3);
                Admin=new Admin();
                Admin.setId(Admin_id);
                Admin.setName(Admin_name);
                Admin.setPassword(Admin_password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Admin;
    }

    /**
     * 获取数据库中所有行的数据
     * @return
     */
    public List<Admin> list() {
        String sql = "select * from Admin";
        List<Admin> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Admin Admin = new Admin();
                Admin.setId(rs.getInt(1));
                Admin.setName(rs.getString(2));
                Admin.setPassword(rs.getString(3));
                list.add(Admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<Admin> list(int start,int end) {
        return null;
    }
}
