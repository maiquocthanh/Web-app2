/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funix.prj.asm4.dto;

import com.funix.prj.asm4.db.ConnectUtils;
import com.funix.prj.asm4.model.ContentModel;
import com.funix.prj.asm4.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hung
 */
public class ContentDTO {

    private static final String TABLE_CONTENT = "postcontent";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_DESCRIPTION = "Description";
    private static final String COLUMN_CONTENTS = "Contents";
    private static final String COLUMN_IS_PUBLISHED = "IsPublishes";
    private static final String COLUMN_CREATED = "Created";
    private static final String COLUMN_USER_ID = "Username";

    private Connection connection;

    public ContentDTO() {
        if (connection == null) {
            try {
                connection = (Connection) ConnectUtils.getConnection();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ContentDTO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void adContent(ContentModel contentModel) throws SQLException {

        String sql = "INSERT INTO " + TABLE_CONTENT + " values(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, contentModel.getId());
        ps.setString(2, contentModel.getTitle());
        ps.setString(3, contentModel.getDescription());
        ps.setString(4, contentModel.getContents());
        ps.setInt(5, contentModel.getIsPublish());
        ps.setString(6, contentModel.getCreated());
        ps.setString(7, contentModel.getUserID());
        ps.executeUpdate();
        ps.close();

    }

    public ContentModel getContentsByID(int id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_CONTENT + " WHERE "
                + COLUMN_ID + " = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ContentModel model = null;
        while (rs.next()) {
            model = new ContentModel();
            model.setId(rs.getInt(COLUMN_ID));
            model.setTitle(rs.getString(COLUMN_TITLE));
            model.setDescription(rs.getString(COLUMN_DESCRIPTION));
            model.setContents(rs.getString(COLUMN_CONTENTS));
            model.setIsPublish(rs.getInt(COLUMN_IS_PUBLISHED));
            model.setCreated(rs.getString(COLUMN_CREATED));
            model.setUserID(rs.getString(COLUMN_USER_ID));
            break;

        }
        return model;
    }

    public void updateContent(ContentModel contentModel) throws SQLException {
        String sql = "UPDATE " + TABLE_CONTENT + " SET "
                + COLUMN_TITLE + " = ?, "
                + COLUMN_DESCRIPTION + " = ?, "
                + COLUMN_CONTENTS + " = ?, "
                + COLUMN_IS_PUBLISHED + " = ?, "
                + COLUMN_CREATED + " = ?, "
                + COLUMN_USER_ID + " = ? WHERE "
                + COLUMN_ID + " = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, contentModel.getTitle());
        ps.setString(2, contentModel.getDescription());
        ps.setString(3, contentModel.getContents());
        ps.setInt(4, contentModel.getIsPublish());
        ps.setString(5, contentModel.getCreated());
        ps.setString(6, contentModel.getUserID());
        ps.setInt(7, contentModel.getId());
        ps.executeUpdate();
        ps.close();

    }

    public static void main(String[] args) throws SQLException {
        ContentDTO dto = new ContentDTO();

        UserDTO udto = new UserDTO();
        UserModel user = new UserModel("Thanh3", "12345", false);
        udto.addUser(user);
        System.out.println("adding user");
        ContentModel model = new ContentModel(4, "xx", "xx", "xx", 1, "xx", "Thanh3");
        dto.adContent(model);
        System.out.println("adding content");
    }

}
