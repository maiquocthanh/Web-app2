package com.funix.prj.asm4.servlets;

import com.funix.prj.asm4.dto.ContentDTO;
import com.funix.prj.asm4.dto.UserDTO;
import com.funix.prj.asm4.model.ContentModel;
import com.funix.prj.asm4.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hung
 */
public class LoginProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            UserDTO dto = new UserDTO();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            List<UserModel> ls = dto.getUser(username, password);

            request.getSession().removeAttribute("errorMessage");
            request.getSession().removeAttribute("isLogin");
            request.getSession().removeAttribute("isFirst");
            if (ls.size() > 0) {
                request.getSession().setAttribute("isLogin", true);
                if (dto.getFirstLogined(username)) {
                    request.getSession().setAttribute("isFirst", true);
                    dto.updateIsLogined(username, 1);
                }
                for (UserModel user : ls) {
                    request.getSession().setAttribute("username", user.getUserName());
                    break;
                }
                
                request.getSession().setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/posts.jsp");

            } else {
                request.setAttribute("errorMessage", "Sorry UserName or Password Error!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);

            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginProcessServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       processRequest(request, response);
       
       
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
