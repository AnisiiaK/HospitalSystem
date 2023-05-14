package com.user.servlet;

import com.dao.UserDao;
import com.db.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userChangePassword")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid =Integer.parseInt(req.getParameter("uid"));
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        UserDao userDao = new UserDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if(userDao.checkOldPassword(uid, oldPassword)){
            if(userDao.changeOldPassword(uid, newPassword)){
                session.setAttribute("sucMsg", "Password Change Successfully");
                resp.sendRedirect("change_password.jsp");
            }else{
                session.setAttribute("errorMsg", "Something wrong on server. Cannot change password");
                resp.sendRedirect("change_password.jsp");
            }
        }else {
            session.setAttribute("errorMsg", "Old password incorrect");
            resp.sendRedirect("change_password.jsp");

        }
    }
}
