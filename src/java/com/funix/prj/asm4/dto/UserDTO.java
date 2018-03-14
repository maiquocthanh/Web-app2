/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funix.prj.asm4.dto;

import com.funix.prj.asm4.db.ConnectUtils;
import com.funix.prj.asm4.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hung
 */
public class UserDTO {

    private static final String TABLE_USERS = "users";

    private static final String COLUMN_USERNAME = "Username";

    private static final String COLUMN_PASSWORD = "Passwords";

    private static final String COLUMN_IS_LOGINED = "IsLogined";

    private Connection connection;

    public UserDTO() {
        if (connection == null) {

            try {
                connection = (Connection) ConnectUtils.getConnection();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean getUserByName(String username) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME + " = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    public void addUser(UserModel userModel) throws SQLException {
        String sql = "INSERT INTO " + TABLE_USERS + " values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, userModel.getUserName());
        ps.setString(2, userModel.getPassWord());
        if (userModel.isIsLogined()) {
            ps.setInt(3, 1);
        } else {
            ps.setInt(3, 0);
        }
        ps.executeUpdate();
        ps.close();
    }

    public boolean getFirstLogined(String username) throws SQLException {

        String sql = "SELECT " + COLUMN_IS_LOGINED + " FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME + " = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int isLogin = rs.getInt(COLUMN_IS_LOGINED);
            if (isLogin == 0) {
                return true;

            }
        }
        return false;
    }

    public void updateIsLogined(String username, int isLogined) throws SQLException {
        String sql = "UPDATE " + TABLE_USERS + " SET "
                + COLUMN_IS_LOGINED + " = ? WHERE "
                + COLUMN_USERNAME + " = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, isLogined);
        ps.setString(2, username);
        ps.executeUpdate();
        ps.close();

    }
    
    public List<UserModel> getUser(String username, String password) throws SQLException {
        
        String sql = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        List<UserModel> ls = new ArrayList<>();
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            UserModel model = new UserModel();
            model.setUserName(rs.getString(COLUMN_USERNAME));
            model.setPassWord(rs.getString(COLUMN_PASSWORD));
           ls.add(model);
        
        }
        
        return ls;
    
    }
    
     public List<UserModel> getAllUser() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_USERS;
        PreparedStatement ps = connection.prepareStatement(sql);
        List<UserModel> ls = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            UserModel model = new UserModel();
            model.setUserName(rs.getString(COLUMN_USERNAME));
            ls.add(model);
        }

        return ls;
    }


}
