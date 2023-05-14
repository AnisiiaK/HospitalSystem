package com.user.servlet;

import com.dao.AppointmentDao;
import com.db.DBConnect;
import com.entity.Appointment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addAppointment")
public class AppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("userid"));
        String fullname = req.getParameter("fullname");
        String gender = req.getParameter("gender");
        String age = req.getParameter("age");
        String appoint_date = req.getParameter("appoint_date");
        String email = req.getParameter("email");
        String phno = req.getParameter("phno");
        String diseases = req.getParameter("diseases");
        int doctor_id = Integer.parseInt(req.getParameter("doct"));
        String address = req.getParameter("address");


        Appointment ap = new Appointment(userId, fullname, gender, age, appoint_date, email, phno, diseases, doctor_id,
                address, "Pending");

        AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
        HttpSession session = req.getSession();

        if (dao.addAppointment(ap)) {
            session.setAttribute("sucMsg", "Appointment Successfully scheduled");
            resp.sendRedirect("user_appointment.jsp");
        } else {
            session.setAttribute("errorMsg", "Something wrong on server. Cannot make an appointment");
            resp.sendRedirect("user_appointment.jsp");
        }
    }
}