package dao;

import core.Db;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private final Connection connect;

    public UserDao() {
        this.connect = Db.getInstance();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "Select * From public.user";
        try {
            ResultSet rs = this.connect.createStatement().executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User findByLogin(String username, String password) {
        User obj = null;
        String quary = "Select * From public.user Where user_name = ? and user_password = ?";
        try {
            PreparedStatement pr = this.connect.prepareStatement(quary);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(rs.getString("user_role"));
        return obj;
    }
}
