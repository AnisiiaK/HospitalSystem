package com.user.servlet;

import com.dao.UserDao;
import com.db.DBConnect;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user_register")
public class UserRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User u = new User(fullName, email, password);

            UserDao userDao = new UserDao(DBConnect.getConn());

            HttpSession session = req.getSession();

            boolean f = userDao.register(u);
            if (f) {
                System.out.println("user register ok");

                session.setAttribute("sucMsg", "Register Successfully");
                resp.sendRedirect("signup.jsp");
            } else {
                System.out.println("Something is wrong on server. User register is not ok");
                session.setAttribute("errorMsg", "Something is wrong on server");
                resp.sendRedirect("signup.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
