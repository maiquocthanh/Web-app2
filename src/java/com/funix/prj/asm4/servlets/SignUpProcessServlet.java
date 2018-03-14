/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funix.prj.asm4.servlets;

import com.funix.prj.asm4.dto.UserDTO;
import com.funix.prj.asm4.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hung
 */
public class SignUpProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDTO dto = new UserDTO();
        request.removeAttribute("message");
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmpassword = request.getParameter("confirmpassword");
            if (!password.equals(confirmpassword)) {
                request.setAttribute("message", "Password is not macth");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
                dispatcher.forward(request, response);

            } else if (dto.getUserByName(username)) {
                request.setAttribute("message", "Username is exist in db!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
                dispatcher.forward(request, response);

            } else if (username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                request.setAttribute("message", "Username or password is not empty");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
                dispatcher.forward(request, response);

            } else if (!validateUserName(username, password)) {
                request.setAttribute("message", "Username or password is wrong format");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signup.jsp");
                dispatcher.forward(request, response);

            } else {
                UserModel user = new UserModel(username, password, false);
                dto.addUser(user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SignUpProcessServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Check username and passwod
    public boolean validateUserName(String username, String password) {
        String userNamePattern1 = "\\w{6,}";
        String userNamePattern2 = "(?=.*[$#@%^&*])";
        String passwodPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+*=])(?=\\S+$).{8,}";
        return username.matches(userNamePattern1)
                && !username.matches(userNamePattern2)
                && password.matches(passwodPattern);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
