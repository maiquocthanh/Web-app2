/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funix.prj.asm4.servlets;

import com.funix.prj.asm4.dto.ContentDTO;
import com.funix.prj.asm4.dto.UserDTO;
import com.funix.prj.asm4.model.ContentModel;
import com.funix.prj.asm4.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.DataFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hung
 */
public class AddContentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            ContentDTO dto = new ContentDTO();
            ContentModel contentModel = null;
            String title = request.getParameter("title");
            //String description = request.getParameter("description");
            String contents = request.getParameter("content");
            String publisher = request.getParameter("status");
            String author = request.getParameter("author");
            String edit_content = request.getParameter("edit_content");

            if (edit_content != null) {
                int id = Integer.parseInt(edit_content);
                contentModel = dto.getContentsByID(id);
                contentModel.setContents(contents);
                if (publisher != null && publisher.equals("1")) {
                    contentModel.setIsPublish(1);
                } else {
                    contentModel.setIsPublish(0);
                }
                contentModel.setCreated(getCurrentCreated());
                contentModel.setUserID(author);
                dto.updateContent(contentModel);

            } else {
                contentModel = new ContentModel();
                contentModel.setTitle(title);
                contentModel.setDescription(getDescription(contents));
                contentModel.setContents(contents);
                if (publisher != null && publisher.equals("1")) {
                    contentModel.setIsPublish(1);

                } else {
                    contentModel.setIsPublish(0);
                }
                contentModel.setCreated(getCurrentCreated());
                contentModel.setUserID(author);
                dto.adContent(contentModel);
            }
            request.getRequestDispatcher("/postDetail.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(AddContentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String getCurrentCreated() {
        DateFormat dateformat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateformat.format(date);
    }
    private String getDescription(String content){
        return content.substring(0, (content.length()-1)/2);
    
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
