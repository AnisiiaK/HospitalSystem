package com.dao;

import com.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {
    private Connection connection;

    public DoctorDao(Connection connection) {
        this.connection = connection;
    }

    public boolean registerDoctor(Doctor d) {

        boolean f = false;
        try {
            String sql = "insert into doctor(full_name, dob, qualification, specialist, " +
                    "email, mobno, password) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, d.getFullName());
            preparedStatement.setString(2, d.getDob());
            preparedStatement.setString(3, d.getQualification());
            preparedStatement.setString(4, d.getSpecialist());
            preparedStatement.setString(5, d.getEmail());
            preparedStatement.setString(6, d.getMobNo());
            preparedStatement.setString(7, d.getPassword());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return f;
    }

    public List<Doctor> getAllDoctor() {
        List<Doctor> list = new ArrayList<>();
        Doctor doctor = null;
        String sql = "select * from doctor order by id desc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                doctor = new Doctor();
                doctor.setId(resultSet.getInt(1));
                doctor.setFullName(resultSet.getString(2));
                doctor.setDob(resultSet.getString(3));
                doctor.setQualification(resultSet.getString(4));
                doctor.setSpecialist(resultSet.getString(5));
                doctor.setEmail(resultSet.getString(6));
                doctor.setMobNo(resultSet.getString(7));
                doctor.setPassword(resultSet.getString(8));
                list.add(doctor);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return list;
    }

    public Doctor getDoctorById(int id) {
        Doctor doctor = null;
        String sql = "select * from doctor where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                doctor = new Doctor();
                doctor.setId(resultSet.getInt(1));
                doctor.setFullName(resultSet.getString(2));
                doctor.setDob(resultSet.getString(3));
                doctor.setQualification(resultSet.getString(4));
                doctor.setSpecialist(resultSet.getString(5));
                doctor.setEmail(resultSet.getString(6));
                doctor.setMobNo(resultSet.getString(7));
                doctor.setPassword(resultSet.getString(8));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return doctor;
    }

    public boolean updateDoctor(Doctor d) {

        boolean f = false;
        try {
            String sql = "update doctor set full_name=?, dob=?, qualification=?, specialist=?, " +
                    "email=?, mobno=?, password=? where id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, d.getFullName());
            preparedStatement.setString(2, d.getDob());
            preparedStatement.setString(3, d.getQualification());
            preparedStatement.setString(4, d.getSpecialist());
            preparedStatement.setString(5, d.getEmail());
            preparedStatement.setString(6, d.getMobNo());
            preparedStatement.setString(7, d.getPassword());
            preparedStatement.setInt(8, d.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return f;
    }

    public boolean deleteDoctor(int id) {
        boolean f = false;
        String sql = "delete from doctor where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return f;
    }

    public Doctor login(String email, String pass) {
        Doctor doctor = null;

        try {
            String sql = "select * from doctor where email = ? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                doctor = new Doctor();
                doctor.setId(resultSet.getInt(1));
                doctor.setFullName(resultSet.getString(2));
                doctor.setDob(resultSet.getString(3));
                doctor.setQualification(resultSet.getString(4));
                doctor.setSpecialist(resultSet.getString(5));
                doctor.setEmail(resultSet.getString(6));
                doctor.setMobNo(resultSet.getString(7));
                doctor.setPassword(resultSet.getString(8));

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return doctor;
    }

    public int countDoctor() {
        int i = 0;

        try {
            String sql = "select * from doctor";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                i++;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return i;
    }

    /*
     * WHY here?
     *
     * */
    public int countAppointment() {
        int i = 0;
        try {
            String sql = "select * from appointment";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    public int countAppointmentByDoctorId(int did) {
        int i = 0;
        try {
            String sql = "select * from appointment where doctor_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, did);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    public int countUser() {
        int i = 0;
        try {
            String sql = "select * from user_dtls";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    public int countSpecialist() {
        int i = 0;
        try {
            String sql = "select * from specialist";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }
/*
 * END OF WHY HERE
 * */
    public boolean checkOldPassword(int doctorId, String oldPassword){
        boolean f = false;

        try {
            String sql = "select * from doctor where id=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, oldPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                f = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return f;
    }

    public boolean changeOldPassword(int doctorId, String newPassword){
        boolean f = false;

        try {
            String sql = "update doctor set password = ? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, doctorId);

            int i = preparedStatement.executeUpdate();
            if(i == 1){
                f = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return f;
    }

    public boolean editDoctorProfile(Doctor d) {

        boolean f = false;
        try {
            String sql = "update doctor set full_name=?, dob=?, qualification=?, specialist=?, " +
                    "email=?, mobno=? where id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, d.getFullName());
            preparedStatement.setString(2, d.getDob());
            preparedStatement.setString(3, d.getQualification());
            preparedStatement.setString(4, d.getSpecialist());
            preparedStatement.setString(5, d.getEmail());
            preparedStatement.setString(6, d.getMobNo());
            preparedStatement.setInt(7, d.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                f = true;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return f;
    }


}
