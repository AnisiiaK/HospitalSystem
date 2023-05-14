package com.dao;

import com.entity.Specialist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialistDao {
    private Connection connection;

    public SpecialistDao(Connection connection) {
        this.connection = connection;
    }

    public boolean addSpecialist(String spec) {
        boolean f = false;

        try {
            String sql = "insert into specialist(spec_name) values(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, spec);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return f;

    }

    public List<Specialist> getAllSpecialist(){
        List<Specialist> list = new ArrayList<>();
        Specialist specialist = null;

        try {
            String sql = "select * from specialist";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                specialist = new Specialist();
                specialist.setId(resultSet.getInt(1));
                specialist.setSpecialistName(resultSet.getString(2));
                list.add(specialist);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return list;
    }
}
