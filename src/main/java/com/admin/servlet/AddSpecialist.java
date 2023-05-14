package com.admin.servlet;

import com.dao.SpecialistDao;
import com.db.DBConnect;
import com.entity.Specialist;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String specName = req.getParameter("specName");

        SpecialistDao specialistDao = new SpecialistDao(DBConnect.getConn());
        boolean f = specialistDao.addSpecialist(specName);

        HttpSession session = req.getSession();

        if (f) {
            session.setAttribute("sucMsg", "Specialist Added");
            resp.sendRedirect("admin/index.jsp");
        }else {
            session.setAttribute("errorMsg", "smth wtong on server");
            resp.sendRedirect("admin/index.jsp");
        }
    }
}
