package com.doctor.servlet;

import com.dao.DoctorDao;
import com.db.DBConnect;
import com.entity.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/doctorEditProfile")
public class EditProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fullName = req.getParameter("fullname");
            String dob = req.getParameter("dob");
            String qualification = req.getParameter("qualification");
            String spec = req.getParameter("spec");
            String email = req.getParameter("email");
            String mobno = req.getParameter("mobno");
//            String password = req.getParameter("password");

            int id = Integer.parseInt(req.getParameter("id"));

            Doctor doctor = new Doctor(id, fullName, dob, qualification, spec, email, mobno, "");

            DoctorDao doctorDao = new DoctorDao(DBConnect.getConn());
            HttpSession session = req.getSession();

            if (doctorDao.editDoctorProfile(doctor)) {
                Doctor updateDoctor = doctorDao.getDoctorById(id);
                session.setAttribute("doctorObj", updateDoctor);
                session.setAttribute("sucMsgd", "Doctor Update Successfully");
                resp.sendRedirect("doctor/edit_profile.jsp");
            } else {
                session.setAttribute("errorMsgd", "something wrong on server. cannot add doctor");
                resp.sendRedirect("doctor/edit_profile.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
