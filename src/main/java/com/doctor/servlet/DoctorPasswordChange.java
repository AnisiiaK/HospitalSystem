package com.doctor.servlet;

import com.dao.DoctorDao;
import com.db.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/doctorChangePassword")
public class DoctorPasswordChange extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id"));
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        DoctorDao doctorDao = new DoctorDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if(doctorDao.checkOldPassword(id, oldPassword)){
            if(doctorDao.changeOldPassword(id, newPassword)){
                session.setAttribute("sucMsg", "Password Change Successfully");
                resp.sendRedirect("doctor/edit_profile.jsp");
            }else{
                session.setAttribute("errorMsg", "Something wrong on server. Cannot change password");
                resp.sendRedirect("doctor/change_password.jsp");
            }
        }else {
            session.setAttribute("errorMsg", "Old password incorrect");
            resp.sendRedirect("doctor/edit_profile.jsp");

        }
    }
}
