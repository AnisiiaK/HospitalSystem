package com.dao;

import com.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public boolean register(User u) {
        boolean f = false;
        try {
            String sql = "insert into user_dtls(full_name, email, password) values(?, ?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getFullName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());

            int i =ps.executeUpdate();
            if(i ==1){
                f = true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return f;
    }

    public User login(String em, String ps){

        User u = null;

        try {
            String sql = "select * from user_dtls where email = ? and password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, em);
            preparedStatement.setString(2, ps);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                u = new User();
                u.setId(resultSet.getInt(1));
                u.setFullName(resultSet.getString(2));
                u.setEmail(resultSet.getString(3));
                u.setPassword(resultSet.getString(4));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return u;
    }

    public boolean checkOldPassword(int userId, String oldPassword){
        boolean f = false;

        try {
            String sql = "select * from user_dtls where id=? and password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, oldPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                f = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return f;
    }

    public boolean changeOldPassword(int userId, String newPassword){
        boolean f = false;

        try {
            String sql = "update user_dtls set password =? where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);

            int i = preparedStatement.executeUpdate();
            if(i == 1){
                f = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return f;
    }

}
